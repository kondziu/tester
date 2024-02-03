package tt.gui;

import java.util.Optional;

import javax.swing.KeyStroke;

import tt.config.annotations.Converter;
import tt.config.exceptions.ConfigException;
import tt.gui.exceptions.InvalidKeyStrokeException;

public class KeyStrokeConvereter implements Converter<String, Optional<KeyStroke>> {

    @Override
    public Optional<KeyStroke> apply(String string) throws ConfigException {
        if (string.isBlank()) {
            return Optional.empty();
        }

        var stroke = KeyStroke.getKeyStroke(string);
        if (stroke == null) {
            throw new InvalidKeyStrokeException(string);
        }
        return Optional.of(stroke);
    }
    
}
