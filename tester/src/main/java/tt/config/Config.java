package tt.config;

import java.io.IOException;
import java.util.Set;

import tt.config.exceptions.ConfigException;

public interface Config {
    boolean isLoaded() throws ConfigException;
    void load() throws ConfigException;
    String get(String key) throws ConfigException;
    String getStr(String key) throws ConfigException;
    boolean getBool(String key) throws ConfigException;
    int getInt(String key) throws ConfigException;
    Set<String> keys() throws ConfigException;
}
