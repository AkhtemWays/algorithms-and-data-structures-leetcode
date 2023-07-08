package dynamicProgramming.WordBreak;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.wordBreak("leetcode", List.of("leet","code")));
        System.out.println(main.wordBreak("applepenapple", List.of("apple","pen")));
        System.out.println(main.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
        System.out.println(main.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int start = 0; start < s.length(); start++) {
            if (!dp[start]) continue;
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordSet.contains(s.substring(start, end)) && dp[start]) {
                    dp[end] = true;
                }
            }
        }
        return dp[s.length()];
    }


}
