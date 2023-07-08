package backtracking.ExtraCharactersinaString;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static void test1() {
        String[] dictionary = {"dsadas"};
        System.out.println(minExtraChar("a", dictionary));
    }
    private static void test2() {
        String[] dictionary = {"leet","code","leetcode"};
        System.out.println(minExtraChar("leetscode", dictionary));
    }
    private static void test3() {
        String[] dictionary = {"hello","world"};
        System.out.println(minExtraChar("sayhelloworld", dictionary));
    }
    public static void main(String[] args) {
        test1(); // 1
        test2(); // 1
        test3(); // 3
    }

    public static int minExtraChar(String s, String[] dictionary) {
        Set<String> words = new HashSet<>(Arrays.asList(dictionary));
        int[][] memo = new int[s.length()][s.length()];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, 0, s, words, memo);
    }

    private static int dfs(int i, int l, String s, Set<String> words, int[][] memo) {
        if (i >= s.length()) return l;

        if (memo[i][l] != -1) return memo[i][l];

        if (words.contains(s.substring(i-l, i+1))) {
            int ans1 = dfs(i+1, 0, s, words, memo);
            int ans2 = dfs(i+1, l+1, s, words, memo);
            int ans3 = l+1 + dfs(i+1, 0, s, words, memo);
            return memo[i][l] = Math.min(Math.min(ans1, ans2), ans3);
        } else {
            int ans1 = dfs(i+1, l+1, s, words, memo);
            int ans2 = l+1 + dfs(i+1, 0, s, words, memo);
            return memo[i][l] = Math.min(ans1, ans2);
        }
    }
}
