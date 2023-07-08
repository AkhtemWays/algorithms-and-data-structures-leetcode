package arrays.TopKFrequentElements;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) counts.put(num, 1 + counts.getOrDefault(num, 0));
        int[] res = new int[k];
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(counts.size(), (a, b) -> b.getValue() - a.getValue());
        q.addAll(counts.entrySet());
        for (int i = 0; i < k; i++) res[i] = q.remove().getKey();
        return res;
    }
}
