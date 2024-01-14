package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.From;

@From(file="tt/options/magic_word.properties")
public class MagicWord {

    @Option
    public String accept;

    @Option
    public String bypass;
}
