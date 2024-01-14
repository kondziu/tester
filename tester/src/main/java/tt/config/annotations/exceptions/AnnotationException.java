package tt.config.annotations.exceptions;

public class AnnotationException extends Exception {
    public AnnotationException(String message) {
        super(message);
    }
    public AnnotationException(String message, Throwable cause) {
        super(message, cause);
    }
}
