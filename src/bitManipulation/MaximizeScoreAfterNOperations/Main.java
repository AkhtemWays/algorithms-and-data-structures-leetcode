package bitManipulation.MaximizeScoreAfterNOperations;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,2};
        int[] nums2 = {3,4,6,8};
        int[] nums3 = {1,2,3,4,5,6};
//        System.out.println(maxScore(nums)); // 1
        System.out.println(maxScore(nums2)); // 11
        System.out.println(maxScore(nums3)); // 14
    }

    public static int maxScore(int[] nums) {
        int n = nums.length;
        long bitmask = ((1L << n) - 1) << 1;
        HashMap<Long, Integer> cache = new HashMap<>();
        return dfs(bitmask, nums, cache, 0, 0, n);
    }

    private static int dfs(long bitmask, int[] nums, HashMap<Long, Integer> cache, int sum, int pairsUsed, int n) {
        if (pairsUsed == n / 2) {
            return sum;
        }

        long key = bitmask | ((long) sum << n+1);
        if (cache.containsKey(key)) return cache.get(key);

        int res = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (((bitmask >> j) & 1) == 1 && ((bitmask >> i) & 1) == 1) {
                    int curSum = sum + (gcd(nums[i-1], nums[j-1]) * pairsUsed+1);
                    long updatedBitmask = (bitmask ^ (1L << i)) ^ (1L << j);
                    res = Math.max(res, dfs(updatedBitmask, nums, cache, curSum, pairsUsed + 1, n));
                }
            }
        }

        cache.put(key, res);
        return res;
    }

    public static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        while (Math.max(a, b) % Math.min(a, b) != 0) {
            if (b > a) b = b % a;
            else a = a % b;
        }
        return Math.min(a, b);
    }
}
