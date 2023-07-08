package strings.IsomorphicStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("paper", "title"));
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> st = new HashMap<>();
        Map<Character, Character> ts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (st.containsKey(s.charAt(i))) {
                if (st.get(s.charAt(i)) != t.charAt(i)) return false;
            } else if (ts.containsKey(t.charAt(i))) return false;
            st.put(s.charAt(i), t.charAt(i));
            ts.put(t.charAt(i), s.charAt(i));
        }
        return true;
    }
}
