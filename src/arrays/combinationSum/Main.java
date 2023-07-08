package arrays.combinationSum;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> result = combinationSum(candidates, 7);
        result.forEach(l -> System.out.println(l.toString()));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>(new ArrayList<>());
        dfs(0, new ArrayList<>(), 0, target, result, candidates);
        return result;
    }

    public static void dfs(int i, List<Integer> cur, int total, int target, List<List<Integer>> result, int[] candidates) {
        if (total == target) {
            result.add(List.copyOf(cur));
            return;
        }
        if (i >= candidates.length || total > target) return;

        cur.add(candidates[i]);
        dfs(i, cur, total + candidates[i], target, result, candidates);
        cur.remove(cur.size() - 1);
        dfs(i + 1, cur, total, target, result, candidates);
    }

}
