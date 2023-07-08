package strings.MinimumInsertiontoMakeaStringPalindrome;

public class Main {
    public static void main(String[] args) {
        System.out.println(minInsertions("zzazz")); // 0
        System.out.println(minInsertions("mbadm")); // 2
        System.out.println(minInsertions("leetcode")); // 5
        System.out.println(minInsertions("zjveiiwvc")); // 5
        System.out.println(minInsertions("vsrgaxxpgfiqdnwvrlpddcz")); // 17
        System.out.println(minInsertions("tldjbqjdogipebqsohdypcxjqkrqltpgviqtqz")); // 25
    }

    public static int minInsertions(String s) {
        Integer[][] memo = new Integer[s.length()+1][s.length()+1];
        return s.length() - lcs(s, new StringBuilder(s).reverse().toString(), 0, 0, memo);
    }

    private static int lcs(String s1, String s2, int i, int j, Integer[][] memo) {
        if (i == s1.length() || j == s2.length()) return 0;
        if (memo[i][j] != null) return memo[i][j];
        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j] = 1 + lcs(s1, s2, i+1, j+1, memo);
        }
        return memo[i][j] = Math.max(lcs(s1, s2, i+1, j, memo), lcs(s1, s2, i, j+1, memo));
    }
}
