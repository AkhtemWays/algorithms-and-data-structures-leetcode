package dynamicProgramming.CounttheNumberofIdealArrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(idealArrays(37, 71)); // 12789125 1140
        System.out.println(idealArrays(3, 9)); // 44
        System.out.println(idealArrays(4, 9)); // 73
        System.out.println(idealArrays(5, 9)); // 111
        System.out.println(idealArrays(4, 6)); // 39
        System.out.println(idealArrays(5, 3)); // 11
        System.out.println(idealArrays(2, 5)); // 10
        System.out.println(idealArrays(184, 389)); // 510488787
        System.out.println(idealArrays(9767, 9557)); // 510488787
    }

    public static int idealArrays(int n, int maxValue) {
        int MOD = 100_000_000_7;
        int[] dp = new int[n+1];
        dp[1] = maxValue;
        int[] lastStage = new int[maxValue+1];
        for (int num = 1; num <= maxValue; num++) {
            int value = maxValue / num;
            lastStage[num] = value;
        }
        int[] meta = getDelta(lastStage);
        int delta = meta[0];
        int increment = meta[1];
        delta = (delta + increment) % MOD;
        dp[1] = lastStage[1];
        int secondValue = 0;
        for (int i = 2; i < lastStage.length; i++) secondValue = (secondValue + lastStage[i]) % MOD;
        dp[2] = secondValue;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + delta) % MOD;
            delta = (delta + increment) % MOD;
        }
        int result = 0;
        for (int val : dp) {
            result = (result + val) % MOD;
        }
        return result % MOD;
    }

    private static int[] getDelta(int[] lastStage) {
        int delta = 0;
        int increment = 0;
        for (int num = lastStage.length-1; num >= 2; num--) {
            for (int k = 2; k <= (lastStage.length-1); k++) {
                if (num*k < lastStage.length) {
                    increment += lastStage[num*k] - 1;
                } else {
                    break;
                }
            }
        }
        return new int[]{delta, increment};
    }
}
