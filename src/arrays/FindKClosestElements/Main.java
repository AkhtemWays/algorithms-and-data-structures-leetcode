package arrays.FindKClosestElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int[] nums2 = {0,0,1,2,3,3,4,7,7,8};
        findClosestElements(nums, 4, 3).forEach(System.out::print);
        System.out.println();
        findClosestElements(nums, 4, -1).forEach(System.out::print);
        System.out.println();
        findClosestElements(nums2, 3, 5).forEach(System.out::print);
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int index = binarySearch(arr, x);
        if (index >= arr.length) {
            index = arr.length - 1;
        } else if (index < 0) {
            index = 0;
        } else {
            index--;
        }
        int left = index;
        int right = left + 1;
        while (k-- > 0) {
            int leftValue = left >= 0 ? arr[left] : -99999999;
            int rightValue = right < arr.length ? arr[right] : -99999999;
            if (Math.abs(leftValue - x) <= Math.abs(rightValue - x)) {
                res.add(leftValue);
                left--;
            } else {
                res.add(rightValue);
                right++;
            }
        }
        Collections.sort(res);
        return res;
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
