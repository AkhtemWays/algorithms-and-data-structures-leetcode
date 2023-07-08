package arrays.MoveZeroes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static void moveZeroes(int[] nums) {
        int insertionIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(i, insertionIndex++, nums);
            }
        }
    }

    private static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
