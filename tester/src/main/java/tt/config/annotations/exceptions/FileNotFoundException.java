package tt.config.annotations.exceptions;

public class FileNotFoundException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Cannot initialize class %s, because resource '%s' does no exist";

    public FileNotFoundException(Class<?> cls, String resource) {
        super(String.format(MESSAGE_FORMAT, cls.getName(), resource));
    }
}
