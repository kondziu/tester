package tt.config.annotations;

import tt.config.exceptions.ConfigException;

@FunctionalInterface
public interface Converter<Ti, To> {
        To apply(Ti obj) throws ConfigException;
}