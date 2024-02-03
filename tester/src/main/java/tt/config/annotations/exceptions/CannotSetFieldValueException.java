package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class CannotSetFieldValueException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Cannot set value for field %s of type %s with annotation %s in class %s: %s.";

    public CannotSetFieldValueException(Class<?> cls, Field field, Type type, Class<?> annotation, Throwable cause) {
        super(String.format(MESSAGE_FORMAT, field, type, annotation, cls, cause.getMessage()), cause);
    }

}
