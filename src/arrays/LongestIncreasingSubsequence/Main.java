package arrays.LongestIncreasingSubsequence;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums2 = {0,1,0,3,2,3};
        int[] nums3 = {7,7,7,7,7,7,7};
        System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS(nums2));
        System.out.println(lengthOfLIS(nums3));
    }

    public static int lengthOfLIS(int... nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[dp.length-1] = 1;
        for (int i = dp.length-2; i >= 0; i--) {
            int cur = nums[i];
            int lis = 1;
            for (int j = i + 1; j < dp.length; j++) {
                if (dp[j] + 1 > lis && nums[j] > cur) {
                    lis = dp[j] + 1;
                }
            }
            dp[i] = lis;
        }
        return Arrays.stream(dp).max().orElse(0);
    }
}
