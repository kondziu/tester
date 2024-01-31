package tt.config;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import tt.config.converters.Converter;
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

    private static final Set<String> BOOLEAN_TRUE = new HashSet<>();
    private static final Set<String> BOOLEAN_FALSE = new HashSet<>();

    static {
        BOOLEAN_TRUE.add("true");
        BOOLEAN_TRUE.add("t");
        BOOLEAN_TRUE.add("yes");
        BOOLEAN_TRUE.add("y");
        BOOLEAN_TRUE.add("on");

        BOOLEAN_FALSE.add("false");
        BOOLEAN_FALSE.add("f");
        BOOLEAN_FALSE.add("no");
        BOOLEAN_FALSE.add("n");
        BOOLEAN_FALSE.add("off");
    }

    public Optional<Boolean> getBoolean(String key) throws ConfigException {
        Optional<String> option = this.loadAndGet(key);
        if (option.isEmpty()) {
            return Optional.empty();
        }

        String initial = option.get().toLowerCase();
        boolean checkTrue = AbstractConfig.BOOLEAN_TRUE.contains(initial);
        boolean checkFalse = AbstractConfig.BOOLEAN_FALSE.contains(initial);

        assert(!(checkFalse && checkTrue));
        if (checkTrue) {
            return Optional.of(true);
        }
        if (checkFalse) {
            return Optional.of(false);
        }

        throw new ConversionException(this.getDescriptor(), key, option.get(), Boolean.class);
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

    public <T> Optional<T> getAndConvert(String key, Converter<String, Optional<T>> converter) throws ConfigException {
        Optional<String> value = this.loadAndGet(key);
        if (value.isEmpty()) {
            return Optional.empty();
        } 
        String string = value.get();
        assert(string != null);
        return converter.apply(string);
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

    public <T> T getAndConvertOrFail(String key, Converter<String, Optional<T>> converter) throws ConfigException {
        Optional<T> value = this.getAndConvert(key, converter);
        if (value.isEmpty()) {
            throw new NoPropertyException(this.getDescriptor(), key);
        }
        return value.get();
    }


}
