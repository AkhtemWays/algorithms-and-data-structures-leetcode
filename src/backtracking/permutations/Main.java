package backtracking.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);
        result.forEach(col -> System.out.println(col.toString()));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(new ArrayList<>(), nums, result);
        return result;
    }

    public static void dfs(List<Integer> curPermutation, int[] subArray, List<List<Integer>> result) {
        if (subArray.length == 1) {
            List<Integer> newCurPermutation = new ArrayList<>(curPermutation);
            newCurPermutation.add(subArray[0]);
            result.add(newCurPermutation);
            return;
        }
        for (int i = 0; i < subArray.length; i++) {
            List<Integer> newCurPermutation = new ArrayList<>(curPermutation);
            newCurPermutation.add(subArray[i]);
            int[] leftSubArray = Arrays.copyOfRange(subArray, 0, i);
            int[] rightSubArray = Arrays.copyOfRange(subArray, i + 1, subArray.length);
            int[] newSubArray = IntStream.concat(Arrays.stream(leftSubArray), Arrays.stream(rightSubArray)).toArray();
            dfs(newCurPermutation, newSubArray, result);
        }
    }
}
