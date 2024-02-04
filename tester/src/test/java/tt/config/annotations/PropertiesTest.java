package tt.config.annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tt.config.annotations.exceptions.AnnotationException;
import tt.config.annotations.exceptions.CannotConvertIntoTypeException;
import tt.config.annotations.exceptions.FileNotFoundException;
import tt.config.annotations.exceptions.NotAConverterException;
import tt.config.annotations.exceptions.NotAnnotatedException;
import tt.config.exceptions.ConfigException;
import tt.config.exceptions.ConversionException;
import tt.config.exceptions.NoPropertyException;
import tt.config.helpers.DoubleIdentity;
import tt.config.helpers.IntegerIdentity;
import tt.config.helpers.StringToUppercase;

public class PropertiesTest {

    public class TestClassNotAnnotated implements Properties {
        public TestClassNotAnnotated() throws ConfigException, AnnotationException {
            this.initialize();
        }
    }

    @Test
    void testClassNotAnnotated() throws Exception {
        assertThrows(NotAnnotatedException.class, () -> {
            new TestClassNotAnnotated();
        });
    }

    @From(file = "tt/config/.not_here.properties")
    public class TestClassWrongPath implements Properties {
        public TestClassWrongPath() throws ConfigException, AnnotationException {
            this.initialize();
        }
    }

