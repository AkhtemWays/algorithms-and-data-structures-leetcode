package arrays.MaximumProductofThreeNumbers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static int maximumProduct(int[] nums) {

        Arrays.sort(nums);
        return Math.max(nums[nums.length-1] * Math.max(nums[nums.length-2] * nums[nums.length-3], nums[0] * nums[1]),
                nums[0] * nums[1] * nums[2]);
    }

}
