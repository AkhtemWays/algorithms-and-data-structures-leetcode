package arrays.FindPeakElement;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 6, 5, 4, 3, 2, 1, 8, 7, 5, 1, 0};
        System.out.println(findPeakElement(nums));
    }

    public static int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public static int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }
}
