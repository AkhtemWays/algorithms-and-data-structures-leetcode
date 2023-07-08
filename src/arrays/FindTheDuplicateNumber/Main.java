package arrays.FindTheDuplicateNumber;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 3, 3, 2};
        int[] nums2 = {2,2,2,2,2};
        int[] nums3 = {1,3,4,2,2};
        int[] nums4 = {1, 1};
        System.out.println(findDuplicate(nums));
        System.out.println(findDuplicate(nums2));
        System.out.println(findDuplicate(nums3));
        System.out.println(findDuplicate(nums4));
    }

    public static int findDuplicate(int[] nums) {
        int pointer = 0;
        int prev;
        while (nums[nums[pointer]] > 0) {
            int tmp = nums[pointer];
            nums[pointer] = -nums[pointer];
            pointer = tmp;
            prev = nums[pointer];
            if (prev < 0) return Math.abs(prev);
        }
        return -1;
    }
}
