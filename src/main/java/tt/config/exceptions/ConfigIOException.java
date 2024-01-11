package tt.config.exceptions;

public class ConfigIOException extends ConfigException {
    public ConfigIOException(String configDescriptor, String message) {
        super(configDescriptor, message);
    }
    public ConfigIOException(String configDescriptor, String message, Throwable cause) {
        super(configDescriptor, message, cause);
    }
}

