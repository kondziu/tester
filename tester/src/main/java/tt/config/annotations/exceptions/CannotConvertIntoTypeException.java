package tt.config.annotations.exceptions;

import tt.config.annotations.Properties;
import tt.config.annotations.PropertyField;

public class CannotConvertIntoTypeException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s is annotated to convert a value to type %s with a converter, but the designated converter class converts to type the wrong type";

        private static <T extends Properties> String message(PropertyField<T> field) {
            return String.format(MESSAGE_FORMAT, 
                field.field.getName(), 
                field.cls().getCanonicalName(), 
                field.type.getTypeName(), 
                field.option.getClass().getCanonicalName());
    }

    // public CannotConvertIntoTypeException(Class<?> cls, Field field, Type fieldType, Class<?> converter,
    //         Type intoType) {
    //     super(String.format(MESSAGE_FORMAT, field.getName(), cls.getSimpleName(), fieldType.getTypeName()));
    // }

    // public <T extends Properties> CannotConvertIntoTypeException(PropertyField<T> field, Throwable cause) {
    //     super(CannotConvertIntoTypeException.message(field), cause);
    // }

    public <T extends Properties> CannotConvertIntoTypeException(PropertyField<T> field) {
        super(CannotConvertIntoTypeException.message(field));
    }
}
