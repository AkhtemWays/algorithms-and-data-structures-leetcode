package arrays.LongestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MoreEfficientButWeird {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums2 = {0,1,0,3,2,3};
        int[] nums3 = {7,7,7,7,7,7,7};
        int[] nums4 = {0,2,-1,3,1};
//        System.out.println(lengthOfLIS(nums));
//        System.out.println(lengthOfLIS(nums2));
//        System.out.println(lengthOfLIS(nums3));
        System.out.println(lengthOfLIS(nums4));
    }

    public static int lengthOfLIS(int[] nums) {
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for (int num : nums) {
            if (num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                int replaceIdx = binarySearch(sub, num);
                sub.set(replaceIdx, num);
            }
        }
        return sub.size();
    }

    static int binarySearch(List<Integer> nums, int target) {
        int l = 0, r = nums.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (nums.get(m) < target) l = m + 1;
            else r = m;
        }
        return l;
    }
}
