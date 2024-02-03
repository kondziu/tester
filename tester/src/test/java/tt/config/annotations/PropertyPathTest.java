package tt.config.annotations;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PropertyPathTest {

    public class TestClassNotAnnotated implements Properties {}

    @Test
    void testTop() {
        var actual = PropertyPath.top();
        assertTrue(actual.equals(""));
        assertArrayEquals(new String[]{}, actual.toArray());
    }

    @Test
    void testStringPath0() {
        var actual = PropertyPath.from("");
        assertTrue(actual.equals(""));
        assertArrayEquals(new String[]{""}, actual.toArray());
        assertEquals(PropertyPath.top(), actual);
    }

    @Test
    void testStringPath1() {
        var actual = PropertyPath.from("a");
        assertTrue(actual.equals("a"));
        assertArrayEquals(new String[]{"a"}, actual.toArray());
    }

    @Test
    void testStringPath2() {
        var actual = PropertyPath.from("a.b");
        assertTrue(actual.equals("a.b"));
        assertArrayEquals(new String[]{"a", "b"}, actual.toArray());
    }

    @Test
    void testStringPath3() {
        var actual = PropertyPath.from("a.b.c");
        assertTrue(actual.equals("a.b.c"));
        assertArrayEquals(new String[]{"a", "b", "c"}, actual.toArray());
    }

    @Test
    void testStringPathDots() {
        var actual = PropertyPath.from("..a..");
        assertTrue(actual.equals("..a.."));
        assertArrayEquals(new String[]{"", "", "a", "", ""}, actual.toArray());
    }

    @Test
    void testArrayPath0(){
        var actual = PropertyPath.from(new String[]{""});
        assertTrue(actual.equals(""));
        assertArrayEquals(new String[]{""}, actual.toArray());
        assertEquals(PropertyPath.top(), actual);
    }

    @Test
    void testArrayPath1() {
        var actual = PropertyPath.from(new String[]{"a"});
        assertTrue(actual.equals("a"));
        assertArrayEquals(new String[]{"a"}, actual.toArray());
    }

    @Test
    void testArrayPath2() {
        var actual = PropertyPath.from(new String[]{"a", "b"});
        assertTrue(actual.equals("a.b"));
        assertArrayEquals(new String[]{"a", "b"}, actual.toArray());
    }

    @Test
    void testArrayPath3() {
        var actual = PropertyPath.from(new String[]{"a", "b", "c"});
        assertTrue(actual.equals("a.b.c"));
        assertArrayEquals(new String[]{"a", "b", "c"}, actual.toArray());
    }

    @Test
    void testArrayPathDots() {
        var actual = PropertyPath.from(new String[]{"", "", "a", "", ""});
        assertTrue(actual.equals("..a.."));
        assertArrayEquals(new String[]{"", "", "a", "", ""}, actual.toArray());
    }

    @Test 
    void testConversion() {
        var path = PropertyPath.from(new String[]{"a", "b", "c", "d", "e"});
        var actual = path.convertEach(s -> "<" + s + ">");
        assertArrayEquals(new String[]{"<a>", "<b>", "<c>", "<d>", "<e>"}, actual.toArray());
    }

    @Test
    void testConcat() {
        var path = PropertyPath.from("a");
        var actual = path.concat("b");
        assertArrayEquals(new String[]{"a", "b"}, actual.toArray());
    }

    @Test
    void testConcatTop() {
        var path = PropertyPath.top();
        var actual = path.concat("a");
        assertArrayEquals(new String[]{"a"}, actual.toArray());
    }
}
