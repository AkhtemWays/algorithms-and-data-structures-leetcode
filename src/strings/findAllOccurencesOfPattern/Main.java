package strings.findAllOccurencesOfPattern;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(strStr("sadbutsad", "sad"));
        System.out.println(strStr("leetcode", "leeto"));
        System.out.println(strStr("AAACXAABAAA", "AAA"));
        System.out.println(strStr("aabaaabaaac", "aabaaac"));
    }

    public static int strStr(String s, String pattern) {
        int[] lps = getLPS(pattern);
        List<Integer> allOccurences = new ArrayList<>();
        int i = 0, j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            else if (j == 0) {
                i++;
            } else {
                j = lps[j - 1];
            }
            if (j == lps.length) {
                allOccurences.add(i - pattern.length());
                j = lps[j - 1];
            }
        }
        return allOccurences.size() == 0 ? -1 : allOccurences.get(0);
    }

    private static int[] getLPS(String s) {
        int[] lps = new int[s.length()];
        int prev = 0;
        int i = 1;
        while (i < s.length()) {
            if (s.charAt(prev) == s.charAt(i)) {
                lps[i++] = prev++ + 1;
            }
            else if (prev == 0) {
                lps[i++] = 0;
            }
            else {
                prev = lps[prev-1];
            }
        }
        return lps;
    }
}
