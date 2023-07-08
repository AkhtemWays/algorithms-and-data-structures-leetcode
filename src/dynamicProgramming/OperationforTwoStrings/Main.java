package dynamicProgramming.OperationforTwoStrings;

public class Main {
    public static void main(String[] args) {
        System.out.println(minDistance("eat", "sea")); // 2
        System.out.println(minDistance("mose", "some")); // 4
        System.out.println(minDistance("leetcode", "etco")); // 4
        System.out.println(minDistance("a", "a")); // 0
        System.out.println(minDistance("a", "b")); // 2
    }

    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] dp = new int[m+1];
        for (int i = n; i >= 0; i--) {
            int[] temp = new int[m+1];
            for (int j = m; j >= 0; j--) {
                if (i == n || j == m) {
                    temp[j] = n - i + m - j;
                }
                else if (word1.charAt(i) == word2.charAt(j)) {
                    temp[j] = dp[j+1];
                } else {
                    temp[j] = Math.min(dp[j], temp[j+1]) + 1;
                }
            }
            dp = temp;
        }
        return dp[0];
    }
}
