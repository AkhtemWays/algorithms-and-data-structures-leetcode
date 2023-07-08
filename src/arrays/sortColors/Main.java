package arrays.sortColors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2, 0, 1, 1, 0, 2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        counts.put(0, 0); counts.put(1, 0); counts.put(2, 0);
        for (int num : nums) counts.put(num, counts.get(num) + 1);

        int start = 0;
        for (int i = start; i < start + counts.get(0); i++) {
            nums[i] = 0;
        }
        start += counts.get(0);
        for (int i = start; i < start + counts.get(1); i++) {
            nums[i] = 1;
        }
        start += counts.get(1);
        for (int i = start; i < start + counts.get(2); i++) {
            nums[i] = 2;
        }
    }
}
