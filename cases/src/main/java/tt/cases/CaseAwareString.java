package tt.cases;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * CaseAwareString
 */
public class CaseAwareString {

    public enum Case {
        Camel,
        Snake,
        Kebab,
    }

    final private List<String> sections;
    
    CaseAwareString() {
        this(new ArrayList<>());
    }

    CaseAwareString(String[] sections) {
        this(List.of(sections));
    }

    CaseAwareString(List<String> sections) {
        this.sections = sections;
    }

    public List<String> getSections() {
        return this.sections;
    }

    private static final String italize(Function<String, String> f, String string) {
        assert(string != null);
        switch (string.length()) {
            case 0:
                return string;
            case 1:
                return f.apply(string);
            default:
                // Not safe for all unicode
                return f.apply(string.substring(0, 1)) + string.substring(1);
        }        
    }

    public static final String capitalize(String string) {
        return CaseAwareString.italize(String::toUpperCase, string);
    }

    public static final String decapitalize(String string) {
        return CaseAwareString.italize(String::toLowerCase, string);
    }
    
    public static CaseAwareString from(Case c, String string) {
        switch (c) {
            case Camel:
                return CaseAwareString.fromCamelCase(string);
            case Kebab:
                return CaseAwareString.fromKebabCase(string);
            case Snake:
                return CaseAwareString.fromSnakeCase(string);
        }
        throw new UnsupportedOperationException();
    }

    public static CaseAwareString fromCamelCase(String string) {
        List<String> substrings = new ArrayList<>();
        String substring = "";
        int length = string.length();

        if (length == 0) {
            return new CaseAwareString();
        }

        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (i == 0) {
                substring += c;
                continue;
            }            
            if (Character.isUpperCase(c)) {
                substrings.add(substring);
                substring = String.valueOf(c);
                continue;
            } 
            substring += c;
        }

        if (!substring.isEmpty()) {
            substrings.add(substring);
        }

        return new CaseAwareString(substrings);
    }

    public static CaseAwareString fromSnakeCase(String string) {
        if (string.length() == 0) {
            return new CaseAwareString();
        }
        return new CaseAwareString(string.split("_", -1));
    }

    public static CaseAwareString fromKebabCase(String string) {
        if (string.length() == 0) {
            return new CaseAwareString();
        }
        return new CaseAwareString(string.split("-", -1));
    }

    // case:CamelCase, capitalize: false => helloWorld
    // case:CamelCase, capitalize: true  => HelloWorld
    // case:SnakeCase, capitalize: false => hello_world
    // case:SnakeCase, capitalize: true  => HELLO_WORLD
    // case:KebabCase, capitalize: false => hello-world
    // case:KebabCase, capitalize: true  => Hello-World
    public String into(Case c, boolean capitalize) {
        switch (c) {
            case Camel:
                return this.intoCamelCase(capitalize);
            case Snake:
                return this.intoSnakeCase(capitalize);
            case Kebab:
                return this.intoKebabCase(capitalize);
        }
        throw new UnsupportedOperationException();
    }

    // capitalize: false => helloWorld
    // capitalize: true  => HelloWorld
    public String intoCamelCase(boolean capitalize) {        
        // The first section is decapitalized
        if (!capitalize) {
            final var first = sections.stream().limit(1).map(CaseAwareString::decapitalize);
            final var rest = sections.stream().skip(1).map(CaseAwareString::capitalize);
            return Stream.concat(first, rest).collect(Collectors.joining(""));
        }
        // Everything is capitalized
        return sections.stream()
            .map(CaseAwareString::capitalize)
            .collect(Collectors.joining(""));
    }

    // capitalize: false => hello_world
    // capitalize: true  => HELLO_WORLD
    public String intoSnakeCase(boolean capitalize) {
        Function<String, String> transformation = 
            capitalize ? String::toUpperCase : String::toLowerCase;
        return sections.stream().map(transformation).collect(Collectors.joining("_"));
    }

    // capitalize: false => hello-world
    // capitalize: true  => Hello-World
    public String intoKebabCase(boolean capitalize) {
        Function<String, String> transformation = 
            capitalize ? CaseAwareString::capitalize : CaseAwareString::decapitalize;
        return sections.stream().map(transformation).collect(Collectors.joining("-"));
    }

    @Override
    public int hashCode() {
        return this.sections.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof CaseAwareString)) {
            return false;
        }
        CaseAwareString other = (CaseAwareString) obj;

        if (other.sections.size() != this.sections.size()) {
            return false;
        }

        int size = this.sections.size();
        for (int i = 0; i < size; i++) {
            var thisSection = this.sections.get(i).toLowerCase();
            var otherSection = other.sections.get(i).toLowerCase();
            if (!thisSection.equals(otherSection)){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        var contents = this.sections.stream()
            .map(s -> "'" + s + "'")
            .collect(Collectors.joining(", "));
        return "[[" + contents + "]]";
    }
}