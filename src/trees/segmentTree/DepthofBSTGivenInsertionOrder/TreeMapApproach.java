package trees.segmentTree.DepthofBSTGivenInsertionOrder;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapApproach {
    public static void main(String[] args) {
        int[] input = {4, 2, 6, 1, 10, -1, 12, 3, 8, 7};
        int[] input2 = {2,1,4,3};
        System.out.println(maxDepthBST(input));
        System.out.println(maxDepthBST(input2));
    }

    public static int maxDepthBST(int[] order) {
        if (order.length == 0) return 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int maxDepth = 1;
        for (int i = 0; i < order.length; i++) {
            int val = order[i];
            int leftDepth = 0, rightDepth = 0;

            Map.Entry<Integer, Integer> biggerEntry = map.ceilingEntry(val);
            Map.Entry<Integer, Integer> smallerEntry = map.floorEntry(val);
            if (smallerEntry != null) leftDepth = smallerEntry.getValue();
            if (biggerEntry != null) rightDepth = biggerEntry.getValue();

            int curDepth = Math.max(leftDepth, rightDepth) + 1;
            maxDepth = Math.max(maxDepth, curDepth);
            map.put(val, curDepth);
        }
        return maxDepth;
    }
}
