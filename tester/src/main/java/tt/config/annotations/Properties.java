package tt.config.annotations;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
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

public class Properties {

    private static final From fromPropertiesAnnotation(Class cls) throws NotAnnotatedException {
        Annotation propertyInterface = cls.getAnnotation(From.class);
        if (null == propertyInterface) {
            throw new NotAnnotatedException(cls, From.class);
        }            
        return (From) propertyInterface;
    }

    private static final String fromFile(Class cls) throws NotAnnotatedException, FileNotFoundException {
        From from = Properties.fromPropertiesAnnotation(cls);
        String path = from.file();
        URL resource = Config.class.getClassLoader().getResource(path);
        if (resource == null) {
            throw new FileNotFoundException(cls, path);
        }
        return path;
    }

    private static final List<Field> optionAnnotatedFields(Class cls) {
        return Stream.of(cls.getFields())
            .filter(f -> f.isAnnotationPresent(Option.class))
            .collect(Collectors.toList());
    }

    private static final String getPropertyName(Field field) {
        Option annotation = field.getAnnotation(Option.class);
        String property = annotation.property();
        if (property.isEmpty()) {
            String name = field.getName();
            property = CaseAwareString.fromCamelCase(name).intoSnakeCase(false);
        }
        return property;
    }

    private static final void setFieldValueFromConfig(Config config, Object object, Field field) throws ConfigException, AnnotationException, IllegalAccessException {
        
        Class type = field.getType();
        
        Class[] allowedTypes = new Class[] {String.class, Integer.class, Integer.TYPE, Boolean.class, Boolean.TYPE};
        String property = getPropertyName(field);

        if (type == String.class) {
            String value = config.getStringOrFail(property);
            field.set(object, value);
            return;
        }

        if (type == Integer.TYPE) {
            int value = config.getIntegerOrFail(property);
            field.setInt(object, value);
            return;
        }

        if (type == Integer.class) {
            Integer value = config.getIntegerOrFail(property);
            field.set(object, value);
            return;
        }

        if (type == Boolean.TYPE) {
            boolean value = config.getBooleanOrFail(property);
            field.setBoolean(object, value);
            return;
        }

        if (type == Boolean.class) {
            Boolean value = config.getBooleanOrFail(property);
            field.set(object, value);
            return;
        }

        throw new UnsupportedFieldTypeException(type, field, type, allowedTypes);
    }

    public static final void initializeProperties(Object object) throws ConfigException, AnnotationException, IllegalAccessException {

        Class cls = object.getClass();
        String file = Properties.fromFile(cls);
        Config config = new PropertyConfig(file);
        List<Field> fields = Properties.optionAnnotatedFields(cls);     

        System.err.println("FIELDS " + fields);
        for (Field field: fields) {
            setFieldValueFromConfig(config, object, field);
        }
    }
}
