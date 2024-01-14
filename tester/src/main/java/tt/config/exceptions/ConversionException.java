package tt.config.exceptions;

public class ConversionException extends ConfigException {

    private final static String MESSAGE_FORMAT = "Cannot convert value '%s' of property '%s' to type '%s'";

    public <T> ConversionException(String configDescriptor, String key, String value, Class<T> intoType) {
        super(configDescriptor, String.format(MESSAGE_FORMAT, value, key, intoType.getName()));
    }

    public <T> ConversionException(String configDescriptor, String key, String value, Class<T> intoType, Throwable cause) {
        super(configDescriptor, String.format(MESSAGE_FORMAT, value, key, intoType.getName()), cause);
    }
}
