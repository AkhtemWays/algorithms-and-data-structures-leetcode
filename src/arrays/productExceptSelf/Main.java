package arrays.productExceptSelf;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length+1];
        int[] right = new int[nums.length+1];
        right[right.length-1] = 1;
        left[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            left[i+1] = left[i] * nums[i];
        }
        for (int i = nums.length-1; i >= 0; i--) {
            right[i] = right[i+1] * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = right[i+1] * left[i];
        }
        return nums;
    }
}
