package tt.config.exceptions;

public class ConfigOpenException extends ConfigException {

    private static final String MESSAGE_FORMAT = "Cannot open resource: '%s' to load configuration";

    public ConfigOpenException(String descriptor) {
        super(descriptor, String.format(MESSAGE_FORMAT, descriptor));
    }
}