    @Test
    void testClassWrongPath() throws Exception {
        assertThrows(FileNotFoundException.class, () -> {
            new TestClassWrongPath();
        });
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassEmpty implements Properties {
        public TestClassEmpty() throws ConfigException, AnnotationException {
            this.initialize();
        }
    }

    @Test
    void testClassEmpty() throws Exception {
        new TestClassEmpty();
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassStringField implements Properties {
        public TestClassStringField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option
        public String fieldStr;
    }

    @Test
    void testClassStringField() throws Exception {
        var obj = new TestClassStringField ();
        assertEquals("Hello, world!", obj.fieldStr);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassStringRenamedField implements Properties {
        public TestClassStringRenamedField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(property = "field_str")
        public String str;
    }

    @Test
    void testClassStringRenamedField() throws Exception {
        var obj = new TestClassStringRenamedField ();
        assertEquals("Hello, world!", obj.str);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassIntegerField implements Properties {
        public TestClassIntegerField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option
        public Integer fieldInt;
    }

    @Test
    void testClassIntegerField() throws Exception {
        var obj = new TestClassIntegerField ();
        assertEquals(42, obj.fieldInt);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassIntegerRenamedField implements Properties {
        public TestClassIntegerRenamedField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(property = "field_int")
        public Integer i;
    }

    @Test
    void testClassIntegerRenamedField() throws Exception {
        var obj = new TestClassIntegerRenamedField ();
        assertEquals(42, obj.i);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassIntegerPrimitiveField implements Properties {
        public TestClassIntegerPrimitiveField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option
        public int fieldInt;
    }

    @Test
    void testClassIntegerPrimitiveField() throws Exception {
        var obj = new TestClassIntegerPrimitiveField ();
        assertEquals(42, obj.fieldInt);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassIntegerRenamedPrimitiveField implements Properties {
        public TestClassIntegerRenamedPrimitiveField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(property = "field_int")
        public int i;
    }

    @Test
    void testClassIntegerRenamedPrimitiveField() throws Exception {
        var obj = new TestClassIntegerRenamedPrimitiveField ();
        assertEquals(42, obj.i);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassBooleanField implements Properties {
        public TestClassBooleanField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option
        public Boolean fieldBool;
    }

    @Test
    void testClassBooleanField() throws Exception {
        var obj = new TestClassBooleanField ();
        assertEquals(true, obj.fieldBool);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassBooleanRenamedField implements Properties {
        public TestClassBooleanRenamedField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(property = "field_bool")
        public Boolean b;
    }

    @Test
    void testClassBooleanRenamedField() throws Exception {
        var obj = new TestClassBooleanRenamedField ();
        assertEquals(true, obj.b);
    }


    @From(file = "tt/config/testing.properties")
    public class TestClassBooleanPrimitiveField implements Properties {
        public TestClassBooleanPrimitiveField() throws ConfigException, AnnotationException {
            this.initialize();
        }
        
        @Option
        public boolean fieldBool;
    }

    @Test
    void testClassBooleanPrimitiveField() throws Exception {
        var obj = new TestClassBooleanPrimitiveField ();
        assertEquals(true, obj.fieldBool);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassBooleanRenamedPrimitiveField implements Properties {
        public TestClassBooleanRenamedPrimitiveField() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(property = "field_bool")
        public boolean b;
    }

    @Test
    void testClassBooleanRenamedPrimitiveField() throws Exception {
        var obj = new TestClassBooleanRenamedPrimitiveField ();
        assertEquals(true, obj.b);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassMix implements Properties {
        public TestClassMix() throws ConfigException, AnnotationException {
            this.initialize();
        }

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
        assertEquals(true, obj.fieldBool);
        assertEquals(42, obj.fieldInt);
        assertEquals("Hello, world!", obj.fieldStr);
        assertEquals(true, obj.b);
        assertEquals(42, obj.i);
        assertEquals("Hello, world!", obj.s);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassWrongTypeInt implements Properties {
        public TestClassWrongTypeInt() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option
        public int fieldStr;
    }
    
    @Test
    void testClassWrongTypeInt() throws Exception {
        assertThrows(ConversionException.class, () -> {
            new TestClassWrongTypeInt ();
        });
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassWrongTypeBoolean implements Properties {
        public TestClassWrongTypeBoolean() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option
        public boolean fieldStr;
    }
    
    @Test
    void testClassWrongTypeBoolean() throws Exception {
        assertThrows(ConversionException.class, () -> {
            new TestClassWrongTypeBoolean ();
        });
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassMissingProperty implements Properties {
        public TestClassMissingProperty() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(property = "fieldStr")
        public String nope;
    }

    @Test
    void testClassMissingProperty() throws Exception {
        assertThrows(NoPropertyException.class, () -> {
            new TestClassMissingProperty ();
        });
    }  

    @From(file = "tt/config/testing.properties")
    public class TestClassCustomStringToString implements Properties {
        public TestClassCustomStringToString() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(converter = StringToUppercase.class)
        public String fieldStr;
    }

    @Test
    void testClassCustomStringToString() throws Exception {
        var obj = new TestClassCustomStringToString ();
        assertEquals("HELLO, WORLD!", obj.fieldStr);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassCustomStringToDouble implements Properties {
        public TestClassCustomStringToDouble() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(converter = DoubleIdentity.class)
        public Double fieldFp;
    }

    @Test
    void testClassCustomStringToDouble() throws Exception {
        var obj = new TestClassCustomStringToDouble ();
        assertEquals(3.14159, obj.fieldFp);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassCustomStringToInteger implements Properties {
        public TestClassCustomStringToInteger() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(converter = IntegerIdentity.class)
        public Integer fieldInt;
    }

    @Test
    void testClassCustomStringToInteger() throws Exception {
        var obj = new TestClassCustomStringToInteger ();
        assertEquals(42, obj.fieldInt);
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassCustomStringToDoubleButInteger implements Properties {
        public TestClassCustomStringToDoubleButInteger() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(converter = IntegerIdentity.class)
        public Double fieldInt;
    }

    @Test
    void testClassCustomStringToDoubleButInteger() throws Exception {
        assertThrows(CannotConvertIntoTypeException.class, () -> {
            new TestClassCustomStringToDoubleButInteger ();
        });
    }

    @From(file = "tt/config/testing.properties")
    public class TestClassCustomWrongConverter implements Properties {
        public TestClassCustomWrongConverter() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option(converter = Double.class)
        public Double fieldFp;
    }

    @Test
    void testClassCustomWrongConverter () throws Exception {
        assertThrows(NotAConverterException.class, () -> {
            new TestClassCustomWrongConverter  ();
        });
    }

    @From(file = "tt/config/testing.properties")
    public class TestNesting implements Properties {
        public TestNesting() throws ConfigException, AnnotationException {
            this.initialize();
        }

        @Option
        public TestNested testNested;
    }

    public static class TestNested implements Properties {
        @Option
        public String str;

        @Option(property = "int")
        public int i;

        @Option(converter = StringToUppercase.class)
        public String conv;
    }

    @Test
    void testNesting () throws Exception {
        var obj = new TestNesting();
        assertEquals("Hello!", obj.testNested.str);
        assertEquals(111, obj.testNested.i);
        assertEquals("HELLO", obj.testNested.conv);
    }
}
