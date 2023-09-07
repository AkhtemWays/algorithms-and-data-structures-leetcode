package prefixSum.SumOfFlooredPairs;

import java.util.*;

public class PrefixSumApproach {
    private static void test1() {
        int[] nums = {2,5,9};
        System.out.println(sumOfFlooredPairs(nums));
    }
    private static void test2() {
        int[] nums = {2,5,5,9,9};
        System.out.println(sumOfFlooredPairs(nums));
    }
    private static void test3() {
        int[] nums = {7,7,7,7,7,7,7};
        System.out.println(sumOfFlooredPairs(nums));
    }
    public static void main(String[] args) {
        test1(); // 10
        test2(); // 25
        test3(); // 49
    }

    public static int sumOfFlooredPairs(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int MOD = 100_000_000_7;
        int[] prefixSums = new int[max+1];
        for (int num : nums) {
            prefixSums[num]++;
        }

        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i = 1; i < prefixSums.length; i++) {
            if (prefixSums[i] > 0) {
                counts.put(i, prefixSums[i]);
            }
            prefixSums[i] += prefixSums[i-1];
        }

        long res = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            for (int factor = 1; factor <= max / num; factor++) {
                long cnt = prefixSums[Math.min(num * (factor+1) - 1, prefixSums.length-1)] - prefixSums[num * factor - 1];
                res += cnt * factor * count;
                res %= MOD;
            }
        }

        return (int) res;
    }
}
