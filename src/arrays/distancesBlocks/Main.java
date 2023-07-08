package arrays.distancesBlocks;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    static int distanceBlocks(Map<String, Boolean>[] blocks, Set<String> requirements) {
        List<Integer> counters = new ArrayList<>();
        for (int i = 0; i < blocks.length; i++) {
            Set<String> reqs = new HashSet<>(requirements);
            Map<String, Integer> counter = new HashMap<>();
            int j = i;
            while (j < blocks.length) {
                Map<String, Boolean> block = blocks[j];
                for (Map.Entry<String, Boolean> entry : block.entrySet()) {
                    if (reqs.contains(entry.getKey()) && entry.getValue() && counter.get(entry.getKey()) > Math.abs(j-i)) {
                        counter.put(entry.getKey(), Math.abs(j-i));
                    }
                }
                j++;
            }
            j = i;
            while (j >= 0) {
                Map<String, Boolean> block = blocks[j];
                for (Map.Entry<String, Boolean> entry : block.entrySet()) {
                    if (reqs.contains(entry.getKey()) && entry.getValue() && counter.get(entry.getKey()) > Math.abs(j-i)) {
                        counter.put(entry.getKey(), Math.abs(j-i));
                    }
                }
                j--;
            }
            counters.add(counter.values().stream().reduce(0, Integer::sum));
        }
        int k = 0;
        int smallest = counters.get(k);
        for (int i = 0; i < counters.size(); i++) {
            if (smallest > counters.get(i)) {
                k = i;
                smallest = counters.get(i);
            }
        }
        return k;
    }
}
