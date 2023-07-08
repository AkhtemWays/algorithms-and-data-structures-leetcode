package dynamicProgramming.MakeArrayStrictlyIncreasing;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static void test1() {
        int[] arr = {2, 8, 10, 15};
        String s = "RLLL";
        System.out.println(sumDistance(arr, s, 0));
    }
    public static void main(String[] args) {
        test1();
    }
    public static int sumDistance(int[] nums, String s, int d) {
        final int mod = 1_000_000_007;
        for (int i=0; i<nums.length; i++) {
            nums[i] += d * (s.charAt(i) == 'R' ? 1 : -1);
        }

        Arrays.sort(nums);
        long sum = 0;
        int n = nums.length;
        for (int i=0; i<n; i++) {
            long curr = i * (long)nums[i] - (n - 1 - i) * (long)nums[i];
            sum += curr;
            sum %= mod;
        }

        return (int) sum ;
    }
}
