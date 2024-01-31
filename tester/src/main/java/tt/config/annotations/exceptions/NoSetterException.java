package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

public class NoSetterException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s requires a setter for type %s, but none were found. Getters are available for types: %s";

    public NoSetterException(Class<?> cls, Field field, Class<?> type, Set<Class<?>> availableSetters) {
        super(String.format(MESSAGE_FORMAT, field.getName(), cls.getSimpleName(), type.getSimpleName(),
                availableSetters.stream()
                        .map(c -> c.getName())
                        .collect(Collectors.joining(", "))));
    }

}
