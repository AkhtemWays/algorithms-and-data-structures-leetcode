package arrays.SubarrayProductLessThanK;

public class Main {
    public static void main(String[] args) {
        int[] nums = {10,5,2,6};
        int[] nums2 = {1, 2, 3};
        int[] nums3 = {1, 1, 1};
        System.out.println(numSubarrayProductLessThanK(nums, 100));
        System.out.println(numSubarrayProductLessThanK(nums, 2000));
        System.out.println(numSubarrayProductLessThanK(nums2, 0));
        System.out.println(numSubarrayProductLessThanK(nums3, 1));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int count = 0;
        for (int left = 0, right = 0, maxProduct = 1; right < nums.length; right++) {
            maxProduct *= nums[right];
            while (left <= right && maxProduct >= k) {
                maxProduct /= nums[left++];
            }
            count += right - left + 1;
        }
        return count;
    }
}
