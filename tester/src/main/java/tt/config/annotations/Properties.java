package tt.config.annotations;

import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;

public interface Properties {
    default void initialize() throws ConfigException, AnnotationException {
        var property = PropertyClass.from(this);
        property.populateFields();
    }

    default <T extends Properties> void initialize(PropertyField<T /*baka!*/> field) throws ConfigException, AnnotationException {
        var property = PropertyClass.from(this, field);
        property.populateFields();
    }
}
