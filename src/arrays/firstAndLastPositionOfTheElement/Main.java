package arrays.firstAndLastPositionOfTheElement;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] range = searchRange(nums, 8);
        Arrays.stream(range).forEach(System.out::println);
    }

    public static int[] searchRange(int[] nums, int target) {
        int startIndex = binarySearch(nums, target, true);
        int endIndex = binarySearch(nums, target, false);
        return new int[]{startIndex, endIndex};
    }

    public static int binarySearch(int[] nums, int target, boolean leftBias) {
        int i = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = getMid(left, right);
            if (nums[mid] == target) {
                i = mid;
                if (leftBias) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (target < nums[mid]) right = mid - 1;
            else if (target > nums[mid]) left = mid + 1;
        }
        return i;
    }

    public static int getMid(int left, int right) {
        return ((Double) Math.floor((left + right) / 2)).intValue();
    }
}
