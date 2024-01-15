package tt.config.annotations.exceptions;

public class NotAnnotatedException extends AnnotationException {
    private static final String MESSAGE_FORMAT = "Class %s is not annotated as @%s, so it cannot be initialized from a '.property' file.";

    public NotAnnotatedException(Class<?> cls, Class<?> annotation) {
        super(String.format(MESSAGE_FORMAT, cls.getSimpleName(), annotation.getSimpleName()));
    }
}
