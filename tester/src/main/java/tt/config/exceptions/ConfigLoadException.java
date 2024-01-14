package tt.config.exceptions;

public class ConfigLoadException extends ConfigException {

    private static final String MESSAGE_FORMAT = "Cannot load configuration from resource: '%s'";

    public ConfigLoadException(String descriptor, Throwable reason) {
        super(descriptor, String.format(MESSAGE_FORMAT, descriptor), reason);
    }
}
