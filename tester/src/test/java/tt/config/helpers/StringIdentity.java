package tt.config.helpers;

import tt.config.converters.Converter;
import tt.config.exceptions.ConfigException;

public class StringIdentity implements Converter<String, String> {

    @Override
    public String apply(String obj) throws ConfigException {
        return obj;
    }
    
}
