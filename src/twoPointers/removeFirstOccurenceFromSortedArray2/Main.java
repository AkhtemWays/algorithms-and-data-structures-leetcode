package twoPointers.removeFirstOccurenceFromSortedArray2;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1, 2, 2, 2};
        int[] nums2 = {1, 1, 2, 2, 3};
        int[] nums3 = {1, 1, 1, 2};
        int[] nums4 = {1, 1, 1};
        int[] nums5 = {1, 1, 2};
        int[] nums6 = {1, 1};
        int[] nums7 = {1, 1, 1, 1};
        int[] nums8 = {1, 2, 2, 2};
        int k = removeDuplicates(nums);
        for (int i = 0; i < nums.length; i++) System.out.println(nums[i]);
        System.out.println(k);
    }

    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }

    static void swap(int i, int j, int[] nums) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}
