package backtracking.TheNumberofBeautifulSubsets;

import java.util.*;

public class Third {
    public static void main(String[] args) {
        int[] nums = {2,4,6};
        int[] nums2 = {1};
        int[] nums3 = {4,2,5,9,10,3};
        System.out.println(beautifulSubsets(nums, 2));
        System.out.println(beautifulSubsets(nums2, 1));
        System.out.println(beautifulSubsets(nums3, 1));
    }

    public static int beautifulSubsets(int[] nums, int k) {
        HashMap<Integer, List<Integer>> groups = new HashMap<>();
        for (int num : nums) {
            groups.computeIfAbsent(num % k, (key) -> new ArrayList<>()).add(num);
        }
        int score = 1;
        for (List<Integer> group : groups.values()) {
            Collections.sort(group);
            Map<Integer, Integer> counts = new HashMap<>();
            for (int num : group) counts.put(num, counts.getOrDefault(num, 0) + 1);
            int prev_el = Integer.MIN_VALUE, prevNotTaken = 1, prevTaken = 0, nowNotTaken = 0, nowTaken = 0;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                int freq = entry.getValue();
                int el = entry.getKey();
                int possibleSubsets = (int) Math.pow(2, freq) - 1;
                if (prev_el+el == k) {
                    nowNotTaken = prevNotTaken + prevTaken;
                    nowTaken = prevNotTaken * possibleSubsets;
                } else {
                    nowNotTaken = prevNotTaken + prevTaken;
                    nowTaken = nowNotTaken * possibleSubsets;
                }
                prevNotTaken = nowNotTaken;
                prevTaken = nowTaken;
                prev_el = el;
            }
            score = score * (nowTaken + nowNotTaken);
        }
        return score-1;
    }
}
