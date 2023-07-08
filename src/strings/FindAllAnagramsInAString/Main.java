package strings.FindAllAnagramsInAString;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        findAnagrams("cbaebabacd", "abc").forEach(System.out::println);
    }

    public static List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) return List.of();
        final int[] pCount = getPCount(p);
        final int[] curCount = new int[26];
        int curSize = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            curCount[s.charAt(i) - 'a']++;
            curSize++;

            if (curSize == p.length()) {
                int idx = i - curSize + 1;
                char chToRemove = s.charAt(idx);
                if (Arrays.equals(curCount, pCount)) {
                    res.add(idx);
                }
                curSize--;
                curCount[chToRemove - 'a']--;
            }
        }

        return res;
    }

    private static int[] getPCount(String p) {
        final int[] res = new int[26];
        for (int i = 0; i < p.length(); i++) res[p.charAt(i) - 'a'] += 1;
        return res;
    }
}
