package graphs.Tarjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {2, 0}, {0, 3}, {3, 4}, {3, 5}};
        System.out.println(findAPs(6, edges));
    }

    private static int time = 0;

    public static List<Integer> findAPs(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            if (adj[edge[0]] == null) adj[edge[0]] = new ArrayList<>();
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        return tarjan(adj);
    }

    private static List<Integer> tarjan(List<Integer>[] adj) {
        int n = adj.length;
        time = 0;
        int[] disc = new int[n], low = new int[n], parent = new int[n];
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);
        boolean[] ap = new boolean[n];
        List<Integer> result = new ArrayList<>();

        dfs(0, disc, low, parent, adj, ap);
        return result;
    }

    private static void dfs(int u, int[] disc, int[] low, int[] parent, List<Integer>[] adj, boolean[] ap) {
        low[u] = disc[u] = time++;
        int children = 0;

        for (int v : adj[u]) {
            if (disc[v] == -1) {
                parent[v] = u;
                children += 1;
                dfs(v, disc, low, parent, adj, ap);
                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children>1) ap[u] = true;
                if (parent[u] != -1 && low[v]>=disc[u]) ap[u] = true;
            }
            else if (parent[u] != v) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
