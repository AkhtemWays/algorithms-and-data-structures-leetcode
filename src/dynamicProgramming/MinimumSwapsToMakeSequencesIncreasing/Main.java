package dynamicProgramming.MinimumSwapsToMakeSequencesIncreasing;

import java.util.Arrays;

public class Main {
    private static void test1() {
        int[] nums = {1,3,5,4};
        int[] nums2 = {1,2,3,7};
        System.out.println(minSwap(nums, nums2));
    }
    private static void test2() {
        int[] nums1 = {3,3,8,9,10};
        int[] nums2 = {1,7,4,6,8};
        System.out.println(minSwap(nums1, nums2));
    }
    private static void test3() {
        int[] nums1 = {0,4,4,5,9};
        int[] nums2 = {0,1,6,8,10};
        System.out.println(minSwap(nums1, nums2));
    }
    private static void test4() {
        int[] nums1 = {0,1,8,9,10};
        int[] nums2 = {0,5,5,8,9};
        System.out.println(minSwap(nums1, nums2));
    }
    public static void main(String[] args) {
        test1(); // 1
        test2(); // 1
        test3(); // 1
        test4(); // 1
    }

    private static int n;
    public static int minSwap(int[] nums1, int[] nums2) {
        n = nums1.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        return dfs(0, dp, nums1, nums2, 0);
    }

    private static int dfs(int i, int[][] dp, int[] nums1, int[] nums2, int swap) {
        if (i == n) return 0;

        if (dp[i][swap] != -1) return dp[i][swap];

        int swapped = Integer.MAX_VALUE, notSwapped = Integer.MAX_VALUE;

        if (isValid(i, nums1, nums2)) {
            notSwapped = dfs(i+1, dp, nums1, nums2, 0);
        }
        swap(i, nums1, nums2);
        if (isValid(i, nums1, nums2)) {
            swapped = 1 + dfs(i+1, dp, nums1, nums2, 1);
        }
        swap(i, nums1, nums2);
        int res = Math.min(notSwapped, swapped);
        return dp[i][swap] = res;
    }

    private static void swap(int i, int[] nums1, int[] nums2) {
        int tmp = nums1[i];
        nums1[i] = nums2[i];
        nums2[i] = tmp;
    }

    private static boolean isValid(int i, int[] nums1, int[] nums2) {
        if (i-1 < 0) return true;
        if (nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1]) return true;
        return false;
    }
}
