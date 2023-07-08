package backtracking.PartitionEqualSubsetSum;

import java.util.Arrays;

public class Active {
    public static void main(String[] args) {
        Active main = new Active();
        int[] nums = {1, 5, 11, 5};
        int[] nums2 = {1, 5, 12, 5};
        System.out.println(main.canPartition(nums));
        System.out.println(main.canPartition(nums2));
    }

    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        Boolean[][] cache = new Boolean[nums.length+1][(sum/2)+1];
        return dfs(0, nums, 0, sum / 2, cache);
    }

    private boolean dfs(int i, int[] nums, int curSum, int subsetSum, Boolean[][] cache) {
        if (curSum == subsetSum) return true;
        if (i == nums.length || nums[i] + curSum > subsetSum) return false;

        boolean try1, try2;
        if (cache[i+1][curSum+nums[i]] != null) {
            try1 = cache[i+1][curSum+nums[i]];
        } else {
            try1 = dfs(i+1, nums, curSum + nums[i], subsetSum, cache);
            cache[i+1][curSum+nums[i]] = try1;
        }
        if (cache[i+1][curSum] != null) {
            try2 = cache[i][curSum];
        } else {
            try2 = dfs(i+1, nums, curSum, subsetSum, cache);
            cache[i+1][curSum] = try2;
        }

        return try1 || try2;
    }
}
