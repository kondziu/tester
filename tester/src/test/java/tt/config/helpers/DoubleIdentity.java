package tt.config.helpers;

import tt.config.converters.Converter;
import tt.config.exceptions.ConfigException;

public class DoubleIdentity implements Converter<String, Double> {

    @Override
    public Double apply(String obj) throws ConfigException {
        System.out.println("OBJ: " + obj);
        return Double.parseDouble(obj);
    }
    
}
