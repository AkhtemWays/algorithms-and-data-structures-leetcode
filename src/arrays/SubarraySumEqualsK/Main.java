package arrays.SubarraySumEqualsK;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {1,1,1};
        int[] nums2 = {1,2,3};
        int[] nums3 = {1,2,1,2,1};
        System.out.println(subarraySum(nums1, 2));
        System.out.println(subarraySum(nums2, 3));
        System.out.println(subarraySum(nums3, 3));
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> prefixSumCounter = new HashMap<>();
        prefixSumCounter.put(0, 1);
        int sum = 0;
        for (int num : nums) {
            sum += num;
            count += prefixSumCounter.getOrDefault(sum - k, 0);
            prefixSumCounter.put(sum, prefixSumCounter.containsKey(sum) ? prefixSumCounter.get(sum) + 1 : 1);
        }
        return count;
    }
}
