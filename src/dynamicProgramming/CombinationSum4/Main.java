package dynamicProgramming.CombinationSum4;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target];
        Arrays.fill(memo, -1);
        return dfs(0, nums, target, memo);
    }

    private static int dfs(int sum, int[] nums, int target, int[] memo) {
        if (sum > target) return 0;
        if (sum == target) return 1;

        if (memo[sum] != -1) return memo[sum];

        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            answer += dfs(sum + nums[i], nums, target, memo);
        }

        return memo[sum] = answer;
    }
}
