package tt.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tt.config.annotations.From;
import tt.config.annotations.Properties;
import tt.config.annotations.Option;
import tt.config.annotations.exceptions.FileNotFoundException;
import tt.config.annotations.exceptions.NotAnnotatedException;
import tt.config.exceptions.ConversionException;
import tt.config.exceptions.NoPropertyException;
import tt.config.helpers.DoubleIdentity;
import tt.config.helpers.IntegerIdentity;
import tt.config.helpers.StringToUppercase;

public class AnnotatedPropertyTest {

    public class TestClassNotAnnotated implements Properties {}

    @Test
    void testClassNotAnnotated() throws Exception {
        assertThrows(NotAnnotatedException.class, () -> {
            var obj = new TestClassNotAnnotated();
            obj.initializeProperties();
        });
    }

    @From(file = "tt/config/.not_here.properties")
    public class TestClassWrongPath implements Properties { }

    @Test
    void testClassWrongPath() throws Exception {
        assertThrows(FileNotFoundException.class, () -> {
            var obj = new TestClassWrongPath();
            obj.initializeProperties();
        });
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassEmpty implements Properties { }

    @Test
    void testClassEmpty() throws Exception {
        var obj = new TestClassEmpty();
        obj.initializeProperties();
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassStringField implements Properties {
        @Option
        public String fieldStr;
    }

    @Test
    void testClassStringField() throws Exception {
        var obj = new TestClassStringField ();
        obj.initializeProperties();
        assertEquals("Hello, world!", obj.fieldStr);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassStringRenamedField implements Properties {
        @Option(property = "field_str")
        public String str;
    }

    @Test
    void testClassStringRenamedField() throws Exception {
        var obj = new TestClassStringRenamedField ();
        obj.initializeProperties();
        assertEquals("Hello, world!", obj.str);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassIntegerField implements Properties {
        @Option
        public Integer fieldInt;
    }

    @Test
    void testClassIntegerField() throws Exception {
        var obj = new TestClassIntegerField ();
        obj.initializeProperties();
        assertEquals(42, obj.fieldInt);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassIntegerRenamedField implements Properties {
        @Option(property = "field_int")
        public Integer i;
    }

    @Test
    void testClassIntegerRenamedField() throws Exception {
        var obj = new TestClassIntegerRenamedField ();
        obj.initializeProperties();
        assertEquals(42, obj.i);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassIntegerPrimitiveField implements Properties {
        @Option
        public int fieldInt;
    }

    @Test
    void testClassIntegerPrimitiveField() throws Exception {
        var obj = new TestClassIntegerPrimitiveField ();
        obj.initializeProperties();
        assertEquals(42, obj.fieldInt);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassIntegerRenamedPrimitiveField implements Properties {
        @Option(property = "field_int")
        public int i;
    }

    @Test
    void testClassIntegerRenamedPrimitiveField() throws Exception {
        var obj = new TestClassIntegerRenamedPrimitiveField ();
        obj.initializeProperties();
        assertEquals(42, obj.i);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassBooleanField implements Properties {
        @Option
        public Boolean fieldBool;
    }

    @Test
    void testClassBooleanField() throws Exception {
        var obj = new TestClassBooleanField ();
        obj.initializeProperties();
        assertEquals(true, obj.fieldBool);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassBooleanRenamedField implements Properties {
        @Option(property = "field_bool")
        public Boolean b;
    }

    @Test
    void testClassBooleanRenamedField() throws Exception {
        var obj = new TestClassBooleanRenamedField ();
        obj.initializeProperties();
        assertEquals(true, obj.b);
    }


    @From(file = "tt/config/testing.properties")
    public class TestClassBooleanPrimitiveField implements Properties {
        @Option
        public boolean fieldBool;
    }

    @Test
    void testClassBooleanPrimitiveField() throws Exception {
        var obj = new TestClassBooleanPrimitiveField ();
        obj.initializeProperties();
        assertEquals(true, obj.fieldBool);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassBooleanRenamedPrimitiveField implements Properties {
        @Option(property = "field_bool")
        public boolean b;
    }

    @Test
    void testClassBooleanRenamedPrimitiveField() throws Exception {
        var obj = new TestClassBooleanRenamedPrimitiveField ();
        obj.initializeProperties();
        assertEquals(true, obj.b);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassMix implements Properties {
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
        obj.initializeProperties();
        assertEquals(true, obj.fieldBool);
        assertEquals(42, obj.fieldInt);
        assertEquals("Hello, world!", obj.fieldStr);
        assertEquals(true, obj.b);
        assertEquals(42, obj.i);
        assertEquals("Hello, world!", obj.s);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassWrongTypeInt implements Properties {
        @Option
        public int fieldStr;
    }
    
    @Test
    void testClassWrongTypeInt() throws Exception {
        assertThrows(ConversionException.class, () -> {
            var obj = new TestClassWrongTypeInt ();
            obj.initializeProperties();
        });
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassWrongTypeBoolean implements Properties {
        @Option
        public boolean fieldStr;
    }
    
    @Test
    void testClassWrongTypeBoolean() throws Exception {
        assertThrows(ConversionException.class, () -> {
            var obj = new TestClassWrongTypeBoolean ();
            obj.initializeProperties();
        });
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassMissingProperty implements Properties {
        @Option(property = "fieldStr")
        public String nope;
    }
    

    @Test
    void testClassMissingProperty() throws Exception {
        assertThrows(NoPropertyException.class, () -> {
            var obj = new TestClassMissingProperty ();
            obj.initializeProperties();
        });
    }  

    @From(file = "tt/config/testing.properties")
    public class TestClassCustomStringToString implements Properties {
        @Option(converter = StringToUppercase.class)
        public String fieldStr;
    }

    @Test
    void testClassCustomStringToString() throws Exception {
        var obj = new TestClassCustomStringToString ();
        obj.initializeProperties();
        assertEquals("HELLO, WORLD!", obj.fieldStr);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassCustomStringToDouble implements Properties {
        @Option(converter = DoubleIdentity.class)
        public Double fieldFp;
    }

    @Test
    void testClassCustomStringToDouble() throws Exception {
        var obj = new TestClassCustomStringToDouble ();
        obj.initializeProperties();
        assertEquals(3.14159, obj.fieldFp);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassCustomStringToInteger implements Properties {
        @Option(converter = IntegerIdentity.class)
        public Integer fieldInt;
    }

    @Test
    void testClassCustomStringToInteger() throws Exception {
        var obj = new TestClassCustomStringToInteger ();
        obj.initializeProperties();
        assertEquals(42, obj.fieldInt);
    }
}
