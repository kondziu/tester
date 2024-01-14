package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnsupportedFieldTypeException extends AnnotationException {
    private static final String MESSAGE_FORMAT = "Field %s from class is has type %s, which is unsupported by annotation @%s. Allowed types: %s";

    public UnsupportedFieldTypeException(Class cls, Field field, Class annotation, Class[] allowedTypes) {
        super(
            String.format(MESSAGE_FORMAT, 
            field.getName(), 
            cls.getName(), 
            annotation.getSimpleName(), 
            Stream.of(allowedTypes).map(c -> c.getName()).collect(Collectors.joining(", ")))
        );
    }
}
