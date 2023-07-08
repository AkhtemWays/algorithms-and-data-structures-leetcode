package trie.WordBreak;

import java.util.*;

public class DPApproach {
    public static void main(String[] args) {
        String s = "cars";
        String s2 = "leetcode";
        String s3 = "aaaaaaa";
        String s4 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        BFSApproach main = new BFSApproach();
        System.out.println(main.wordBreak(s2, List.of("leet", "code")));
        System.out.println(main.wordBreak(s, List.of("car", "ca", "rs")));
        System.out.println(main.wordBreak(s3, List.of("aaaa", "aa")));
        System.out.println(main.wordBreak(s4, List.of("a", "aa")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
