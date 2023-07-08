package hashmap.longestConsecutiveSequence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int count = 1;
        for (int num : set) {
            count = Math.max(checkAndUpdate(set, num, 1), count);
        }
        return count;
    }

    public static int checkAndUpdate(Set<Integer> set, int num, int count) {
        if (!set.contains(num - 1) && set.contains(num + 1)) {
            while (set.contains(num + 1)) {
                num += 1;
                count += 1;
            }
        }
        return count;
    }
}
