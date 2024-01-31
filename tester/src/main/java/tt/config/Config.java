package tt.config;

import java.util.Optional;
import java.util.Set;

import tt.config.annotations.Converter;
import tt.config.exceptions.ConfigException;

public interface Config {
    @FunctionalInterface
    public interface ValueGetter<T> {
        Optional<T> apply(Config config, String property) throws ConfigException;
    }

    boolean isLoaded() throws ConfigException;
    void load() throws ConfigException;
    Optional<String> get(String key) throws ConfigException;
    Optional<String> getString(String key) throws ConfigException;
    Optional<Boolean> getBoolean(String key) throws ConfigException;
    Optional<Integer> getInteger(String key) throws ConfigException;
    <T> Optional<T> getAndConvert(String key, Converter<String, Optional<T>> converter) throws ConfigException;
    String getOrFail(String key) throws ConfigException;
    String getStringOrFail(String key) throws ConfigException;
    Boolean getBooleanOrFail(String key) throws ConfigException;
    Integer getIntegerOrFail(String key) throws ConfigException;
    <T> T getAndConvertOrFail(String key, Converter<String, Optional<T>> converter) throws ConfigException;
    Set<String> keys() throws ConfigException;
    String getDescriptor();
}
