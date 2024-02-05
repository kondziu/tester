package tt.options.gui;

import java.util.Optional;

import javax.swing.KeyStroke;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.gui.KeyStrokeConvereter;
import tt.gui.VirtualKeyConvereter;

public class AbstractElement implements Properties {
    @Option
    public String label;

    @Option
    public String description;

    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> key;

    @Option(converter = VirtualKeyConvereter.class)
    public Optional<Integer> mnemonic;
}
