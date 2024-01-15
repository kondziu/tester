package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;
import tt.config.annotations.From;

@From(file="tt/options/magic_word.properties")
public class MagicWord {

    public MagicWord() throws IllegalAccessException, ConfigException, AnnotationException {
        Properties.initialize(this);
    }

    @Option
    public String accept;

    @Option
    public String bypass;
}
