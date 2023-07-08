package arrays.nextPermutation;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 1, 5};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n == 1) return;

        int i = 1;
        int lastIncrement = -1;
        while (i < n) {
            if (nums[i] > nums[i - 1]) {
                lastIncrement = i;
            }
            i++;
        }

        if (lastIncrement == -1) {
            Arrays.sort(nums);
            return;
        }

        int index = lastIncrement;
        for (int j = lastIncrement + 1; j < n; j++) {
            if (nums[j] < nums[lastIncrement] && nums[j] > nums[lastIncrement - 1]) {
                index = j;
            }
        }
        swap(nums, lastIncrement - 1, index);
        Arrays.sort(nums, lastIncrement, n);
        return;
    }

    public static void swap(int[] nums, int index1, int index2) {
        int tempValue = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tempValue;
    }
}
