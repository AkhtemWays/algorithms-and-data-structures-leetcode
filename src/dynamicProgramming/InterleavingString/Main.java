package dynamicProgramming.InterleavingString;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcag";
        System.out.println(main.isInterleave(s1, s2, s3));
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for (int i = 1; i < s1.length() + 1; i++) {
            if (s1.charAt(i-1) == s3.charAt(i-1)) {
                dp[i][0] = dp[i-1][0] + 1;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }
        for (int j = 1; j < s2.length() + 1; j++) {
            if (s2.charAt(j-1) == s3.charAt(j-1)) {
                dp[0][j] = dp[0][j-1] + 1;
            } else {
                dp[0][j] = dp[0][j-1];
            }
        }
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                int topValue = dp[i-1][j];
                int leftValue = dp[i][j-1];
                if (topValue > leftValue) {
                    if (s3.charAt(topValue) == s1.charAt(i-1) && i + j - 1 == topValue) {
                        dp[i][j] = topValue + 1;
                    } else {
                        dp[i][j] = topValue;
                    }
                } else {
                    if (s3.charAt(leftValue) == s2.charAt(j-1) && i + j - 1 == leftValue) {
                        dp[i][j] = leftValue + 1;
                    } else {
                        dp[i][j] = leftValue;
                    }
                }
            }
        }
        return dp[s1.length()][s2.length()] == s3.length();
    }
}
