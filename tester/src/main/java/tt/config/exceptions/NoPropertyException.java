package tt.config.exceptions;

public class NoPropertyException extends ConfigException {
    public NoPropertyException(String configDescriptor, String key) {
        super(configDescriptor, String.format("No such property `%s`" , key));
    }
}

