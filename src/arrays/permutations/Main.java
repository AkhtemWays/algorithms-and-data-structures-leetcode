package arrays.permutations;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Main main = new Main();
        main.permute(nums).forEach(permutation -> {
            permutation.forEach(System.out::print);
            System.out.println();
        });
    }

    public void backtrack(int n, ArrayList<Integer> nums, List<List<Integer>> output, int start) {

        if (start == n) {
            output.add(List.copyOf(nums));
        }
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            backtrack(n, nums, output, start + 1);
            Collections.swap(nums, start, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }
}
