package tt.options.gui;

import java.util.Optional;
import java.util.UUID;

import javax.swing.KeyStroke;

import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.gui.KeyStrokeConvereter;
import tt.gui.VirtualKeyConvereter;

public class Element implements Properties {
    @Option
    public String label;

    @Option
    public String description;

    @Option(converter = KeyStrokeConvereter.class)
    public Optional<KeyStroke> key;

    @Option(converter = VirtualKeyConvereter.class)
    public Optional<Integer> mnemonic;

    // Memoized unique label.
    public String uniqueLabel;

    // Generates a unique label which is then memoized.
    public String actionLabel() {
        if (uniqueLabel==null) {
            this.uniqueLabel = this.label + "Action@" + UUID.randomUUID();
        }
        return uniqueLabel;
    }
}
