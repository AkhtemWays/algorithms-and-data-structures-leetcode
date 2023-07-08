package dynamicProgramming.DominoandTrominoTiling;

public class Main {
    public static void main(String[] args) {
//        System.out.println(numTilings(3)); // 5
//        System.out.println(numTilings(4)); // 11
//        System.out.println(numTilings(5)); // 24
//        System.out.println(numTilings(6)); // 53
        System.out.println(numTilings(7)); // 117
    }

    public static int numTilings(int n) {
        if (n == 1 || n == 2) return n;
        if (n == 3) return 5;
        int[] dp = new int[n+1];
        int MOD = 100_000_000_7;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= n; i++) {
            dp[i] = ((dp[i-1] * 2) % MOD + (dp[i-3]) % MOD) % MOD;
        }
        return dp[n];
    }
}
