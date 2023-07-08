package arrays.TopKFrequentElements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BucketSort {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(nums, 3)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> numCounts = new HashMap<>();
        int maxBuckets = 0;
        for (int num : nums) {
            int val = numCounts.getOrDefault(num, 0) + 1;
            numCounts.put(num, val);
            maxBuckets = Math.max(val, maxBuckets);
        }
        List<Integer>[] buckets = new List[maxBuckets+1];
        for (int i = 0; i <= maxBuckets; i++) buckets[i] = new ArrayList<>();
        numCounts.forEach((key, value) -> buckets[value].add(key));
        int[] result = new int[Math.min(k, numCounts.size())];
        for (int bucket = maxBuckets, i = 0; bucket >= 1 && i < k; bucket--) {
            for (int val : buckets[bucket]) {
                result[i++] = val;
            }
        }
        return result;
    }
}
