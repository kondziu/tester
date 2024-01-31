package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;

import tt.config.annotations.From;

@From(file="tt/options/key_bindings.properties")
public class KeyBindings implements Properties {

    public KeyBindings() throws IllegalAccessException, ConfigException, AnnotationException {
        this.initializeProperties();
    }

    // Show help for current question
    @Option
    public String help;

    // Evaluate answer
    @Option
    public String ok;

    // Open test file from disk
    @Option
    public String open;

    // Open test file from internet
    @Option
    public String internet;

    // Open test file manager tool
    @Option
    public String manage;

    // Quit program
    @Option
    public String quit;

    // Start test
    @Option
    public String start;

    // Stop test
    @Option
    public String stop;

    // Set UI language
    @Option
    public String language;

    // Open configuration
    @Option
    public String configuration;

    // Reload program (to reload configuration after changes)
    @Option
    public String reload;

    // Show dictionary
    @Option
    public String dictionary;
}