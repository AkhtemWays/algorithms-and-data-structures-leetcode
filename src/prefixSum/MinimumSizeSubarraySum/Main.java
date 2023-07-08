package prefixSum.MinimumSizeSubarraySum;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (Arrays.stream(nums).sum() < target) return 0;
        int left = 0;
        int right = 1;
        int res = Integer.MAX_VALUE;
        int sum = nums[left];
        while ((left < right && sum >= target) || right < nums.length) {
            if (sum >= target) {
                res = Math.min(right - left, res);
                sum -= nums[left];
                left++;
            } else {
                sum += nums[right];
                right++;
            }
        }
        return res;
    }
}
