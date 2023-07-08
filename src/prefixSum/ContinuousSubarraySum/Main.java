package prefixSum.ContinuousSubarraySum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] nums = {23,2,4,6,7};
        int[] nums2 = {23,2,6,4,7};
        int[] nums3 = {23,2,6,4,7};
        int[] nums4 = {5, 0, 0, 0};
        int[] nums5 = {1,0};
        int[] nums6 = {1,0,1,0,1};
        int[] nums7 = {0,1,0,3,0,4,0,4,0};
        int[] nums8 = {5, 2, 4};
        int[] nums9 = {0, 1, 0};
        System.out.println(checkSubarraySum(nums, 6)); // true
        System.out.println(checkSubarraySum(nums2, 6)); // true
        System.out.println(checkSubarraySum(nums3, 13)); // false
        System.out.println(checkSubarraySum(nums4, 3)); // true
        System.out.println(checkSubarraySum(nums5, 2)); // false
        System.out.println(checkSubarraySum(nums6, 4)); // false
        System.out.println(checkSubarraySum(nums7, 5)); // false
        System.out.println(checkSubarraySum(nums8, 5)); // false
        System.out.println(checkSubarraySum(nums9, 1)); // true
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        // initialize the hash map with index 0 for sum 0
        Map<Integer, Integer> hashMap = new HashMap<>(Map.of(0, 0));
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // if the remainder sum % k occurs for the first time
            if (!hashMap.containsKey(sum % k))
                hashMap.put(sum % k, i + 1);
                // if the subarray size is at least two
            else if (hashMap.get(sum % k) < i)
                return true;
        }
        return false;
    }
}
