package trie.WordBreak;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecondApproach {
    public static void main(String[] args) {
        String s = "cars";
        String s2 = "leetcode";
        String s3 = "aaaaaaa";
        String s4 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        SecondApproach main = new SecondApproach();
        System.out.println(main.wordBreak(s2, List.of("leet", "code")));
        System.out.println(main.wordBreak(s, List.of("car", "ca", "rs")));
        System.out.println(main.wordBreak(s3, List.of("aaaa", "aa")));
        System.out.println(main.wordBreak(s4, List.of("a", "aa")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        HashSet<String> memo = new HashSet<>();
        return find(0, s, set, memo);
    }

    private static boolean find(int start, String s, Set<String> set, HashSet<String> memo) {
        if (start == s.length()) return true;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
            String substringToDiscover = s.substring(i+1);
            if (set.contains(sb.toString()) && !memo.contains(substringToDiscover)) {
                boolean found = find(i+1, s, set, memo);
                if (found) return true;
                memo.add(substringToDiscover);
            }
        }
        return false;
    }
}
