package hashmap.LeastNumberofUniqueIntegersafterKRemovals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int numBuckets = 0;
        for (int num : arr) {
            int val = m.getOrDefault(num, 0) + 1;
            numBuckets = Math.max(numBuckets, val);
            m.put(num, val);
        }

        int uniques = m.size();
        int[] buckets = new int[numBuckets+1];
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            buckets[entry.getValue()]++;
        }

        for (int i = 1; i <= numBuckets; i++) {
            while (k >= i) {
                k -= i;
                uniques--;
            }
        }
        return uniques;
    }
}
