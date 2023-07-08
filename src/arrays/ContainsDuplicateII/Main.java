package arrays.ContainsDuplicateII;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 1};
        System.out.println(containsNearbyDuplicate(nums, 3));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0) return false;
        HashSet<Integer> valueCounts = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (valueCounts.contains(nums[i])) return true;
            if (i-k >= 0) valueCounts.remove(nums[i-k]);
            valueCounts.add(nums[i]);
        }
        return false;
    }
}
