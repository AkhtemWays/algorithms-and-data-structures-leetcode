package strings.RepeatedSubstringPattern;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abab"));
        System.out.println(repeatedSubstringPattern("abcabcabcabc"));
        System.out.println(repeatedSubstringPattern("aba"));
        System.out.println(repeatedSubstringPattern("abaabaa"));
    }

    public static boolean repeatedSubstringPattern(String s) {
        if (s.length() == 0) return true;
        int[] counts = new int[26];
        int patternLength = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            patternLength = Math.min(patternLength, getPatternLength(counts));
        }
        if (patternLength > s.length() || s.length() % patternLength != 0) return false;

        return isRepeated(s, patternLength);
    }

    private static boolean isRepeated(String s, int patternLength) {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < patternLength; i++) pattern.append(s.charAt(i));

        for (int i = patternLength; i < s.length(); i++) {
            if (pattern.charAt(i % patternLength) != s.charAt(i)) return false;
        }
        return true;
    }

    private static int getPatternLength(int[] counts) {
        boolean allEven = Arrays.stream(counts).allMatch(count -> count % 2 == 0);
        if (!allEven) return Integer.MAX_VALUE;
        return Arrays.stream(counts).sum() / 2;
    }
}
