package arrays.LongestIncreasingSubsequence;

import java.util.List;

public class FastestApproach {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums2 = {0,1,0,3,2,3};
        int[] nums3 = {7,7,7,7,7,7,7};
        System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS(nums2));
        System.out.println(lengthOfLIS(nums3));
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        int index = 0;
        dp[0] = nums[0];

        for (int num: nums) {
            if (num > dp[index]) {
                dp[++index] = num;
            } else {
                int j = binarySearch(nums, num, 0, index);
                dp[j] = num;
            }
        }
        return index + 1;
    }

    static int binarySearch(int[] nums, int target, int l, int r) {
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }
}
