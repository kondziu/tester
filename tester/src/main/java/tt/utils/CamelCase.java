package tt.utils;

import java.util.ArrayList;
import java.util.List;

public class CamelCase {
    private String s;

    public CamelCase(String s) {
        this.s = s;
    }
    
    public String[] split() {
        
        List<String> substrings = new ArrayList<>();
        String substring = "";

        int length = this.s.length();
        for (int i = 0; i < length; i++) {

            char c = s.charAt(i);

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
        return (String[]) substrings.toArray();
    }
}