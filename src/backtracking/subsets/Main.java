package backtracking.subsets;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4, 1};
        List<List<Integer>> subsets = subsets(nums);
        subsets.forEach(subset -> System.out.println(subset.toString()));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(List.of());
        dfs(0, new ArrayList<>(), subsets, nums);
        return subsets;
    }

    public static void dfs(int i, List<Integer> subset, List<List<Integer>> subsets, int[] nums) {
        if (i == nums.length) return;

        subset.add(nums[i]);
        subsets.add(List.copyOf(subset));
        dfs(i + 1, subset, subsets, nums);
        subset.remove(subset.size() - 1);
        dfs(i + 1, subset, subsets, nums);
    }
}
