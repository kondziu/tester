package tt.config.annotations.exceptions;

import java.lang.reflect.Type;

import tt.config.annotations.Properties;
import tt.config.annotations.PropertyField;

public class CannotConvertFromTypeException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s is annotated to convert a value to type %s with a converter, the input type is incorrect: it should be string, but is %s";

        private static <T extends Properties> String message(PropertyField<T> field, Type fromType) {
            return String.format(MESSAGE_FORMAT, 
                field.field.getName(), 
                field.parent.cls.getCanonicalName(), 
                field.type.getTypeName(), 
                field.option.getClass().getCanonicalName(),
                fromType.getTypeName());
    }

    // public <T extends Properties> CannotConvertIntoTypeException(PropertyField<T> field, Throwable cause) {
    //     super(CannotConvertIntoTypeException.message(field), cause);
    // }

    public <T extends Properties> CannotConvertFromTypeException(PropertyField<T> field, Type fromType) {
        super(CannotConvertFromTypeException.message(field, fromType));
    }
}
