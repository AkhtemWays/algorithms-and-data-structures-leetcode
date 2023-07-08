package arrays.RecovertheOriginalArray;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2, 10, 6, 4, 8, 12};
        int[] nums2 = {1,1,3,3};
        int[] nums3 = {5,435};
        int[] nums4 = {11,6,3,4,8,7,8,7,9,8,9,10,10,2,1,9};
//        System.out.println(Arrays.toString(recoverArray(nums)));
//        System.out.println(Arrays.toString(recoverArray(nums2)));
//        System.out.println(Arrays.toString(recoverArray(nums3)));
        System.out.println(Arrays.toString(recoverArray(nums4)));
    }

    public static int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> ks = new HashSet<>();
        Map<Integer, List<Integer>> numIndices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - nums[0] > 0) {
                ks.add(nums[i] - nums[0]);
            }
            numIndices.computeIfAbsent(nums[i], (k) -> new ArrayList<>()).add(i);
        }

        return findK(ks, nums, numIndices);
    }

    private static int[] findK(Set<Integer> ks, int[] nums, Map<Integer, List<Integer>> numIndices) {
        for (int k : ks) {
            if (k == 0 || k % 2 == 1) continue;
            HashMap<Integer, Integer> tracker = initTracker(numIndices);
            Set<Integer> visited = new HashSet<>();
            boolean found = true;
            int[] result = new int[nums.length/2];
            for (int i = 0, j = 0; i < nums.length; i++) {
                if (visited.contains(i)) continue;
                int num = nums[i];
                int pair = num + k;
                if (tracker.containsKey(pair) && tracker.get(pair) < numIndices.get(pair).size()) {
                    int pairTracker = tracker.get(pair);
                    int numTracker = tracker.get(num);
                    tracker.put(pair, pairTracker + 1);
                    tracker.put(num, numTracker + 1);
                    int pairIndex = numIndices.get(pair).get(pairTracker);
                    int numIndex = numIndices.get(num).get(numTracker);
                    result[j++] = nums[numIndex] + k/2;
                    visited.add(pairIndex);
                    visited.add(numIndex);
                } else {
                    found = false;
                    break;
                }
            }
            if (found) return result;
        }
        return new int[0];
    }

    private static HashMap<Integer, Integer> initTracker(Map<Integer, List<Integer>> numIndices) {
        HashMap<Integer, Integer> tracker = new HashMap<>();
        for (int key : numIndices.keySet()) {
            tracker.put(key, 0);
        }
        return tracker;
    }
}
