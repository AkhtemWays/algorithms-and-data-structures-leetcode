package backtracking.PermutationSequence;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3)); // 213
        System.out.println(getPermutation(4, 9)); // 2314
        System.out.println(getPermutation(3, 1)); // 123
        System.out.println(getPermutation(9, 360000)); // 783269514
    }

    private static TreeSet<String> permutations;
    public static String getPermutation(int n, int k) {
        int[] nums = new int[n];
        permutations = new TreeSet<>();
        for (int i = 0; i < n; i++) nums[i] = i+1;
        dfs(nums.length-1, nums, k);
        return String.valueOf(permutations.last());
    }

    private static void dfs(int i, int[] nums, int k) {
        while (permutations.size() > k) {
            permutations.remove(permutations.last());
        }
        for (int j = i; j >= 0; j--) {
            swap(i, j, nums);
            permutations.add(getString(nums));
            dfs(i-1, nums, k);
            swap(i, j, nums);
        }
    }

    private static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static String getString(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) sb.append(num);
        return sb.toString();
    }
}
