package tt.gui;

import java.awt.event.KeyEvent;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tt.config.annotations.Converter;
import tt.config.exceptions.ConfigException;
import tt.gui.exceptions.InvalidKeyStrokeException;

public class VirtualKeyConvereter implements Converter<String, Optional<Integer>> {

    static private Map<String, Integer> virtualKeyCodes = 
    Stream.of(KeyEvent.class.getDeclaredFields()).filter(
        (field) -> Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())
    ).collect(Collectors.toMap(
        (field) -> field.getName(), 
        (field) -> {
            try {
                return field.getInt(null);
            } catch (Exception e) {
                throw new RuntimeException(); // TODO
            }
        }
    ));

    @Override
    public Optional<Integer> apply(String string) throws ConfigException {
        if (string.isBlank()) {
            return Optional.empty();
        }

        String virtualKeyCode = "VK_" + string.toUpperCase();
        Integer code = virtualKeyCodes.get(virtualKeyCode);
        if (code == null) {
            throw new InvalidKeyStrokeException(virtualKeyCode);
        }
        return Optional.of(code);
    }
    
}
