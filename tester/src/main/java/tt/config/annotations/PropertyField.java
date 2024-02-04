package tt.config.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import tt.cases.CaseAwareString;
import tt.config.Config;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.annotations.exceptions.CannotConvertFromTypeException;
import tt.config.annotations.exceptions.CannotConvertIntoTypeException;
import tt.config.annotations.exceptions.CannotCreateConverterInstanceException;
import tt.config.annotations.exceptions.CannotSetFieldValueException;
import tt.config.annotations.exceptions.NoGetterException;
import tt.config.annotations.exceptions.NoSetterException;
import tt.config.annotations.exceptions.NotAConverterException;
import tt.config.annotations.exceptions.UnsupportedFieldTypeException;
import tt.config.exceptions.ConfigException;
import tt.exceptions.Utils;

public class PropertyField<T extends Properties> {

    @FunctionalInterface
    public static interface FieldSetter<T> {
        void apply(Field field, Properties obj, T value) throws AnnotationException, IllegalAccessException;
    }

    public static final Map<Class<?>, FieldSetter<Object>> setters = Map.of(
            String.class, Field::set,
            Integer.class, Field::set,
            Boolean.class, Field::set,
            Integer.TYPE, (field, obj, value) -> field.setInt(obj, (Integer) value),
            Boolean.TYPE, (field, obj, value) -> field.setBoolean(obj, (Boolean) value));

    @FunctionalInterface
    public static interface ValueGetter<T> {
        T apply(Config config, String property) throws ConfigException;
    }

    public static final Map<Class<?>, ValueGetter<Object>> getters = Map.of(
            String.class, Config::getStringOrFail,
            Integer.class, Config::getIntegerOrFail,
            Boolean.class, Config::getBooleanOrFail,
            Integer.TYPE, Config::getIntegerOrFail,
            Boolean.TYPE, Config::getBooleanOrFail);

    public final PropertyClass<T> parent;
    public final Field field;
    public final Option option;
    public final Type type;

    PropertyField(PropertyClass<T> parent, Field field) {
        this.parent = parent;
        this.field = field;
        this.option = field.getAnnotation(Option.class);
        this.type = field.getGenericType();
    }

    public PropertyPath path() {

        String property = this.option.property();

        if (property.isEmpty()) {
            String name = this.field.getName();
            property = CaseAwareString.fromCamelCase(name).intoSnakeCase(false);
        }
        return this.parent.path.concat(property);
    }

    public <C> Optional<Converter<String, C>> converter() throws AnnotationException {
        
        Class<?> converter = this.option.converter();
        if (converter == None.class) {
            return Optional.empty();
        }

        var interfaces = List.of(converter.getInterfaces());
        Integer converterIndex = Utils.check(
            () -> interfaces.indexOf(Converter.class),
            (result) -> result >= 0,
            () -> new NotAConverterException(this)
        );

        var genericInterface = converter.getGenericInterfaces()[converterIndex];
        Type[] generics = ((ParameterizedType) genericInterface).getActualTypeArguments();

        Utils.check(
            () -> generics[0],
            (result) -> result.equals(String.class),
            () -> new CannotConvertFromTypeException(this, generics[0])
        );

        Utils.check(
            () -> generics[1],
            (result) -> result.equals(this.type),
            () -> new CannotConvertIntoTypeException(this)
        );

        @SuppressWarnings("unchecked")
        Converter<String, C> converterInstance = Utils.attempt(
            () -> (Converter<String, C>) converter.getConstructor().newInstance(), 
            (e) -> new CannotCreateConverterInstanceException(this, e)
        );
        return Optional.of(converterInstance);
    }

    private void setDefault() throws ConfigException, AnnotationException {
        System.err.println("default");
        Utils.check2(
            () -> PropertyField.setters.containsKey(type), 
            () -> {
                System.err.println("X");
                return new UnsupportedFieldTypeException(this);
            }
        );

        ValueGetter<Object> getter = Utils.notNull(
            () -> PropertyField.getters.get(type), 
            () -> new NoGetterException(this)
        );

        FieldSetter<Object> setter = Utils.notNull(
            () -> PropertyField.setters.get(type), 
            () -> new NoSetterException(this)
        );

        Object value = getter.apply(this.parent.config, this.path().toString());
        Utils.attempt(
            () -> setter.apply(this.field, this.parent.instance, value), 
            (e) -> new CannotSetFieldValueException(this, e)
        );
    }

    private <C> void setFromConverter(Converter<String, C> converter) throws ConfigException, AnnotationException {

        System.err.println("conv");
        String value = this.parent.config.getOrFail(this.path().getLastOrEmpty());
        Object object = converter.apply(value);

        Utils.attempt(
            () -> field.set(this.parent.instance, object), 
            (e) -> new CannotSetFieldValueException(this, e)
        );
    }

    public static boolean isPropertyClass(Class<?> cls) {

        if (Object.class == cls) {
            return false;
        }

        if (Properties.class == cls) {
            return true;
        }
        
        Class<?>[] interfaces = cls.getInterfaces();
        if (Stream.of(interfaces).anyMatch(i -> Properties.class == i)) {
            return true;
        }            
        Stream.of(interfaces).forEach(PropertyField::isPropertyClass);

        Class<?> parent = cls.getSuperclass();
        if (Properties.class == parent) {
            return true;
        }

        if (parent == null) {
            return false;
        }

        return isPropertyClass(parent);
}    

    public boolean isNestedProperty() {
        var field_type =  this.field.getType();
        System.err.println("field type" + field_type);        
        return isPropertyClass(field_type);
    }

    @SuppressWarnings("unchecked")
    private void setNestedProperty() throws ConfigException, AnnotationException {
        System.err.println("nested");
        Constructor<Properties> constructor = Utils.attempt(
            () -> (Constructor<Properties>) field.getType().getConstructor(), 
            (e) -> new AnnotationException("WIP2", e)
        );

        System.err.println("constructor " + constructor);
        Properties nestedProperties = Utils.attempt(
            () -> constructor.newInstance(),
            (e) -> {
                e.printStackTrace();;
                return new AnnotationException("WIP3", e);
            }
        );

        Utils.attempt(
            () -> field.set(this.parent.instance, nestedProperties),
            (e) -> new AnnotationException("WIP4", e)
        );

        nestedProperties.initialize(this);
    }

    public void set() throws ConfigException, AnnotationException {

        System.err.println("set: " + this);

        var converter = this.converter();

        if (this.isNestedProperty()) {
            Utils.check2(
                () -> converter.isEmpty(),
                () -> new AnnotationException("WIP1")
            );

            this.setNestedProperty();
            return;
        }

        if (converter.isPresent()) {
            this.setFromConverter(converter.get());
            return;
        }

        this.setDefault();
    }

    @Override
    public String toString() {
        String format = "PropertyField { field: %s, option: %s, type: %s, path: %s, parent: %s }";
        return String.format(format, this.field, this.option, this.type, this.path(), this.parent.cls);
    }
}
