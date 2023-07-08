package arrays.FindMinimumInRotatedSortedArray;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3,4,5,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {11,13,15,17};
        int[] nums4 = {1};
        System.out.println(findMin(nums));
        System.out.println(findMin(nums2));
        System.out.println(findMin(nums3));
        System.out.println(findMin(nums4));
    }

    public static int findMin(int[] nums) {
        if (nums[0] <= nums[nums.length-1]) return nums[0];
        return binarySearch(nums, 0, nums.length-1);
    }

    private static int binarySearch(int[] nums, int l, int r) {
        if (l > r) return -1;
        int mid = (r + l) / 2;
        if (nums[mid-1] > nums[mid]) return nums[mid];
        int left = binarySearch(nums, l, mid-1);
        int right = binarySearch(nums, mid+1, r);
        return Math.max(left, right);
    }
}
