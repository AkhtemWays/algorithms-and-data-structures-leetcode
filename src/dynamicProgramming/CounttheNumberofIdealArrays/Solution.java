package dynamicProgramming.CounttheNumberofIdealArrays;

class Solution {
    public static void main(String[] args) {
        System.out.println(idealArrays(37, 71)); // 12789125
        System.out.println(idealArrays(3, 9)); // 44
        System.out.println(idealArrays(4, 9)); // 73
        System.out.println(idealArrays(5, 9)); // 111
        System.out.println(idealArrays(4, 6)); // 39
        System.out.println(idealArrays(5, 3)); // 11
        System.out.println(idealArrays(2, 5)); // 10
        System.out.println(idealArrays(184, 389)); // 510488787
        System.out.println(idealArrays(9767, 9557)); // 1998089
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
        dp[1] = lastStage[1];
        int secondValue = 0;
        for (int i = 2; i < lastStage.length; i++) secondValue += lastStage[i];
        dp[2] = secondValue;
        for (int i = 3; i <= n; i++) {
            int curSum = 0;
            for (int num = 2; num <= maxValue; num++) {
                int sum = 0;
                for (int k = 1; ;k++) {
                    if (num * k >= lastStage.length) break;
                    sum = (sum + lastStage[num*k]) % MOD;
                }
                lastStage[num] = sum;
                curSum = (curSum + sum) % MOD;
            }
            dp[i] = curSum % MOD;
        }
        int result = 0;
        for (int val : dp) result = (result + val) % MOD;
        return result;
    }
}