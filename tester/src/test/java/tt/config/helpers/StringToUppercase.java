package tt.config.helpers;

import tt.config.annotations.Converter;
import tt.config.exceptions.ConfigException;

public class StringToUppercase implements Converter<String, String> {
    @Override
    public String apply(String obj) throws ConfigException {
        return obj.toUpperCase();
    }
}
