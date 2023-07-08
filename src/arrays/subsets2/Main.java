package arrays.subsets2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> subsets = subsets2(nums);
        subsets.forEach(subset -> System.out.println(subset.toString()));
    }

    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(List.of());
        Arrays.sort(nums);
        dfs(0, new ArrayList<>(), subsets, nums);
        return subsets;
    }

    public static void dfs(int i, List<Integer> subset, List<List<Integer>> subsets, int[] nums) {
        if (i == nums.length) return;

        subset.add(nums[i]);
        subsets.add(List.copyOf(subset));
        dfs(i + 1, subset, subsets, nums);
        subset.remove(subset.size() - 1);
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
        dfs(i + 1, subset, subsets, nums);
    }

}
