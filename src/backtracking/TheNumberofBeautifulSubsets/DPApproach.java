package backtracking.TheNumberofBeautifulSubsets;

import java.util.*;

public class DPApproach {
    public static void main(String[] args) {
        int[] nums = {2,4,6};
        int[] nums2 = {1};
        int[] nums3 = {4,2,5,9,10,3};
//        System.out.println(beautifulSubsets(nums, 2));
//        System.out.println(beautifulSubsets(nums2, 1));
        System.out.println(beautifulSubsets(nums3, 1));
    }

    public static int beautifulSubsets(int[] nums, int k) {
        if (nums.length == 1) return 1;
        HashMap<Integer, List<Integer>> groups = new HashMap<>();
        for (int num : nums) {
            groups.computeIfAbsent(num % k, (key) -> new ArrayList<>()).add(num);
        }
        int score = 0;
        for (List<Integer> group : groups.values()) {
            Collections.sort(group);
            int[] dp = new int[group.size()+1];
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 1; i < group.size(); i++) {
                if (group.get(i) - group.get(i-1) == k) {
                    dp[i+1] = dp[i] + 1;
                } else {
                    dp[i+1] = dp[i-1]+1 + dp[i];
                }
            }
            int finalScore = dp[dp.length-1];
            if (score == 0) {
                score += finalScore;
            } else {
                score = score * finalScore;
            }
        }
        return score-1;
    }
}
