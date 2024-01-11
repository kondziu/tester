package tt.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import tt.config.exceptions.ConfigException;
import tt.config.exceptions.ConfigIOException;
import tt.config.exceptions.NoPropertyException;

public class PropertyConfig extends AbstractConfig implements Config {

    final private String path;
    private Properties properties;

    public static void main(String[] args) throws Exception {
        Config config = new PropertyConfig("default.properties");
        System.out.println(config.getStr("magic_word.bypass"));
        System.out.println(config.getStr("magic_word.good"));
    }

    public PropertyConfig(String path) {
        this.path = path;
    }

    @Override
    final public boolean isLoaded() {
        return null != this.properties;
    }

    @Override
    final public String get(String key) throws ConfigException {
        String value = this.properties.getProperty(key);
        if (null == value) {
            throw new NoPropertyException(this.path, key);
        }
        return value;
    }

    @Override
    final public void load() throws ConfigException {
        // Only open the resource once.
        if (this.isLoaded()) {
            return;
        }

        // Locate the resource from the classpath and read the contents of the
        // resource as a Property file.
        this.properties = new Properties();
        InputStream input = Config.class.getClassLoader().getResourceAsStream(this.path);
        if (null == input) {
            // TODO make into its own exception class
            String message = 
                String.format("Cannot open resource: '%s' to load configuration", this.path);
            throw new ConfigIOException(this.path, message);
        }
        try {
            this.properties.load(input);
        } catch (IOException e) {
            // TODO make into its own exception class
            String message = 
                String.format("Cannot load configuration from resource: '%s'", this.path);
            throw new ConfigIOException(this.path, message, e);
        }
    }

    @Override
    public Set<String> keys() throws ConfigException {
        this.load();
        return this.properties.stringPropertyNames();
    }
}
