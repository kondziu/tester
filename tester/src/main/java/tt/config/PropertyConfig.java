package tt.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import tt.config.exceptions.ConfigException;
import tt.config.exceptions.ConfigLoadException;
import tt.config.exceptions.ConfigOpenException;

public class PropertyConfig extends AbstractConfig {

    final private String file;
    private Properties properties;

    public PropertyConfig(String path) {
        this.file = path;
    }

    @Override
    public String getDescriptor() {
        return this.file;
    }

    @Override
    final public boolean isLoaded() {
        return null != this.properties;
    }

    @Override
    final public Optional<String> get(String key) throws ConfigException {
        String value = this.properties.getProperty(key);
        if (null == value) {
            return Optional.empty();
        }
        return Optional.of(value);
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
        InputStream input = Config.class.getClassLoader().getResourceAsStream(this.file);
        if (null == input) {
            throw new ConfigOpenException(this.getDescriptor());
        }
        try {
            this.properties.load(input);
        } catch (IOException e) {
            throw new ConfigLoadException(this.getDescriptor(), e);
        }
    }

    @Override
    public Set<String> keys() throws ConfigException {
        this.load();
        return this.properties.stringPropertyNames();
    }
}
