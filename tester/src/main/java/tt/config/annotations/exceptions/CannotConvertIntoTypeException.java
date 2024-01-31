package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import tt.config.annotations.Converter;

public class CannotConvertIntoTypeException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s is annotated to convert a value to type %s with a converter, but the designated converter class %s converts to type %s";

    public CannotConvertIntoTypeException(Class<?> cls, Field field, Type fieldType, Class<?> converter,
            Type intoType) {
        super(String.format(MESSAGE_FORMAT, field.getName(), cls.getSimpleName(), fieldType.getTypeName(),
                converter.getSimpleName(), intoType.getTypeName()));
    }

}
