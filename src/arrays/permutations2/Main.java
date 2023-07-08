package arrays.permutations2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3, 3, 0, 3};
        List<List<Integer>> result = permuteUnique(nums);
        result.forEach(col -> System.out.println(col.toString()));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(new ArrayList<>(), Arrays.stream(nums).sorted().toArray(), result);
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
            if (i - 1 >= 0 && subArray[i] == subArray[i - 1]) {
                continue;
            }
            List<Integer> newCurPermutation = new ArrayList<>(curPermutation);
            newCurPermutation.add(subArray[i]);
            int[] leftSubArray = Arrays.copyOfRange(subArray, 0, i);
            int[] rightSubArray = Arrays.copyOfRange(subArray, i + 1, subArray.length);
            int[] newSubArray = IntStream.concat(Arrays.stream(leftSubArray), Arrays.stream(rightSubArray)).toArray();
            dfs(newCurPermutation, newSubArray, result);
        }
    }
}
