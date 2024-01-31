package tt.config.helpers;

import tt.config.annotations.Converter;
import tt.config.exceptions.ConfigException;

public class IntegerIdentity implements Converter<String, Integer> {

    @Override
    public Integer apply(String obj) throws ConfigException {
        return Integer.parseInt(obj);
    }
    
}
