package arrays.SortAnArray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] nums = {10, 4, 1, 3, 2, 7, 1, 2, 3, 11, 7, 9, 8, 8, 9};
        System.out.println(Arrays.toString(main.sortArray(nums)));
    }

    public int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length-1);
        return nums;
    }

    private void quicksort(int[] nums, int l, int r) {
        if (l < r) {
            int partitionIdx = partition(nums, l, r);
            quicksort(nums, l, partitionIdx-1);
            quicksort(nums, partitionIdx+1, r);
        }
    }

    private int partition(int[] nums, int l, int r) {
        int pivotIdx = getPivotIndex(nums, l, r);
        swap(pivotIdx, r, nums);
        int pivot = nums[r];
        int idx = l-1;
        for (int i = l; i < r; i++) {
            if (pivot > nums[i]) {
                idx++;
                swap(i, idx, nums);
            }
        }
        swap(idx+1, r, nums);
        return idx + 1;
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int getPivotIndex(int[] nums, int l, int r) {
        int mid = (l + r) / 2;
        int val = Math.max(Math.max(nums[mid], nums[l]), nums[r]);
        return val == nums[mid] ? mid : val == nums[l] ? l : r;
    }
}
