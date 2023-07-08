package dynamicProgramming.BinaryTreesWithFactors;

import greedy.MaximumIceCreamBars.Main;

import java.util.HashSet;
import java.util.Set;

public class Active {
    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 18};
        int[] nums2 = {2, 4, 5, 10};
        Active active = new Active();
        System.out.println(active.numFactoredBinaryTrees(nums));
        System.out.println(active.numFactoredBinaryTrees(nums2));
    }

    public int numFactoredBinaryTrees(int[] nums) {
        int[] dp = new int[nums.length];
        Set<Integer> previous = new HashSet<>();
        previous.add(nums[0]);
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int count = dp[i-1];
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && previous.contains(nums[i] / nums[j])) {
                    count += dp[j];
                    if (nums[i] / nums[j] == nums[j]) {
                        count += dp[j];
                    }
                }
            }
            previous.add(nums[i]);
            dp[i] = dp[i-1] == count ? count+1 : count;
        }

        return dp[dp.length-1];
    }
}
