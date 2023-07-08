package arrays.threeSum;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {-2,0,1,1,2};
        List<List<Integer>> result = threeSum(nums);
        result.forEach(combination -> System.out.println(combination.toString()));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int[] sorted = Arrays.stream(nums).sorted().toArray();
        List<List<Integer>> result = new java.util.ArrayList<>(List.of());
        for (int i = 0; i < sorted.length - 2; i++) {
            int a = sorted[i];
            if (i > 0 && a == sorted[i - 1]) continue;
            int j = i + 1;
            int k = sorted.length - 1;
            while (k > j) {
                int b = sorted[j];
                int c = sorted[k];
                if (a + b + c > 0) {
                    k -= 1;
                } else if (a + b + c < 0) {
                    j += 1;
                } else {
                    List<Integer> combination = List.of(a, b, c);
                    result.add(combination);
                    j += 1;
                    while (sorted[j] == sorted[j - 1] && k > j) {
                        j += 1;
                    }
                }
            }
        }
        return result;
    }
}
