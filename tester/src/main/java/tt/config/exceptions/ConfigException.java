package tt.config.exceptions;

public class ConfigException extends Exception {
    public ConfigException(String message) {
        super(message);
    }
    public ConfigException(String configDescriptor, String message) {
        super(String.format("[%s] %s", configDescriptor, message));
    }
    public ConfigException(String configDescriptor, String message, Throwable cause) {
        super(String.format("[%s] %s", configDescriptor, message), cause);
    }
}

