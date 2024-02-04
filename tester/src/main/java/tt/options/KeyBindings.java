package tt.options;

import tt.config.annotations.Option;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;
import tt.gui.KeyStrokeConvereter;

import java.util.Optional;

import javax.swing.KeyStroke;

import tt.config.annotations.AbstractProperties;
import tt.config.annotations.From;

@From(file="tt/options/key_bindings.properties")
public class KeyBindings extends AbstractProperties {

    public KeyBindings() throws ConfigException, AnnotationException {
        super();
    }

    // Show help for current question
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> help;

    // Evaluate answer
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> ok;

    // Open test file from disk
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> open;

    // Open test file from internet
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> internet;

    // Open test file manager tool
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> manage;

    // Quit program
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> quit;

    // Start test
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> start;

    // Stop test
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> stop;

    // Set UI language
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> language;

    // Open configuration
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> configuration;

    // Reload program (to reload configuration after changes)
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> reload;

    // Show dictionary
    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> dictionary;
}