package graphs.Tarjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfSCCs {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {3, 4}, {4, 5}, {4, 0}};
        System.out.println(numSccs(6, edges));
    }
    private static int time = 0;

    public static int numSccs(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            if (adj[edge[0]] == null) adj[edge[0]] = new ArrayList<>();
            adj[edge[0]].add(edge[1]);
        }

        return tarjan(adj);
    }

    private static int tarjan(List<Integer>[] adj) {
        int n = adj.length;
        time = 0;
        int[] disc = new int[n], low = new int[n];
        boolean[] inStack = new boolean[n];
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);

        for (int node = 0; node < n; node++) {
            if (disc[node] == -1) {
                dfs(node, disc, low, inStack, adj);
            }
        }
        return (int) Arrays.stream(low).distinct().count();
    }

    private static void dfs(int u, int[] disc, int[] low, boolean[] inStack, List<Integer>[] adj) {
        disc[u] = time++;
        low[u] = disc[u];
        inStack[u] = true;

        for (int v : adj[u]) {
            if (disc[v] == -1) {
                dfs(v, disc, low, inStack, adj);
                low[u] = Math.min(low[v], low[u]);
            } else if (inStack[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        inStack[u] = false;
    }
}
