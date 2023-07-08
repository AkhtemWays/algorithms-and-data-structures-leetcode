package dynamicProgramming.PartitionEqualSubsetSum;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 5, 12, 5};
        int[] nums2 = {1,2,3,5};
        int[] nums3 = {1, 5, 11, 5};
        int[] nums4 = {1,2,3,6};
        int[] nums5 = {2, 2, 1, 1};
        int[] nums6 = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        int[] nums7 = {4,4,4,4,4,4,4,4,8,8,8,8,8,8,8,8,12,12,12,12,12,12,12,12,16,16,16,16,16,16,16,16,20,20,20,20,20,20,20,20,24,24,24,24,24,24,24,24,28,28,28,28,28,28,28,28,32,32,32,32,32,32,32,32,36,36,36,36,36,36,36,36,40,40,40,40,40,40,40,40,44,44,44,44,44,44,44,44,48,48,48,48,48,48,48,48,52,52,52,52,52,52,52,52,56,56,56,56,56,56,56,56,60,60,60,60,60,60,60,60,64,64,64,64,64,64,64,64,68,68,68,68,68,68,68,68,72,72,72,72,72,72,72,72,76,76,76,76,76,76,76,76,80,80,80,80,80,80,80,80,84,84,84,84,84,84,84,84,88,88,88,88,88,88,88,88,92,92,92,92,92,92,92,92,96,96,96,96,96,96,96,96,97,99};
        int[] nums8 = {1, 2 ,5};
        System.out.println(canPartition(nums)); // false
        System.out.println(canPartition(nums2)); // false
        System.out.println(canPartition(nums3)); // true
        System.out.println(canPartition(nums4)); // true
        System.out.println(canPartition(nums5)); // true
        System.out.println(canPartition(nums6)); // false
        System.out.println(canPartition(nums7)); // false
        System.out.println(canPartition(nums8)); // false
    }

    public static boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        boolean[][] dp = new boolean[nums.length+1][(sum/2)+1];
        for (int i = 0; i < nums.length+1; i++) dp[i][0] = true;

        for (int i = 1; i < nums.length+1; i++) {
            for (int subsetSum = 1; subsetSum < (sum/2)+1; subsetSum++) {
                if (subsetSum - nums[i-1] >= 0) {
                    dp[i][subsetSum] = dp[i-1][subsetSum-nums[i-1]] || dp[i-1][subsetSum];
                } else {
                    dp[i][subsetSum] = dp[i-1][subsetSum];
                }
            }
        }
        return dp[nums.length][sum/2];
    }
}
