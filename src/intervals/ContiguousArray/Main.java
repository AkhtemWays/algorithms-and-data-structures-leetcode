package intervals.ContiguousArray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] nums = {0,1,0};
        int[] nums2 = {0, 1, 1, 1, 0};
        int[] nums3 = {0, 1, 0, 1, 1, 0, 1, 0};
        int[] nums4 = {0,0,0,1,1,1,0};
        int[] nums5 = {0,0,1,0,0,0,1,1};
        System.out.println(findMaxLength2(nums)); // 2
        System.out.println(findMaxLength2(nums2)); // 2
        System.out.println(findMaxLength2(nums3)); // 8
        System.out.println(findMaxLength2(nums4)); // 6
        System.out.println(findMaxLength2(nums5)); // 6
    }

    public static int findMaxLength(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (i-2 >= 0 && nums[i-2] == nums[i]) {
                    dp[i] = dp[i-2] + 1;
                } else {
                    dp[i] = 1;
                }
            } else {
                if (i - 2 * dp[i-1] - 1 >= 0 && nums[i - 2 * dp[i-1] - 1] != nums[i] && dp[i-1] > 0) {
                    dp[i] = dp[i-1] + 1;
                } else {
                    dp[i] = 0;
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt() * 2;
    }

    public static int findMaxLength2(int[] nums) {
        if (nums.length == 0) return 0;
        int sum = 0;
        int max = 0;
        HashMap<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int size = i+1;
            sums.put(size, sum);
            if (size-sum*2 >= 0 && sums.get(size-(sum*2)) == 0) {
                max = sum * 2;
            }
        }
        return max;
    }
}
