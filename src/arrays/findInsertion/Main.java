package arrays.findInsertion;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,6};
        int position = binarySearch(array, 4);
        System.out.println(position);
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (right + left) / 2;
        while (left <= right) {
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else if (target == nums[mid]) {
                return mid;
            }
            mid = (right + left) / 2;
        }
        return mid;
    }
}
