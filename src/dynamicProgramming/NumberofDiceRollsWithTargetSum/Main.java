package dynamicProgramming.NumberofDiceRollsWithTargetSum;

public class Main {
    public static void main(String[] args) {
//        System.out.println(numRollsToTarget(1, 6, 3)); // 1
        System.out.println(numRollsToTarget(2, 6, 7)); // 6
        System.out.println(numRollsToTarget(30, 30, 500)); // 222616187
    }

    private static final int MOD = 100_000_000_7;

    public static int numRollsToTarget(int n, int k, int target) {
        Integer[][] memo = new Integer[n+1][target+1];
        return dfs(n, k, target, 0, memo);
    }

    private static int dfs(int n, int k, int target, int curSum, Integer[][] memo) {
        if (n == 0) {
            if (curSum == target) return 1;
            return 0;
        }

        if (curSum > target) {
            return 0;
        }
        if (memo[n][curSum] != null) {
            return memo[n][curSum];
        }

        int sum = 0;
        for (int dice = 1; dice <= k; dice++) {
            sum = (sum + dfs(n-1, k, target, curSum + dice, memo) % MOD) % MOD;
        }
        memo[n][curSum] = sum;
        return sum;
    }
}
