package tt.config;

import tt.config.exceptions.ConfigException;

public abstract class AbstractConfig implements Config {
    private final String loadAndGet(String key) throws ConfigException {
        if (!this.isLoaded()) {
            this.load();
        }
        return this.get(key);
    }
    
    public String getStr(String key) throws ConfigException {
        return this.loadAndGet(key);
    }

    public boolean getBool(String key) throws ConfigException {
        String value = this.loadAndGet(key);
        return Boolean.valueOf(value);
    }

    public int getInt(String key) throws ConfigException {
        String value = this.loadAndGet(key);
        return Integer.valueOf(value);
    }
}
