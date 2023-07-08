package arrays.MaximumSumCircularSubarray;

public class Main {
    public static void main(String[] args) {
        int[] nums = {0,5,8,-9,9,-7,3,-2};
        System.out.println(maxSubarraySumCircular(nums));
    }

    public static int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] rightMax = new int[n];
        rightMax[n-1] = nums[n-1];
        int curSum = nums[n-1];
        int max = Integer.MIN_VALUE;
        for (int i = n-2; i >= 0; i--) {
            curSum += nums[i];
            if (curSum > max) {
                max = curSum;
            }
            rightMax[i] = max;
        }
        int specialMax = Integer.MIN_VALUE;
        int curMax = Integer.MIN_VALUE;
        for (int i = 0, prefixSum = 0; i < n; i++) {
            prefixSum += nums[i];
            if (curMax < prefixSum) {
                curMax = prefixSum;
            }
            if (prefixSum < 0) {
                prefixSum = 0;
            }
            if (i + 1 < n) {
                specialMax = Math.max(specialMax, curMax + rightMax[i+1]);
            }
        }
        return Math.max(specialMax, max);
    }
}
