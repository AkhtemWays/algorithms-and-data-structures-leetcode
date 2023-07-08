package arrays.TopKFrequentElements;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class HoareAlgorithm {
    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 4, 1, 4, 10, 1, 4};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) counts.put(num, 1 + counts.getOrDefault(num, 0));
        int[] res = new int[k];
        List<Map.Entry<Integer, Integer>> occurrences = new ArrayList<>(counts.entrySet());
        hoaresAlgo(occurrences, 0, occurrences.size() - 1, k);
        for (int i = 0; i < k; i++) res[i] = occurrences.get(occurrences.size() - 1 - i).getKey();
        return res;
    }

    private static void hoaresAlgo(List<Map.Entry<Integer, Integer>> occurrences, int lowerBound, int upperBound, int k) {
        if (lowerBound < upperBound) {
            int pivotIdx = partition(occurrences, lowerBound, upperBound);
            if (occurrences.size() - pivotIdx - 1 < k) {
                hoaresAlgo(occurrences, lowerBound, pivotIdx - 1, k);
            }
            else {
                hoaresAlgo(occurrences, pivotIdx + 1, upperBound, k);
            }
        }
    }

    private static int partition(List<Map.Entry<Integer, Integer>> occurrences, int lowerBound, int upperBound) {
        Map.Entry<Integer, Integer> pivot = occurrences.get(upperBound);
        int i = lowerBound-1;
        for (int j = lowerBound; j < upperBound; j++) {
            if (occurrences.get(j).getValue() < pivot.getValue()) {
                i++;
                Collections.swap(occurrences, i, j);
            }
        }
        Collections.swap(occurrences, upperBound, i+1);
        return i+1;
    }
}
