package tt.config.annotations.exceptions;

import tt.config.annotations.Properties;
import tt.config.annotations.PropertyField;

public class CannotCreateConverterInstanceException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s is annotated to convert a value to type %s with a custom converter, but the converter cannot be instantiated: %s";

    private static <T extends Properties> String message(PropertyField<T> field, Throwable cause) {
        return String.format(MESSAGE_FORMAT, 
            field.field.getName(), 
            field.parent.cls.getCanonicalName(), 
            field.type.getTypeName(), 
            cause.getMessage());
    }

    public <T extends Properties> CannotCreateConverterInstanceException(PropertyField<T> field, Throwable cause) {
        super(CannotCreateConverterInstanceException.message(field, cause), cause);
    }
}