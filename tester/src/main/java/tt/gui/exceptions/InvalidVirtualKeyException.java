package tt.gui.exceptions;

import tt.config.exceptions.ConfigException;

public class InvalidVirtualKeyException extends ConfigException {

    private static final String MESSAGE_FORMAT = "Cannot convert string '%s' into a virtual key code. See: https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/event/KeyEvent.html";

    public InvalidVirtualKeyException(String code) {
        super(String.format(MESSAGE_FORMAT, code));
    }

}
