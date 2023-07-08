package arrays.SubarraySumsDivisiblebyK;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        int[] nums2 = {0, -5};
        System.out.println(subarraysDivByK(nums, 5));
        System.out.println(subarraysDivByK(nums2, 10));
    }

    public static int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCounts = new HashMap<>();
        remainderCounts.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum = (sum + num % k + k) % k;
            count += remainderCounts.getOrDefault(sum, 0);
            remainderCounts.put(sum, remainderCounts.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
