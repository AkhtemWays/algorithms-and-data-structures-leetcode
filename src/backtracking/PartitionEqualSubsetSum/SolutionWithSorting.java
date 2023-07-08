package backtracking.PartitionEqualSubsetSum;

import java.util.Arrays;

public class SolutionWithSorting {
    public static void main(String[] args) {
        int[] nums = {1, 5, 12, 5};
        int[] nums2 = {1,2,3,5};
        int[] nums3 = {1, 5, 11, 5};
        int[] nums4 = {1,2,3,6};
        int[] nums5 = {2, 2, 1, 1};
        System.out.println(canPartition(nums));
        System.out.println(canPartition(nums2));
        System.out.println(canPartition(nums3));
        System.out.println(canPartition(nums4));
    }

    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int half = sum / 2;
        boolean[][] dp = new boolean[nums.length+1][half+1];
        dp[0][0] = true;
        for (int i = 1; i <= nums.length; i++) {
            int cur = nums[i-1];
            for (int subsetSum = 0; subsetSum <= half; subsetSum++) {
                dp[i][subsetSum] = dp[i - 1][subsetSum] || (subsetSum - cur >= 0 && dp[i - 1][subsetSum - cur]);
            }
        }
        return dp[nums.length][half];
    }
}
