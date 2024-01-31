package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

public class UnsupportedFieldTypeException extends AnnotationException {
    private static final String MESSAGE_FORMAT = "Field %s from class %s has type %s, which is unsupported by annotation @%s. Allowed types: %s";

    public UnsupportedFieldTypeException(Class<?> cls, Field field, Class<?> type, Class<?> annotation,
            Set<Class<?>> allowedTypes) {
        super(
                String.format(MESSAGE_FORMAT,
                        field.getName(),
                        cls.getName(),
                        type.getName(),
                        annotation.getSimpleName(),
                        allowedTypes.stream()
                                .map(c -> c.getName())
                                .collect(Collectors.joining(", "))));
    }
}
