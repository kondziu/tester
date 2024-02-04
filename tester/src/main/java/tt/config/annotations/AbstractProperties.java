package tt.config.annotations;

import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;

public class AbstractProperties implements Properties {

    public AbstractProperties() throws ConfigException, AnnotationException {
        this.initialize();
    }

    public <T extends Properties> AbstractProperties(PropertyField<T> field) throws ConfigException, AnnotationException {
        this.initialize(field);
    }
}
