package tt.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tt.config.annotations.From;
import tt.config.annotations.Option;
import tt.config.annotations.Properties;
import tt.config.annotations.exceptions.FileNotFoundException;
import tt.config.annotations.exceptions.NotAnnotatedException;
import tt.config.exceptions.ConversionException;
import tt.config.exceptions.NoPropertyException;


public class AnnotatedPropertyTest {

    public class TestClassNotAnnotated {}

    @Test
    void testClassNotAnnotated() throws Exception {
        assertThrows(NotAnnotatedException.class, () -> {
            var obj = new TestClassNotAnnotated();
            Properties.initialize(obj);
        });
    }

    @From(file = "tt/config/.not_here.properties")
    public class TestClassWrongPath { }

    @Test
    void testClassWrongPath() throws Exception {
        assertThrows(FileNotFoundException.class, () -> {
            var obj = new TestClassWrongPath();
            Properties.initialize(obj);
        });
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassEmpty { }

    @Test
    void testClassEmpty() throws Exception {
        var obj = new TestClassEmpty();
        Properties.initialize(obj);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassStringField {
        @Option
        public String fieldStr;
    }

    @Test
    void testClassStringField() throws Exception {
        var obj = new TestClassStringField ();
        Properties.initialize(obj);
        assertEquals("Hello, world!", obj.fieldStr);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassStringRenamedField {
        @Option(property = "field_str")
        public String str;
    }

    @Test
    void testClassStringRenamedField() throws Exception {
        var obj = new TestClassStringRenamedField ();
        Properties.initialize(obj);
        assertEquals("Hello, world!", obj.str);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassIntegerField {
        @Option
        public Integer fieldInt;
    }

    @Test
    void testClassIntegerField() throws Exception {
        var obj = new TestClassIntegerField ();
        Properties.initialize(obj);
        assertEquals(42, obj.fieldInt);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassIntegerRenamedField {
        @Option(property = "field_int")
        public Integer i;
    }

    @Test
    void testClassIntegerRenamedField() throws Exception {
        var obj = new TestClassIntegerRenamedField ();
        Properties.initialize(obj);
        assertEquals(42, obj.i);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassIntegerPrimitiveField {
        @Option
        public int fieldInt;
    }

    @Test
    void testClassIntegerPrimitiveField() throws Exception {
        var obj = new TestClassIntegerPrimitiveField ();
        Properties.initialize(obj);
        assertEquals(42, obj.fieldInt);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassIntegerRenamedPrimitiveField {
        @Option(property = "field_int")
        public int i;
    }

    @Test
    void testClassIntegerRenamedPrimitiveField() throws Exception {
        var obj = new TestClassIntegerRenamedPrimitiveField ();
        Properties.initialize(obj);
        assertEquals(42, obj.i);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassBooleanField {
        @Option
        public Boolean fieldBool;
    }

    @Test
    void testClassBooleanField() throws Exception {
        var obj = new TestClassBooleanField ();
        Properties.initialize(obj);
        assertEquals(true, obj.fieldBool);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassBooleanRenamedField {
        @Option(property = "field_bool")
        public Boolean b;
    }

    @Test
    void testClassBooleanRenamedField() throws Exception {
        var obj = new TestClassBooleanRenamedField ();
        Properties.initialize(obj);
        assertEquals(true, obj.b);
    }


    @From(file = "tt/config/.testing.properties")
    public class TestClassBooleanPrimitiveField {
        @Option
        public boolean fieldBool;
    }

    @Test
    void testClassBooleanPrimitiveField() throws Exception {
        var obj = new TestClassBooleanPrimitiveField ();
        Properties.initialize(obj);
        assertEquals(true, obj.fieldBool);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassBooleanRenamedPrimitiveField {
        @Option(property = "field_bool")
        public boolean b;
    }

    @Test
    void testClassBooleanRenamedPrimitiveField() throws Exception {
        var obj = new TestClassBooleanRenamedPrimitiveField ();
        Properties.initialize(obj);
        assertEquals(true, obj.b);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassMix {
        @Option
        public Boolean fieldBool;

        @Option
        public String fieldStr;

        @Option
        public Integer fieldInt;

        @Option(property = "field_int")
        public int i;

        @Option(property = "field_bool")
        public boolean b;

        @Option(property = "field_str")
        public String s;
    }

    @Test
    void testClassMix() throws Exception {
        var obj = new TestClassMix ();
        Properties.initialize(obj);
        assertEquals(true, obj.fieldBool);
        assertEquals(42, obj.fieldInt);
        assertEquals("Hello, world!", obj.fieldStr);
        assertEquals(true, obj.b);
        assertEquals(42, obj.i);
        assertEquals("Hello, world!", obj.s);
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassWrongTypeInt {
        @Option
        public int fieldStr;
    }
    
    @Test
    void testClassWrongTypeInt() throws Exception {
        assertThrows(ConversionException.class, () -> {
            var obj = new TestClassWrongTypeInt ();
            Properties.initialize(obj);
            // Integer s = obj.fieldStr;
            // System.out.println(s);
        });
    }

    @From(file = "tt/config/.testing.properties")
    public class TestClassWrongTypeBoolean {
        @Option
        public boolean fieldStr;
    }
    
    @Test
    void testClassWrongTypeBoolean() throws Exception {
        assertThrows(ConversionException.class, () -> {
            var obj = new TestClassWrongTypeBoolean ();
            Properties.initialize(obj);
        });
    }

    // @Test
    // void missingPropertyOrFail() throws Exception {
    //     assertThrows(NoPropertyException.class, () -> {
    //         config.getOrFail("sirNotAppearingInThisMovie");        
    //     });
    // }
    @From(file = "tt/config/.testing.properties")
    public class TestClassMissingProperty {
        @Option(property = "fieldStr")
        public String nope;
    }
    

    @Test
    void testClassMissingProperty() throws Exception {
        assertThrows(NoPropertyException.class, () -> {
            var obj = new TestClassMissingProperty ();
            Properties.initialize(obj);
        });
    }
}
