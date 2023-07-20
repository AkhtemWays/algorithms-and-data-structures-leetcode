package backtracking.RegularExpressionMatching;

public class Main {
    private static void test1() {
        System.out.println(isMatch("aa", "a"));
    }
    private static void test2() {
        System.out.println(isMatch("aa", "a*"));
    }
    private static void test3() {
        System.out.println(isMatch("ab", ".*"));
    }
    private static void test4() {
        System.out.println(isMatch("a", "ab*"));
    }
    private static void test5() {
        System.out.println(isMatch("ab", ".*c"));
    }
    public static void main(String[] args) {
        test1(); // false
        test2(); // true
        test3(); // true
        test4(); // true
        test5(); // false
    }

    public static boolean isMatch(String s, String p) {
        boolean[][] memo = new boolean[s.length()+1][p.length()+1];
        return dfs(0, 0, s, p, memo);
    }

    private static boolean dfs(int i, int j, String s, String p, boolean[][] memo) {
        if (i == s.length() && j == p.length()) return true;
        if (j == p.length()) {
            memo[i][j] = true;
            return false;
        }

        if (memo[i][j]) return false;

        char ch = p.charAt(j);
        if (j+1 < p.length() && p.charAt(j+1) == '*') {
            if (dfs(i, j+2, s, p, memo)) return true; // zero taken case
            for (int k = i; k < s.length(); k++) { // one or more case
                if (s.charAt(k) == ch || ch == '.') {
                    if (dfs(k+1, j+2, s, p, memo)) return true;
                } else {
                    memo[i][j] = true;
                    return false; // not matched when needed
                }
            }
        } else if (i < s.length() && (s.charAt(i) == ch || ch == '.')) {
            return dfs(i+1, j+1, s, p, memo);
        }
        memo[i][j] = true;
        return false;
    }
}
