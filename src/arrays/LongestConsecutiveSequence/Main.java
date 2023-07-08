package arrays.LongestConsecutiveSequence;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] nums = {100,4,200,1,3,2};
        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(main.longestConsecutive(nums));
        System.out.println(main.longestConsecutive(nums2));
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int lcs = 1;
        for (int num : nums) {
            if (!set.contains(num+1)) {
                int number = num;
                int candidate = 0;
                while (set.contains(number--)) {
                    candidate++;
                }
                lcs = Math.max(lcs, candidate);
            }
        }
        return lcs;
    }
}
