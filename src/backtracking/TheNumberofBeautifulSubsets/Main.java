package backtracking.TheNumberofBeautifulSubsets;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int answer = 0;
    public static void main(String[] args) {
        int[] nums = {2,4,6};
        int[] nums2 = {1};
        int[] nums3 = {4,2,5,9};
//        System.out.println(beautifulSubsets(nums, 2));
//        System.out.println(beautifulSubsets(nums2, 1));
        System.out.println(beautifulSubsets(nums3, 1));
    }

    public static int beautifulSubsets(int[] nums, int k) {
        answer = 0;
        if (nums == null || nums.length == 0) return answer;
        dfs(0, nums, k, new ArrayList<>());
        return answer-1;
    }

    private static void dfs(int i, int[] nums, int k, List<Integer> cur) {
        if (i == nums.length) {
            answer++;
            return;
        }

        if (isGoodNumber(nums[i], cur, k)) {
            cur.add(nums[i]);
            dfs(i+1, nums, k, cur);
            cur.remove(cur.size()-1);
        }
        dfs(i+1, nums, k, cur);
    }

    private static boolean isGoodNumber(int num, List<Integer> cur, int k) {
        return cur.stream().allMatch(number -> Math.abs(number - num) != k);
    }
}
