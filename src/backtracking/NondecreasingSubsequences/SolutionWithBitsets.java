package backtracking.NondecreasingSubsequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionWithBitsets {
    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        int[] nums2 = {4,4,3,2,1};
        int[] nums3 = {1,2,1,1};
        System.out.println(findSubsequences(nums));
        System.out.println(findSubsequences(nums2));
        System.out.println(findSubsequences(nums3));
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        for (int bitmask = 1; bitmask < (1 << n); bitmask++) {
            List<Integer> sequence = new ArrayList<Integer>();
            // check the i-th bit of the bitmask
            for (int i = 0; i < n; i++) {
                if (((bitmask >> i) & 1) == 1) {
                    sequence.add(nums[i]);
                }
            }
            if (sequence.size() >= 2) {
                // check whether the sequence is increasing
                boolean isIncreasing = true;
                for (int i = 0; i < sequence.size() - 1; i++) {
                    isIncreasing &= sequence.get(i) <= sequence.get(i + 1);
                }
                if (isIncreasing) {
                    result.add(sequence);
                }
            }
        }
        return new ArrayList(result);
    }
}
