package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

import tt.config.annotations.Properties;
import tt.config.annotations.PropertyField;

public class NoGetterException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s requires a getter for type %s, but none were found. Getters are available for types: %s";

    private static <T extends Properties> String message(PropertyField<T> field) {
        var getters = PropertyField.getters.keySet().stream()
                        .map(c -> c.getName())
                        .collect(Collectors.joining(", "));

        return String.format(MESSAGE_FORMAT, 
            field.field.getName(), 
            field.cls().getCanonicalName(), 
            field.type.getTypeName(), 
            getters);
    }

    // TODO remove
    public NoGetterException(Class<?> cls, Field field, Type type) {
        super(String.format(MESSAGE_FORMAT, field.getName(), cls.getSimpleName(), type,
        PropertyField.getters.keySet().stream()
                        .map(c -> c.getName())
                        .collect(Collectors.joining(", "))));
    }

    public <T extends Properties> NoGetterException(PropertyField<T> field, Throwable cause) {
        super(NoGetterException.message(field), cause);
    }

    public <T extends Properties> NoGetterException(PropertyField<T> field) {
        super(NoGetterException.message(field));
    }

}
