package tt.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import tt.cases.CaseAwareString.Case;

class SnakeCaseTest {

    @Test
    void fromSnakeCase() {
        var actual = CaseAwareString.from(Case.Snake, "a_be_ce_d");
        var expected = new CaseAwareString(new String[] { "a", "be", "ce", "d" });
        assertEquals(expected, actual);
    }

    @Test
    void fromSnakeCaseNone() {
        var actual = CaseAwareString.from(Case.Snake, "");
        var expected = new CaseAwareString(new String[] { });
        assertEquals(expected, actual);
    }


    @Test
    void fromSnakeCaseOne() {
        var actual = CaseAwareString.from(Case.Snake, "aaa");
        var expected = new CaseAwareString(new String[] { "aaa" });
        assertEquals(expected, actual);
    }

    @Test
    void fromSnakeCaseTwo() {
        var actual = CaseAwareString.from(Case.Snake, "aaa_bbb");
        var expected = new CaseAwareString(new String[] { "aaa", "bbb" });
        assertEquals(expected, actual);
    }

    @Test
    void fromSnakeCaseGap() {
        var actual = CaseAwareString.from(Case.Snake, "aaa__bbb");
        var expected = new CaseAwareString(new String[] { "aaa", "", "bbb" });
        assertEquals(expected, actual);
    }

    @Test
    void fromSnakeCaseGapLeft() {
        var actual = CaseAwareString.from(Case.Snake, "_aaa_bbb");
        var expected = new CaseAwareString(new String[] { "", "aaa", "bbb" });
        assertEquals(expected, actual);
    }

    @Test
    void fromSnakeCaseGapRight() {
        var actual = CaseAwareString.from(Case.Snake, "aaa_bbb_");
        var expected = new CaseAwareString(new String[] { "aaa", "bbb", "" });
        assertEquals(expected, actual);
    }

    @Test
    void intoSnakeCaseCapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "ce", "D" });
        var actual = cas.into(Case.Snake, true);
        var expected = "A_BE_CE_D";
        assertEquals(expected, actual);
    }

    @Test
    void intoSnakeCaseDecapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "ce", "D" });
        var actual = cas.into(Case.Snake, false);
        var expected = "a_be_ce_d";
        assertEquals(expected, actual);
    }

    @Test
    void intoSnakeCaseGapCapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "", "ce", "D" });
        var actual = cas.into(Case.Snake, true);
        var expected = "A_BE__CE_D";
        assertEquals(expected, actual);
    }

    @Test
    void intoSnakeCaseGapDecapitalized() {
        var cas = new CaseAwareString(new String[] { "a", "Be", "", "ce", "D" });
        var actual = cas.into(Case.Snake, false);
        var expected = "a_be__ce_d";
        assertEquals(expected, actual);
    }

    @Test
    void intoSnakeCaseGapsCapitalized() {
        var cas = new CaseAwareString(new String[] { "", "", "" });
        var actual = cas.into(Case.Snake, true);
        var expected = "__";
        assertEquals(expected, actual);
    }

    @Test
    void intoSnakeCaseGapsDecapitalized() {
        var cas = new CaseAwareString(new String[] { "", "", "" });
        var actual = cas.into(Case.Snake, false);
        var expected = "__";
        assertEquals(expected, actual);
    }

    @Test
    void intoSnakeCaseEmptyCapitalized() {
        var cas = new CaseAwareString(new String[] {});
        var actual = cas.into(Case.Snake, true);
        var expected = "";
        assertEquals(expected, actual);
    }

    @Test
    void intoSnakeCaseEmptyDecapitalized() {
        var cas = new CaseAwareString(new String[] {});
        var actual = cas.into(Case.Snake, false);
        var expected = "";
        assertEquals(expected, actual);
    }
}

