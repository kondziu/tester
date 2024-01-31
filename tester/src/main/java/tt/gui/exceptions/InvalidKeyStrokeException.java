package tt.gui.exceptions;

import tt.config.exceptions.ConfigException;

public class InvalidKeyStrokeException extends ConfigException {

    private static final String MESSAGE_FORMAT = "Cannot convert string '%s' into a keystroke. See: https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/javax/swing/KeyStroke.html#getKeyStroke(java.lang.String)";

    public InvalidKeyStrokeException(String stroke) {
        super(String.format(MESSAGE_FORMAT, stroke));
    }

}
