package graphs.MinimumTimetoCollectAllApplesinaTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static int applesLeft = 0;
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple = new ArrayList<>(List.of(false,false,true,false,true,true,false));
        int[][] edges2 = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple2 = new ArrayList<>(List.of(false,false,true,false,false,true,false));
        System.out.println(minTime(7, edges, hasApple));
        System.out.println(minTime(7, edges2, hasApple2));
    }

    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        if (n == 1) return 0;
        Map<Integer, List<Integer>> adj = new HashMap<>();
        hasApple.stream().forEach(value -> {
            if (value) applesLeft++;
        });

        for (int[] edge : edges) {
            fillAdj(adj, edge[0], edge[1]);
            fillAdj(adj, edge[1], edge[0]);
        }

        int result = dfs(adj, 0, hasApple, new boolean[n])[0];
        return result == 0 ? result : result - 1;
    }

    private static int[] dfs(Map<Integer, List<Integer>> adj, int curNode, List<Boolean> hasApple, boolean[] visited) {
        if (applesLeft == 0) return new int[]{0, 0};
        int steps = 1;
        int applesFound = 0;
        visited[curNode] = true;
        if (hasApple.get(curNode)) {
            applesFound++;
            hasApple.set(curNode, false);
            applesLeft--;
        }
        for (int neighbor : adj.get(curNode)) {
            if (!visited[neighbor]) {
                int[] meta = dfs(adj, neighbor, hasApple, visited);
                if (meta[1] > 0) {
                    steps += 1 + meta[0];
                    applesFound += meta[1];
                }
            }
        }
        visited[curNode] = false;
        return new int[]{steps, applesFound};
    }

    private static void fillAdj(Map<Integer, List<Integer>> adj, int key, int value) {
        if (adj.containsKey(key)) {
            adj.get(key).add(value);
        } else {
            List<Integer> neighbors = new ArrayList<>();
            neighbors.add(value);
            adj.put(key, neighbors);
        }
    }
}
