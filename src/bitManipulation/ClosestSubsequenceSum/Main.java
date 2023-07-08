package bitManipulation.ClosestSubsequenceSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int[] nums = {5,-7,3,5};
        int[] nums2 = {7,-9,15,-2};
        int[] nums3 = {1,2,3};
        int[] nums4 = {-2772,6927,4773,-2687,7167,-8995,2940,8869,526};
        System.out.println(minAbsDifference(nums, 6)); // 0
        System.out.println(minAbsDifference(nums2, -5)); // 1
        System.out.println(minAbsDifference(nums3, -7)); // 7
        System.out.println(minAbsDifference(nums4, 969621127)); // 969589925
    }

    public static int minAbsDifference(int[] nums, int goal) {
        int n = nums.length/2;
        long[] left = generateSums(nums, 0, n);
        long[] right = generateSums(nums, n, n*2);
        TreeSet<Long> rightBST = new TreeSet<>();
        for (long val : right) rightBST.add(val);
        long answer = Integer.MAX_VALUE;
        for (long leftSum : left) {
            Long smaller = rightBST.ceiling(goal - leftSum);
            Long bigger = rightBST.floor(goal - leftSum);
            if (smaller != null) {
                answer = Math.min(answer, Math.abs(goal - (leftSum + smaller)));
            }
            if (bigger != null) {
                answer = Math.min(answer, Math.abs(goal - (leftSum + bigger)));
            }
        }
        return (int) answer;
    }

    private static long[] generateSums(int[] nums, int left, int right) {
        int n = right - left;
        long[] result = new long[(1 << n)];
        for (long bitmask = 0; bitmask < 1L << n; bitmask++) {
            int sum = 0;
            int i = 0;
            long x = bitmask;
            while (x > 0) {
                if ((x & 1) == 1) {
                    sum += nums[i+left];
                }
                i++;
                x >>= 1;
            }
            result[(int)bitmask] = sum;
        }
        return result;
    }
}
