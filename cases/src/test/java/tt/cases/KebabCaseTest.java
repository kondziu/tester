package tt.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import tt.cases.CaseAwareString.Case;

class KebabCaseTest {

    @Test
    void fromKebabCase() {
        var actual = CaseAwareString.from(Case.Kebab, "a-be-ce-d");
        var expected = new CaseAwareString(new String[] { "a", "be", "ce", "d" });
        assertEquals(expected, actual);
    }

    @Test
    void fromKebabCaseNone() {
        var actual = CaseAwareString.from(Case.Kebab, "");
        var expected = new CaseAwareString(new String[] { });
        assertEquals(expected, actual);
    }


    @Test
    void fromKebabCaseOne() {
        var actual = CaseAwareString.from(Case.Kebab, "aaa");
        var expected = new CaseAwareString(new String[] { "aaa" });
        assertEquals(expected, actual);
    }

    @Test
    void fromKebabCaseTwo() {
        var actual = CaseAwareString.from(Case.Kebab, "aaa-bbb");
        var expected = new CaseAwareString(new String[] { "aaa", "bbb" });
        assertEquals(expected, actual);
    }

    @Test
    void fromKebabCaseGap() {
        var actual = CaseAwareString.from(Case.Kebab, "aaa--bbb");
        var expected = new CaseAwareString(new String[] { "aaa", "", "bbb" });
        assertEquals(expected, actual);
    }

    @Test
    void fromKebabCaseGapLeft() {
        var actual = CaseAwareString.from(Case.Kebab, "-aaa-bbb");
        var expected = new CaseAwareString(new String[] { "", "aaa", "bbb" });
        assertEquals(expected, actual);
    }

    @Test
    void fromKebabCaseGapRight() {
        var actual = CaseAwareString.from(Case.Kebab, "aaa-bbb-");
        var expected = new CaseAwareString(new String[] { "aaa", "bbb", "" });
        assertEquals(expected, actual);
    }

    @Test
    void intoKebabCaseCapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "ce", "D" });
        var actual = cas.into(Case.Kebab, true);
        var expected = "A-Be-Ce-D";
        assertEquals(expected, actual);
    }

    @Test
    void intoKebabCaseDecapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "", "ce", "D" });
        var actual = cas.into(Case.Kebab, false);
        var expected = "a-be--ce-d";
        assertEquals(expected, actual);
    }

    @Test
    void intoKebabCaseGapCapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "", "ce", "D" });
        var actual = cas.into(Case.Kebab, true);
        var expected = "A-Be--Ce-D";
        assertEquals(expected, actual);
    }

    @Test
    void intoKebabCaseGapDecapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "", "ce", "D" });
        var actual = cas.into(Case.Kebab, false);
        var expected = "a-be--ce-d";
        assertEquals(expected, actual);
    }

    @Test
    void intoKebabCaseGapsCapitalized() {
        var cas = new CaseAwareString(new String[] { "", "", "" });
        var actual = cas.into(Case.Kebab, true);
        var expected = "--";
        assertEquals(expected, actual);
    }

    @Test
    void intoKebabCaseGapsDecapitalized() {
        var cas = new CaseAwareString(new String[] { "", "", "" });
        var actual = cas.into(Case.Kebab, false);
        var expected = "--";
        assertEquals(expected, actual);
    }

    @Test
    void intoKebabCaseEmptyCapitalized() {
        var cas = new CaseAwareString(new String[] {});
        var actual = cas.into(Case.Kebab, true);
        var expected = "";
        assertEquals(expected, actual);
    }

    @Test
    void intoKebabCaseEmptyDecapitalized() {
        var cas = new CaseAwareString(new String[] {});
        var actual = cas.into(Case.Kebab, false);
        var expected = "";
        assertEquals(expected, actual);
    }
}

