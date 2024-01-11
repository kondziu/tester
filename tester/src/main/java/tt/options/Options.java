package tt.options;

import tt.config.Config;
import tt.config.exceptions.ConfigException;
import tt.config.PropertyConfig;

public class Options {

    public static final String DEFAULT_VALUES_PATH = "/tt/options/default.properties";

    /*final*/ private Config config;

    public Options() throws ConfigException {
        this.config = new PropertyConfig(DEFAULT_VALUES_PATH);
    }

    public String magicWordGood() throws ConfigException {
        return this.config.getStr("magic_word.good");
    }
    
    public String magicWordBypass() throws ConfigException {
        return this.config.getStr("magic_word.bypass");
    }
}