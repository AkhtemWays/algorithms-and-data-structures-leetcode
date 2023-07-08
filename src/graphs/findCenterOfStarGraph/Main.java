package graphs.findCenterOfStarGraph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[][] edges = {{1,2},{5,1},{1,3},{1,4}};
        System.out.println(findCenter(edges));
    }

    public static int findCenter(int[][] edges) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            if (counts.containsKey(node1)) counts.put(node1, counts.get(node1) + 1);
            else counts.put(node1, 1);
            if (counts.containsKey(node2)) counts.put(node2, counts.get(node2) + 1);
            else counts.put(node2, 1);
        }
        return counts.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }
}
