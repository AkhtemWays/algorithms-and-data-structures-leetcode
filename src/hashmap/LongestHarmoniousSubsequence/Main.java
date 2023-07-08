package hashmap.LongestHarmoniousSubsequence;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }

    public static int findLHS(int[] nums) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (counts.containsKey(entry.getKey() + 1)) {
                res = Math.max(res, counts.get(entry.getKey() + 1) + entry.getValue());
            }
        }
        return res;
    }
}
