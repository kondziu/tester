package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import tt.config.annotations.Converter;
import tt.config.annotations.Properties;
import tt.config.annotations.PropertyField;

public class NotAConverterException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s is annotated to convert a value to type %s with a converter, but the designated converter class does not implement %s";

    private static <T extends Properties> String message(PropertyField<T> field) {
        return String.format(MESSAGE_FORMAT, 
            field.field.getName(), 
            field.cls().getCanonicalName(), 
            field.type.getTypeName(), 
            Converter.class.getCanonicalName());
    }

    // TODO remove
    public NotAConverterException(Class<?> cls, Field field, Type fieldType, Class<?> converter) {
        super(String.format(MESSAGE_FORMAT, field.getName(), cls.getSimpleName(), fieldType.getTypeName(),
                Converter.class.getCanonicalName()));
    }

    public <T extends Properties> NotAConverterException(PropertyField<T> field, Throwable cause) {
        super(NotAConverterException.message(field), cause);
    }

    public <T extends Properties> NotAConverterException(PropertyField<T> field) {
        super(NotAConverterException.message(field));
    }
}
