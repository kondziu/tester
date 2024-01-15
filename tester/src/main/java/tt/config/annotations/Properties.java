package tt.config.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tt.cases.CaseAwareString;
import tt.config.Config;
import tt.config.PropertyConfig;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.annotations.exceptions.FileNotFoundException;
import tt.config.annotations.exceptions.NotAnnotatedException;
import tt.config.annotations.exceptions.UnsupportedFieldTypeException;
import tt.config.exceptions.ConfigException;

public interface Properties {

    public static final Map<Class<?>, FieldSetter> allowedTypes = Map.of(
        String.class, Properties::setStringField,
        Integer.class, Properties::setIntegerField,
        Integer.TYPE, Properties::setIntegerFieldFromPrimitive,
        Boolean.class, Properties::setBooleanField,
        Boolean.TYPE, Properties::setBooleanFieldFromPrimitive
    );

    @FunctionalInterface
    public interface FieldSetter {
        void apply(Properties obj, Config config, Field field, String property) throws ConfigException, AnnotationException, IllegalAccessException;
    }

    default void setStringField(Config config, Field field, String property) throws ConfigException, AnnotationException, IllegalAccessException {
        String value = config.getStringOrFail(property);
        field.set(this, value);
    }

    default void setIntegerFieldFromPrimitive(Config config, Field field, String property) throws ConfigException, AnnotationException, IllegalAccessException {
        int value = config.getIntegerOrFail(property);
        field.setInt(this, value);
    }

    default void setIntegerField(Config config, Field field, String property) throws ConfigException, AnnotationException, IllegalAccessException {
        Integer value = config.getIntegerOrFail(property);
        field.set(this, value);
    }

    default void setBooleanFieldFromPrimitive(Config config, Field field, String property) throws ConfigException, AnnotationException, IllegalAccessException {
        Boolean value = config.getBooleanOrFail(property);
        field.setBoolean(this, value);
    }

    default void setBooleanField(Config config, Field field, String property) throws ConfigException, AnnotationException, IllegalAccessException {
        boolean value = config.getBooleanOrFail(property);
        field.set(this, value);
    }

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

    default void setFieldValueFromConfig(Config config, Field field) throws ConfigException, AnnotationException, IllegalAccessException {
        
        Class<?> type = field.getType();        
        if (!Properties.allowedTypes.containsKey(type)) {
            throw new UnsupportedFieldTypeException(type, field, type, allowedTypes.keySet());
        }
        String property = this.extractPropertyNameFromField(field);
        FieldSetter setter = Properties.allowedTypes.get(type);
        setter.apply(this, config, field, property);
    }

    default void initializeProperties() throws ConfigException, AnnotationException, IllegalAccessException {
        String file = this.extractPropertiesFilePath();
        Config config = new PropertyConfig(file);
        List<Field> fields = this.extractOptionAnnotatedFields();

        for (Field field: fields) {
            this.setFieldValueFromConfig(config, field);
        }
    }
}
