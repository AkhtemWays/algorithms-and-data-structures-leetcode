package arrays.RemoveDuplicatesfromSortedArray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int c = 1;
        int cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != cur) {
                cur = nums[i];
                swap(nums, i, c);
                c++;
            }
        }
        return c;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
