package tt.options.gui;

import java.util.Optional;

import javax.swing.KeyStroke;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.gui.KeyStrokeConvereter;

public class Element implements Properties {
    @Option
    public String label;

    @Option
    public String description;

    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> key;

    @Option
    public String mnemonic;

    public String actionLabel() {
        return this.label + "Action"; // TODO guarantee uniqueness
    }
}
