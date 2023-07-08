package graphs.Tarjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindBridgesInUndirectedGraph {

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}, {0, 3}, {3, 4}};
        System.out.println(findBridges(5, edges));
    }
    private static int time = 0;

    public static List<List<Integer>> findBridges(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            if (adj[edge[0]] == null) adj[edge[0]] = new ArrayList<>();
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        return tarjan(adj);
    }

    private static List<List<Integer>> tarjan(List<Integer>[] adj) {
        int n = adj.length;
        time = 0;
        int[] disc = new int[n], low = new int[n], parent = new int[n];
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);
        List<List<Integer>> result = new ArrayList<>();

        dfs(0, disc, low, parent, adj, result);
        return result;
    }

    private static void dfs(int u, int[] disc, int[] low, int[] parent, List<Integer>[] adj, List<List<Integer>> result) {
        disc[u] = time++;
        low[u] = disc[u];

        for (int v : adj[u]) {
            if (disc[v] == -1) {
                parent[v] = u;
                dfs(v, disc, low, parent, adj, result);
                if (disc[u] < low[v]) {
                    result.add(List.of(v, u));
                } else {
                    low[u] = Math.min(low[u], low[v]);
                }
            }
            else if (parent[u] != v) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
