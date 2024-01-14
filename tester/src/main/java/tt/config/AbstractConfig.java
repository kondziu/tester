package tt.config;

import java.util.Optional;

import tt.config.exceptions.ConfigException;
import tt.config.exceptions.ConversionException;
import tt.config.exceptions.NoPropertyException;

public abstract class AbstractConfig implements Config {
    private final Optional<String> loadAndGet(String key) throws ConfigException {
        if (!this.isLoaded()) {
            this.load();
        }
        return this.get(key);
    }
    
    public Optional<String> getString(String key) throws ConfigException {
        return this.loadAndGet(key);
    }

    public Optional<Boolean> getBoolean(String key) throws ConfigException {
        Optional<String> option = this.loadAndGet(key);
        if (option.isEmpty()) {
            return Optional.empty();
        }
        String initial = option.get();
        if (initial.isBlank()) {
            throw new ConversionException(this.getDescriptor(), key, option.get(), Boolean.class);
        }
        try {
            Boolean value = Boolean.valueOf(initial);
            return Optional.of(value);
        } catch (Throwable e) {
            throw new ConversionException(this.getDescriptor(), key, option.get(), Boolean.class, e);
        }
    }

    public Optional<Integer> getInteger(String key) throws ConfigException {
        Optional<String> option = this.loadAndGet(key);
        if (option.isEmpty()) {
            return Optional.empty();
        }
        String initial = option.get();
        try {
            Integer value = Integer.valueOf(initial);
            return Optional.of(value);
        } catch (Throwable e) {
            throw new ConversionException(this.getDescriptor(), key, option.get(), Integer.class, e);
        }
    }

    public String getOrFail(String key) throws ConfigException {
        var value = this.loadAndGet(key);
        if (value.isEmpty()) {
            throw new NoPropertyException(this.getDescriptor(), key);
        }
        return value.get();
    }

    public String getStringOrFail(String key) throws ConfigException {
        var value = this.getString(key);
        if (value.isEmpty()) {
            throw new NoPropertyException(this.getDescriptor(), key);
        }
        return value.get();
    }

    public Boolean getBooleanOrFail(String key) throws ConfigException {
        var value = this.getBoolean(key);
        if (value.isEmpty()) {
            throw new NoPropertyException(this.getDescriptor(), key);
        }
        return value.get();
    }

    public Integer getIntegerOrFail(String key) throws ConfigException {
        var value = this.getInteger(key);
        if (value.isEmpty()) {
            throw new NoPropertyException(this.getDescriptor(), key);
        }
        return value.get();
    }
}
