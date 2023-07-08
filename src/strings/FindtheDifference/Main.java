package strings.FindtheDifference;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println(findTheDifference("abcd", "bcade"));
    }

    public static char findTheDifference(String s, String t) {
        int[] sCounts = new int[26];
        int[] tCounts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sCounts[s.charAt(i) - 'a']++;
            tCounts[t.charAt(i) - 'a']++;
        }
        tCounts[t.charAt(t.length()-1)-'a']++;
        for (int i = 0; i < 26; i++) {
            if (tCounts[i] != sCounts[i]) {
                int val = 'a' + i;
                return (char) val;
            }
        }
        return 'a';
    }
}
