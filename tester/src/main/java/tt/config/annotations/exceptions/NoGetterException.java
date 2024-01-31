package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

public class NoGetterException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s requires a getter for type %s, but none were found. Getters are available for types: %s";

    public NoGetterException(Class<?> cls, Field field, Class<?> type, Set<Class<?>> availableGetters) {
        super(String.format(MESSAGE_FORMAT, field.getName(), cls.getSimpleName(), type.getSimpleName(),
                availableGetters.stream()
                        .map(c -> c.getName())
                        .collect(Collectors.joining(", "))));
    }

}
