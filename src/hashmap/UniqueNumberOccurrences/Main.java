package hashmap.UniqueNumberOccurrences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        Set<Integer> unique = new HashSet<>();
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        for (int count : counts.values()) {
            if (unique.contains(count)) return false;
            unique.add(count);
        }
        return true;
    }
}
