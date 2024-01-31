package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import tt.config.annotations.Converter;

public class CannotCreateConverterInstanceException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s is annotated to convert a value to type %s with converter %s, but the converter cannot be instantiated: %s";

    public CannotCreateConverterInstanceException(Class<?> cls, Field field, Type fieldType, Class<?> converter,
            Throwable reason) {
        super(String.format(MESSAGE_FORMAT, field.getName(), cls.getSimpleName(), fieldType.getTypeName(),
                converter.getSimpleName(), Converter.class.getCanonicalName(), reason.getMessage()), reason);
    }

}