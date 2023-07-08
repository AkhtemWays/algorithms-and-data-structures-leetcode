package graphs.MinimumTimetoCollectAllApplesinaTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondApproach {

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple = new ArrayList<>(List.of(false,false,true,false,true,true,false));
        int[][] edges2 = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple2 = new ArrayList<>(List.of(false,false,true,false,false,true,false));
        System.out.println(minTime(7, edges, hasApple));
        System.out.println(minTime(7, edges2, hasApple2));
    }

    public static int dfs(int node, int parent, Map<Integer, List<Integer>> adj,
                   List<Boolean> hasApple) {
        if (!adj.containsKey(node))
            return 0;

        int totalTime = 0;
        for (int child : adj.get(node)) {
            if (child == parent)
                continue;
            int childTime = dfs(child, node, adj, hasApple);
            // childTime > 0 indicates subtree of child has apples. Since the root node of the
            // subtree does not contribute to the time, even if it has an apple, we have to check it
            // independently.
            if (childTime > 0 || hasApple.get(child))
                totalTime += childTime + 2;
        }
        return totalTime;
    }

    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            adj.computeIfAbsent(a, value -> new ArrayList<>()).add(b);
            adj.computeIfAbsent(b, value -> new ArrayList<>()).add(a);
        }
        return dfs(0, -1, adj, hasApple);
    }
}
