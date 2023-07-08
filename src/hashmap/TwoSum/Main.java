package hashmap.TwoSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            counts.put(nums[i], i);
        }
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (counts.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = counts.get(target - nums[i]);
                return res;
            }
        }
        return new int[2];
    }
}
