package bitManipulation.SmallestSubarraysWithMaximumBitwiseOR;

import javafx.util.Pair;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,0,2,1,3};
        System.out.println(Arrays.toString(smallestSubarrays(nums)));
    }

    public static int[] smallestSubarrays(int[] nums) {
        int bits = 25;
        int n = nums.length;
        int[] res = new int[n];
        int[] closestBits = new int[bits];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < bits; j++) {
                if (((nums[i] >> j) & 1) == 1) {
                    closestBits[bits-j-1] = i;
                }
                res[i] = Math.max(res[i], closestBits[bits-j-1] - i + 1);
            }
        }
        return res;
    }
}
