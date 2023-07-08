package backtracking.PartitionEqualSubsetSum;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 5, 12, 5};
        int[] nums2 = {1,2,3,5};
        int[] nums3 = {1, 5, 11, 5};
        int[] nums4 = {1,2,3,6};
        int[] nums5 = {2, 2, 1, 1};
        int[] nums6 = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        int[] nums7 = {4,4,4,4,4,4,4,4,8,8,8,8,8,8,8,8,12,12,12,12,12,12,12,12,16,16,16,16,16,16,16,16,20,20,20,20,20,20,20,20,24,24,24,24,24,24,24,24,28,28,28,28,28,28,28,28,32,32,32,32,32,32,32,32,36,36,36,36,36,36,36,36,40,40,40,40,40,40,40,40,44,44,44,44,44,44,44,44,48,48,48,48,48,48,48,48,52,52,52,52,52,52,52,52,56,56,56,56,56,56,56,56,60,60,60,60,60,60,60,60,64,64,64,64,64,64,64,64,68,68,68,68,68,68,68,68,72,72,72,72,72,72,72,72,76,76,76,76,76,76,76,76,80,80,80,80,80,80,80,80,84,84,84,84,84,84,84,84,88,88,88,88,88,88,88,88,92,92,92,92,92,92,92,92,96,96,96,96,96,96,96,96,97,99};
        System.out.println(canPartition(nums)); // false
        System.out.println(canPartition(nums2)); // false
        System.out.println(canPartition(nums3)); // true
        System.out.println(canPartition(nums4)); // true
        System.out.println(canPartition(nums5)); // true
        System.out.println(canPartition(nums6)); // false
        System.out.println(canPartition(nums7));
    }

    public static boolean canPartition(int[] nums) {
        return dfs(0, nums, Arrays.stream(nums).sum(), 0, new HashMap<>());
    }

    private static boolean dfs(int i, int[] nums, int sum, int curSum, Map<Integer, Integer> failuresCache) {
        if (i == nums.length) return false;
        if (sum - curSum == curSum) return true;
        if (sum - curSum < curSum) return false;

        boolean skippedSum = false;
        if (!(failuresCache.containsKey(i+1) && failuresCache.get(i+1) == curSum)) {
            skippedSum = dfs(i+1, nums, sum, curSum, failuresCache);
            failuresCache.put(i+1, curSum);
        }
        boolean includedSum = false;
        if (!(failuresCache.containsKey(i+1) && failuresCache.get(i+1) == curSum + nums[i])) {
            includedSum = dfs(i+1, nums, sum, curSum + nums[i], failuresCache);
            failuresCache.put(i+1, curSum + nums[i]);
        }
        return includedSum || skippedSum;
    }
}
