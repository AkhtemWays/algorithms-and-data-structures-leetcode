package arrays.NumberofLongestIncreasingSubsequence;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7};
        int[] nums2 = {2,2,2,2,2};
        int[] nums3 = {1,4,8,15,0,9,14,2,8,11,16};
        System.out.println(findNumberOfLIS(nums)); // 2
        System.out.println(findNumberOfLIS(nums2)); // 5
        System.out.println(findNumberOfLIS(nums3)); // 3
    }

    private static int binarySearch(int[] lis, int target, int right) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (lis[mid] == target) return mid;
            else if (lis[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n];
        lis[0] = nums[0];
        int[] lisCount = new int[n];
        Arrays.fill(lisCount, 1);
        lisCount[0] = 1;
        int j = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > lis[j-1]) {
                lis[j] = nums[i];
                lisCount[j] = lisCount[j-1];
                j++;
            } else {
                int idx = binarySearch(lis, nums[i], j);
                lis[idx] = nums[i];
                lisCount[idx]++;
            }
        }
        return lisCount[j-1];
    }
}
