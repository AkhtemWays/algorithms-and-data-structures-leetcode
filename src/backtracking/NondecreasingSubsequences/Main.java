package backtracking.NondecreasingSubsequences;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        int[] nums2 = {4,4,3,2,1};
        int[] nums3 = {1,2,1,1};
        System.out.println(findSubsequences(nums));
        System.out.println(findSubsequences(nums2));
        System.out.println(findSubsequences(nums3));
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        dfs(0, nums, res, new ArrayList<>());
        return new ArrayList<>(res);
    }

    private static void dfs(int i, int[] nums, Set<List<Integer>> res, List<Integer> cur) {
        if (i == nums.length) return;

        if (cur.isEmpty() || nums[i] >= cur.get(cur.size() - 1)) {
            cur.add(nums[i]);
            if (cur.size() >= 2) {
                res.add(List.copyOf(cur));
            }
            dfs(i+1, nums, res, cur);
            cur.remove(cur.size()-1);
            int k = i+1;
            while (k < nums.length && nums[k] == nums[i]) {
                k++;
            }
            dfs(k, nums, res, cur);
        } else {
            dfs(i+1, nums, res, cur);
        }
    }
}
