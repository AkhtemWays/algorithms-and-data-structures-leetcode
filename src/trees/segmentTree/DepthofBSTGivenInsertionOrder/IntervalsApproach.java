package trees.segmentTree.DepthofBSTGivenInsertionOrder;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

public class IntervalsApproach {
    public static void main(String[] args) {
        int[] input = {4, 2, 6, 1, 10, -1, 12, 3, 8, 7};
        int[] input2 = {2,1,4,3};
        System.out.println(maxDepthBST(input));
        System.out.println(maxDepthBST(input2));
    }

    public static int maxDepthBST(int[] order) {
        int n = order.length, maxDepth = 1;

        // now, these are the smallest values (inclusive) for each interval:
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 0);

        for (int i = 0; i < n; i++) {
            int val = order[i];

            // we're looking for the greatest value below our queried value:
            Map.Entry<Integer, Integer> lowBound = map.floorEntry(val);
            int bound = lowBound.getKey();
            int depth = lowBound.getValue() + 1;

            maxDepth = Math.max(depth, maxDepth);

            if (bound < val - 1)
                map.replace(bound, depth);

            map.put(val, depth);
        }

        return maxDepth;
    }
}
