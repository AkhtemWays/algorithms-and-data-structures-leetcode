package hashmap.longestSubstring;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String s = "abcdefghicklmnop";
        System.out.println(lengthOfLongestSubstringV2(s));
    }

    public static int lengthOfLongestSubstringV2(String s) {
       Set<Character> set = new HashSet<>();
       int res = 0;
       int l = 0;
       for (int r = 0; r < s.length(); r++) {
           char ch = s.charAt(r);
           while (set.contains(ch)) {
               set.remove(s.charAt(l));
               l += 1;
           }
           set.add(ch);
           res = Math.max(res, set.size());
       }
       return res;
    }
}
