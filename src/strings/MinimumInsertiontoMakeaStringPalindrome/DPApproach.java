package strings.MinimumInsertiontoMakeaStringPalindrome;

public class DPApproach {
    public static void main(String[] args) {
        System.out.println(minInsertions("zzazz")); // 0
        System.out.println(minInsertions("mbadm")); // 2
        System.out.println(minInsertions("leetcode")); // 5
        System.out.println(minInsertions("zjveiiwvc")); // 5
        System.out.println(minInsertions("vsrgaxxpgfiqdnwvrlpddcz")); // 17
        System.out.println(minInsertions("tldjbqjdogipebqsohdypcxjqkrqltpgviqtqz")); // 25
    }

    public static int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i < s.length()+1; i++) {
            for (int j = 1; j < s.length()+1; j++) {
                if (s.charAt(i-1) == s.charAt(n-j)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return n - dp[n][n];
    }
}
