package dynamicProgramming.MaximumProductSubarray;

import javafx.util.Pair;

public class Main {
    public static void main(String[] args) {
        int[] nums = {-9, 3, -9, 11, 8};
        int[] nums2 = {-9, 3, -9, 11, -8};
        int[] nums3 = {2,3,-2,4};
        int[] nums4 = {-2,0,-1};
        int[] nums5 = {-1,-2,-9,-6};
        int[] nums6 = {0, 2};
        int[] nums7 = {2,-5,-2,-4,3};
        int[] nums8 = {2,3,-1,1,-3,3,0};
        System.out.println(maxProduct(nums));
        System.out.println(maxProduct(nums2));
        System.out.println(maxProduct(nums3));
        System.out.println(maxProduct(nums4));
        System.out.println(maxProduct(nums5));
        System.out.println(maxProduct(nums6));
        System.out.println(maxProduct(nums7));
        System.out.println(maxProduct(nums8));
    }

    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int result = nums[0];
        int min = result;
        int max = result;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int candidate = Math.max(Math.max(min * num, result * num), num);
            if (candidate == min * num) {
                min = Math.min(Math.min(num, min * num), result * num);
                result = candidate;
                if (min > 0) min = result;
                max = Math.max(result, max);
            } else if (candidate == num || candidate == result * num) {
                result = candidate;
                max = Math.max(result, max);
                min = Math.min(Math.min(num, min * num), result * num);
            } else {
                max = Math.max(result, max);
                result = num;
                min = Math.min(Math.min(num, min * num), result * num);
            }
        }

        return max;
    }
}
