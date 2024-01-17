package tt.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import tt.cases.CaseAwareString.Case;

class CamelCaseTest {

    @Test
    void fromCamelCase() {
        var actual = CaseAwareString.from(Case.Camel, "aBeCeDE");
        var expected = new CaseAwareString(new String[] { "a", "Be", "Ce", "D", "E" });
        assertEquals(expected, actual);
    }

    @Test
    void fromCamelCaseNone() {
        var actual = CaseAwareString.from(Case.Camel, "");
        var expected = new CaseAwareString(new String[] { });
        assertEquals(expected, actual);
    }


    @Test
    void fromCamelCaseOne() {
        var actual = CaseAwareString.from(Case.Camel, "aaa");
        var expected = new CaseAwareString(new String[] { "aaa" });
        assertEquals(expected, actual);
    }

    @Test
    void fromCamelCaseOneUppercase() {
        var actual = CaseAwareString.from(Case.Camel, "Aaa");
        var expected = new CaseAwareString(new String[] { "Aaa" });
        assertEquals(expected, actual);
    }

    @Test
    void fromCamelCaseTwo() {
        var actual = CaseAwareString.from(Case.Camel, "aaaBbb");
        var expected = new CaseAwareString(new String[] { "aaa", "Bbb" });
        assertEquals(expected, actual);
    }

    @Test
    void intoCamelCaseCapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "ce", "D", "e" });
        var actual = cas.into(Case.Camel, true);
        var expected = "ABeCeDE";
        assertEquals(expected, actual);
    }

    @Test
    void intoCamelCaseDecapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "ce", "D", "e" });
        var actual = cas.into(Case.Camel, false);
        var expected = "aBeCeDE";
        assertEquals(expected, actual);
    }

    @Test
    void intoCamelCaseGapCapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "", "ce", "D" });
        var actual = cas.into(Case.Camel, true);
        var expected = "ABeCeD";
        assertEquals(expected, actual);
    }

    @Test
    void intoCamelCaseGapDecapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "", "ce", "D" });
        var actual = cas.into(Case.Camel, false);
        var expected = "aBeCeD";
        assertEquals(expected, actual);
    }

    @Test
    void intoCamelCaseGapsCapitalized() {
        var cas = new CaseAwareString(new String[] { "", "", "" });
        var actual = cas.into(Case.Camel, true);
        var expected = "";
        assertEquals(expected, actual);
    }

    @Test
    void intoCamelCaseGapsDecapitalized() {
        var cas = new CaseAwareString(new String[] { "", "", "" });
        var actual = cas.into(Case.Camel, false);
        var expected = "";
        assertEquals(expected, actual);
    }

    @Test
    void intoCamelCaseEmptyCapitalized() {
        var cas = new CaseAwareString(new String[] {});
        var actual = cas.into(Case.Camel, true);
        var expected = "";
        assertEquals(expected, actual);
    }

    @Test
    void intoCamelCaseEmptyDecapitalized() {
        var cas = new CaseAwareString(new String[] {});
        var actual = cas.into(Case.Camel, false);
        var expected = "";
        assertEquals(expected, actual);
    }
}

