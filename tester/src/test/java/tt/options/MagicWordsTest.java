package tt.options;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MagicWordsTest {
    
    @Test
    void testInit() throws Exception {
        new MagicWords();
    }

    @Test
    void testAccept() throws Exception {
        MagicWords magicWord = new MagicWords();
        var expected = "@mateur$";
        assertEquals(expected, magicWord.accept); 
    }

    @Test
    void testBypass() throws Exception {
        MagicWords magicWord = new MagicWords();
        var expected = "!@#$%";
        assertEquals(expected, magicWord.bypass); 
    }
}
