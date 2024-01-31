package tt.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import tt.config.exceptions.ConversionException;
import tt.config.exceptions.NoPropertyException;

public class PropertyConfigTest {

    private static final Config config = new PropertyConfig("tt/config/testing.properties");
    
    @Test
    void stringProperty() throws Exception {
        var actual = config.getString("field_str");
        var expected = Optional.of("Hello, world!");
        assertEquals(expected, actual);
    }

    @Test
    void intProperty() throws Exception {
        var actual = config.getInteger("field_int");
        var expected = Optional.of(42);
        assertEquals(expected, actual);
    }

    @Test
    void boolProperty() throws Exception {
        var actual = config.getBoolean("field_bool");
        var expected = Optional.of(true);
        assertEquals(expected, actual);
    }

    @Test
    void stringEmptyProperty() throws Exception {
        var actual = config.getString("field_empty");
        var expected = Optional.of("");
        assertEquals(expected, actual);
    }

    @Test
    void intEmptyProperty() throws Exception {
        assertThrows(ConversionException.class, () -> {
            config.getInteger("field_empty");
        });
    }

    @Test
    void boolEmptyProperty() throws Exception {
        assertThrows(ConversionException.class, () -> {
            config.getBoolean("field_empty");
        });
    }

    @Test
    void missingProperty() throws Exception {
        var actual = config.get("sir_not_appearing_in_this_movie");        
        var expected = Optional.empty();
        assertEquals(expected, actual);
    }

    @Test
    void stringPropertyOrFail() throws Exception {
        var actual = config.getStringOrFail("field_str");
        var expected = "Hello, world!";
        assertEquals(expected, actual);
    }

    @Test
    void intPropertyOrFail() throws Exception {
        var actual = config.getIntegerOrFail("field_int");
        var expected = 42;
        assertEquals(expected, actual);
    }

    @Test
    void boolPropertyOrFail() throws Exception {
        var actual = config.getBooleanOrFail("field_bool");
        var expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void stringEmptyPropertyOrFail() throws Exception {
        var actual = config.getStringOrFail("field_empty");
        var expected = "";
        assertEquals(expected, actual);
    }

    @Test
    void intEmptyPropertyOrFail() throws Exception {
        assertThrows(ConversionException.class, () -> {
            config.getIntegerOrFail("field_empty");
        });
    }

    @Test
    void boolEmptyPropertyOrFail() throws Exception {
        assertThrows(ConversionException.class, () -> {
            config.getBooleanOrFail("field_empty");
        });
    }

    @Test
    void missingPropertyOrFail() throws Exception {
        assertThrows(NoPropertyException.class, () -> {
            config.getOrFail("sir_not_appearing_in_this_movie");        
        });
    }
}
