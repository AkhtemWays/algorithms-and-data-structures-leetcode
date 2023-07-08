package arrays.removeElement;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{1};
        int k = removeElement(array, 1);
        System.out.println(k);
        System.out.println(Arrays.toString(array));
    }

    public static int compare(int i, int j, int[] nums, int val) {
        if (i == j) return i + 1;
        if (nums[i] == val) {
            int candidate = nums[i];
            nums[i] = nums[j];
            nums[j] = candidate;
            return compare(i, j - 1, nums, val);
        } else {
            return compare(i + 1, j, nums, val);
        }
    }

    public static int removeElement(int[] nums, int val) {
        return compare(0, nums.length - 1, nums, 1);
    }
}
