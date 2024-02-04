package tt.config.annotations.exceptions;

import tt.config.annotations.Properties;
import tt.config.annotations.PropertyField;

public class CannotSetFieldValueException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Cannot set value for field %s of type %s with annotation %s in class %s: %s.";

    private static <T extends Properties> String message(PropertyField<T> field, Throwable cause) {
        return String.format(MESSAGE_FORMAT, 
            field.field.getName(), 
            field.type.getTypeName(), 
            field.option.getClass().getCanonicalName(),
            field.cls().getCanonicalName(), 
            cause.getMessage());
    }

    public <T extends Properties> CannotSetFieldValueException(PropertyField<T> field, Throwable cause) {
        super(CannotSetFieldValueException.message(field, cause), cause);
    }
}
