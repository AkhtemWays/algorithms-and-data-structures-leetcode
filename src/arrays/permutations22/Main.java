package arrays.permutations22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        Main main = new Main();
        main.permuteUnique(nums).forEach(permutation -> {
            permutation.forEach(System.out::print);
            System.out.println();
        });
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        ArrayList<Integer> nums_list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int num : nums) nums_list.add(num);

        dfs(nums.length, result, nums_list, 0);
        return result;
    }

    private void dfs(int n, List<List<Integer>> result, ArrayList<Integer> nums, int start) {
        if (start == n) {
            result.add(List.copyOf(nums));
        }

        for (int i = start; i < n; i++) {
            while (i - 1 >= 0 && nums.get(i).equals(nums.get(i-1))) {
                continue;
            }
            Collections.swap(nums, start, i);
            dfs(n, result, nums, start+1);
            Collections.swap(nums, i, start);
        }
    }
}
