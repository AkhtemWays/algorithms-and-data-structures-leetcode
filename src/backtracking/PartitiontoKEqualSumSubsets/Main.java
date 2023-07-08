package backtracking.PartitiontoKEqualSumSubsets;

public class Main {
    public static void main(String[] args) {
        int[] nums = {6,5,9,6,3,5,1,10,4,1,4,3,9,9,3,3}; // false
        int[] nums2 = {1,1,1,1,2,2,2,2};
        System.out.println(canPartitionKSubsets(nums, 9)); // false
        System.out.println(canPartitionKSubsets(nums2, 4)); // true
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) max = num;
            sum += num;
        }

        int subsetSum = sum / k;
        if (sum % k != 0 || max > subsetSum) return false;
        Boolean[][][] memo = new Boolean[nums.length][k+1][subsetSum];
        return dfs(0, nums, k, subsetSum, 0, new boolean[nums.length], memo);
    }

    private static boolean dfs(int i, int[] nums, int k, int subsetSum, int curSum, boolean[] visited, Boolean[][][] memo) {
        if (k == 0) return true;
        if (curSum == subsetSum) return dfs(0, nums, k-1, subsetSum, 0, visited, memo);
        if (curSum > subsetSum || i == nums.length) return false;
        if (visited[i]) return dfs(i+1, nums, k, subsetSum, curSum, visited, memo);
        if (memo[i][k][curSum] != null) return memo[i][k][curSum];

        visited[i] = true;
        boolean added = dfs(i+1, nums, k, subsetSum, curSum + nums[i], visited, memo);
        visited[i] = false;
        boolean skipped = dfs(i+1, nums, k, subsetSum, curSum, visited, memo);

        return memo[i][k][curSum] = added || skipped;
    }
}
