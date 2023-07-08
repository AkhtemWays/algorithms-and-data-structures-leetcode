package hashmap.DegreeofanArray;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,1};
        int[] nums2 = {1,2,2,3,1,4,2};
        int[] nums3 = {2,1,1,2,1,3,3,3,1,3,1,3,2};
        System.out.println(findShortestSubArray(nums));
        System.out.println(findShortestSubArray(nums2));
        System.out.println(findShortestSubArray(nums3));
    }

    public static int findShortestSubArray(int[] nums) {
        HashMap<Integer, int[]> numCounts = new HashMap<>();
        int best = nums[0];
        int bestLastIndex = 0;
        int[] initial = {bestLastIndex, 1};
        int l = 1;
        numCounts.put(best, initial);
        for (int i = 1; i < nums.length; i++) {
            if (numCounts.containsKey(nums[i])) {
                int[] curMeta = numCounts.get(nums[i]);
                curMeta[1]++;

                int[] bestMeta = numCounts.get(best);
                int bestStartIndex = bestMeta[0];
                int bestCount = bestMeta[1];
                if (best != nums[i]) {
                    int curStartIndex = curMeta[0];
                    int curCount = curMeta[1];


                    if (curCount > bestCount ||
                            (curCount == bestCount && i - curStartIndex < bestLastIndex - bestStartIndex)) {
                        best = nums[i];
                        bestLastIndex = i;
                        l = i - curStartIndex + 1;
                    }
                } else {
                    l = i - bestStartIndex + 1;
                    bestLastIndex = i;
                }
            } else {
                int[] val = {i, 1};
                numCounts.put(nums[i], val);
            }
        }
        return l;
    }
}
