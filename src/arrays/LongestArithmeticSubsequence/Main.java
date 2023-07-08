package arrays.LongestArithmeticSubsequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static void test1() {
        int[] nums = {3,6,9,12};
        System.out.println(longestArithSeqLength(nums));
    }
    private static void test2() {
        int[] nums = {9,4,7,2,10};
        System.out.println(longestArithSeqLength(nums));
    }
    private static void test3() {
        int[] nums = {20,1,15,3,10,5,8};
        System.out.println(longestArithSeqLength(nums));
    }
    public static void main(String[] args) {
        test1(); // 4
        test2(); // 3
        test3(); // 4
    }

    public static int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], (k) -> new ArrayList<>()).add(i);
        }

        int answer = 1;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            for (int diff = -500; diff <= 500; diff++) {
                int cur = 1;
                int value = entry.getKey();
                int i = entry.getValue().get(0);
                while (map.containsKey(value + diff)) {
                    List<Integer> indices = map.get(value + diff);
                    int idx = binarySearch(i, indices);
                    if (idx == indices.size()) break;
                    i = indices.get(idx);
                    value = nums[i];
                    cur++;
                }
                answer = Math.max(answer, cur);
            }
        }

        return answer;
    }

    private static int binarySearch(int target, List<Integer> l) {
        int left = 0, right = l.size();
        while (left < right) {
            int mid = (right + left) / 2;
            if (target < l.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
