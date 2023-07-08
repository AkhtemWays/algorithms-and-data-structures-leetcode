package arrays.SearchinRotatedSortedArray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 0, 1};
        int[] arr2 = {3,1};
        int[] arr3 = {5,1,2,3,4};
        System.out.println(search(arr, 6));
        System.out.println(search(arr2, 1));
        System.out.println(search(arr3, 1));
    }

    public static int search(int[] nums, int target) {

        int l = 0, r = nums.length-1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= nums[l]) {
                if (target <= nums[mid] && target >= nums[l]) {
                    return binarySearch(nums, target, l, mid+1);
                }
                l = mid + 1;
            }
            else {
                if (target <= nums[r] && target >= nums[mid]) {
                    return binarySearch(nums, target, mid, r+1);
                }
                r = mid - 1;
            }
        }
        return -1;
    }


    static int binarySearch(int[] nums, int target, int l, int r) {
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < target) l = m + 1;
            else r = m;
        }
        return nums[l] == target ? l : -1;
    }
}
