package arrays.LongestSubstringwithAtMostTwoDistinctCharacters;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("abccbbcccaaacaca")); // 10
        System.out.println(lengthOfLongestSubstringTwoDistinct("ababacccccc")); // 7
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba")); // 3
        System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb")); // 5
        System.out.println(lengthOfLongestSubstringTwoDistinct("a")); // 1
        System.out.println(lengthOfLongestSubstringTwoDistinct("")); // 0
        System.out.println(lengthOfLongestSubstringTwoDistinct("abaccc")); // 4
        System.out.println(lengthOfLongestSubstringTwoDistinct("abaaaaccc")); // 7
        System.out.println(lengthOfLongestSubstringTwoDistinct("aac")); // 3
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
       if (s.length() < 2) return s.length();
       int i = 0;
       int j = getIndex(s);
       if (j == -1) return s.length();
       int length = j + 1;
       for (int k = j + 1; k < s.length(); k++) {
           if (s.charAt(k) != s.charAt(i) && s.charAt(k) != s.charAt(j)) {
               char prev = s.charAt(k-1);
               int c = k - 1;
               while (s.charAt(c) == prev) {
                   c--;
               }
               i = c + 1;
               j = k;
           }
           length = Math.max(length, k - i + 1);
       }
       return length;
    }

    private static int getIndex(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(0)) return i;
        }
        return -1;
    }
}
