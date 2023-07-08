package arrays.combinationSum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] candidates = {2, 5, 2, 1, 2};
        List<List<Integer>> result = combinationSum2(candidates, 7);
        result.forEach(combination -> System.out.println(combination.toString()));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, new ArrayList<>(), result, target, Arrays.stream(candidates).sorted().toArray());
        return result;
    }

    public static void dfs(int i, List<Integer> combination, List<List<Integer>> result, int target, int[] candidates) {
        if (getSum(combination) == target) {
            result.add(List.copyOf(combination));
            return;
        } if (getSum(combination) > target || i >= candidates.length) {
            return;
        }

        combination.add(candidates[i]);
        dfs(i + 1, combination, result, target, candidates);
        combination.remove(combination.size() - 1);
        while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) i++;
        dfs(i + 1, combination, result, target, candidates);
    }

    public static int getSum(List<Integer> combination) {
        return combination.stream().reduce(0, Integer::sum);
    }
}
