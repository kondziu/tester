package tt.options;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MagicWordsTest {
    
    @Test
    void testInit() throws Exception {
        new MagicWord();
    }

    @Test
    void testAccept() throws Exception {
        MagicWord magicWord = new MagicWord();
        var expected = "@mateur$";
        assertEquals(expected, magicWord.accept); 
    }

    @Test
    void testBypass() throws Exception {
        MagicWord magicWord = new MagicWord();
        var expected = "!@#$%";
        assertEquals(expected, magicWord.bypass); 
    }
}
