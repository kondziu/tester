package tt.config.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tt.cases.CaseAwareString;
import tt.config.Config;
import tt.config.PropertyConfig;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.annotations.exceptions.CannotConvertIntoTypeException;
import tt.config.annotations.exceptions.CannotCreateConverterInstanceException;
import tt.config.annotations.exceptions.FileNotFoundException;
import tt.config.annotations.exceptions.NoGetterException;
import tt.config.annotations.exceptions.NoSetterException;
import tt.config.annotations.exceptions.NotAConverterException;
import tt.config.annotations.exceptions.NotAnnotatedException;
import tt.config.annotations.exceptions.UnsupportedFieldTypeException;
import tt.config.exceptions.ConfigException;

public interface Properties {

    @FunctionalInterface
    public interface FieldSetter<T> {
        void apply(Field field, Properties obj, T value) throws AnnotationException, IllegalAccessException;
    }

    public static final Map<Class<?>, FieldSetter<Object>> setters = Map.of(
            String.class, Field::set,
            Integer.class, Field::set,
            Boolean.class, Field::set,
            Integer.TYPE, (field, obj, value) -> field.setInt(obj, (Integer) value),
            Boolean.TYPE, (field, obj, value) -> field.setBoolean(obj, (Boolean) value));

    @FunctionalInterface
    public interface ValueGetter<T> {
        T apply(Config config, String property) throws ConfigException;
    }

    public static final Map<Class<?>, ValueGetter<Object>> getters = Map.of(
            String.class, Config::getStringOrFail,
            Integer.class, Config::getIntegerOrFail,
            Boolean.class, Config::getBooleanOrFail,
            Integer.TYPE, Config::getIntegerOrFail,
            Boolean.TYPE, Config::getBooleanOrFail);

    default From extractPropertiesAnnotation() throws NotAnnotatedException {
        Class<?> cls = this.getClass();
        Annotation propertyInterface = cls.getAnnotation(From.class);
        if (null == propertyInterface) {
            throw new NotAnnotatedException(cls, From.class);
        }
        return (From) propertyInterface;
    }

    default String extractPropertiesFilePath() throws NotAnnotatedException, FileNotFoundException {
        From from = this.extractPropertiesAnnotation();
        String path = from.file();
        URL resource = Config.class.getClassLoader().getResource(path);
        if (resource == null) {
            Class<?> cls = this.getClass();
            throw new FileNotFoundException(cls, path);
        }
        return path;
    }

    default List<Field> extractOptionAnnotatedFields() {
        Class<?> cls = this.getClass();
        return Stream.of(cls.getFields())
                .filter(f -> f.isAnnotationPresent(Option.class))
                .collect(Collectors.toList());
    }

    default String extractPropertyNameFromField(Field field) {
        Option annotation = field.getAnnotation(Option.class);
        String property = annotation.property();
        if (property.isEmpty()) {
            String name = field.getName();
            property = CaseAwareString.fromCamelCase(name).intoSnakeCase(false);
        }
        return property;
    }

    default <T> Optional<Converter<String, T>> extractConverterFromField(Field field) throws AnnotationException {
        Option annotation = field.getAnnotation(Option.class);
        Type fieldType = field.getGenericType();
        Class<?> cls = annotation.converter();

        if (cls == None.class) {
            return Optional.empty();
        }

        var interfaces = List.of(cls.getInterfaces());
        int converterIndex = interfaces.indexOf(Converter.class);
        if (converterIndex < 0) {
            throw new NotAConverterException(this.getClass(), field, fieldType, cls);
        }

        var genericInterface = cls.getGenericInterfaces()[converterIndex];
        assert (genericInterface instanceof ParameterizedType);

        Type[] generics = ((ParameterizedType) genericInterface).getActualTypeArguments();
        assert (generics.length == 2);

        Type from = generics[0];
        Type into = generics[1];

        System.err.println("into: " + into);
        System.err.println("fieldType: " + fieldType);

        assert (from == String.class);
        assert (into instanceof Class<?>);

        if (!into.equals(fieldType)) {
            throw new CannotConvertIntoTypeException(this.getClass(), field, fieldType, cls, into);
        }

        try {
            @SuppressWarnings("unchecked")
            Converter<String, T> converter = (Converter<String, T>) cls.getConstructor().newInstance();
            return Optional.of(converter);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new CannotCreateConverterInstanceException(this.getClass(), field, fieldType, cls, e);
        }
    }

    default <T> void setFieldValueFromConfigDefault(Config config, Field field, String property)
            throws ConfigException, AnnotationException, IllegalAccessException {
        Class<?> type = field.getType();
        if (!Properties.setters.containsKey(type)) {
            throw new UnsupportedFieldTypeException(this.getClass(), field, type, Option.class, setters.keySet());
        }

        ValueGetter<Object> getter = Properties.getters.get(type);
        if (getter == null) {
            throw new NoGetterException(this.getClass(), field, type, getters.keySet());
        }

        FieldSetter<Object> setter = Properties.setters.get(type);
        if (setter == null) {
            throw new NoSetterException(this.getClass(), field, type, setters.keySet());
        }

        Object value = getter.apply(config, property);
        setter.apply(field, this, value);
    }

    default <T> void setFieldValueFromConfigCustom(Config config, Field field, String property,
            Converter<String, ?> converter) throws ConfigException, AnnotationException, IllegalAccessException {
        String value = config.getOrFail(property);
        Object object = converter.apply(value);
        field.set(this, object);
    }

    default <T> void setFieldValueFromConfig(Config config, Field field)
            throws ConfigException, AnnotationException, IllegalAccessException {

        String property = this.extractPropertyNameFromField(field);
        var converterOption = this.extractConverterFromField(field);

        if (converterOption.isEmpty()) {
            this.setFieldValueFromConfigDefault(config, field, property);
        } else {
            var converter = converterOption.get();
            this.setFieldValueFromConfigCustom(config, field, property, converter);
        }
    }

    default void initializeProperties() throws ConfigException, AnnotationException, IllegalAccessException {
        String file = this.extractPropertiesFilePath();
        Config config = new PropertyConfig(file);
        List<Field> fields = this.extractOptionAnnotatedFields();

        for (Field field : fields) {
            this.setFieldValueFromConfig(config, field);
        }
    }
}
