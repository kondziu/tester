package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;
import tt.config.annotations.From;

@From(file="tt/options/magic_word.properties")
public class MagicWords implements Properties {

    public MagicWords() throws IllegalAccessException, ConfigException, AnnotationException {
        this.initializeProperties();
    }

    @Option
    public String accept;

    @Option
    public String bypass;
}
