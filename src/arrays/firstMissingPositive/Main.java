package arrays.firstMissingPositive;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 0};
        System.out.println(firstMissingPositive(nums));
    }

    public static int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        for (int n = 1; n < nums.length + 1; n++) {
            if (!set.contains(n)) {
                return n;
            }
        }
        return nums.length + 1;
    }

}
