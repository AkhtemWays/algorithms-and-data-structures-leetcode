package arrays.leftMostBinarySearch;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3, 3, 5, 7};
        System.out.println(binarySearch(nums, 4));
    }

    static int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }
}
