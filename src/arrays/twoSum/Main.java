package arrays.twoSum;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        System.out.println(Arrays.toString(twoSum(nums, 6)));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                map.put(nums[i], new LinkedList<>(List.of(i)));
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (target % 2 == 0 && target == 2 * nums[i] && map.containsKey(nums[i]) && map.get(nums[i]).size() > 1) {
                LinkedList<Integer> ls = map.get(nums[i]);
                if (ls.getFirst() == i) {
                    ls.removeFirst();
                }
                return new int[]{i, ls.getFirst()};
            } else if (map.containsKey(target - nums[i]) && target != nums[i] * 2) {
                return new int[]{i, map.get(target - nums[i]).getFirst()};
            }
        }
        return new int[2];
    }
}
