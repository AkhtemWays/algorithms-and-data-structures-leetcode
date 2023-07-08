package disjointSets_UnionFind.TreeDiameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] edges = {{0,1},{1,2},{2,3},{1,4},{4,5}};
        int[][] edges2 = {{0,1},{0,2}};
        System.out.println(main.treeDiameter(edges));
        System.out.println(main.treeDiameter(edges2));
    }

    public int treeDiameter(int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < edges.length+1; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int[] diameters = new int[adj.size()];
        boolean[] visited = new boolean[adj.size()];

        for (int node = 0; node < adj.size(); node++) {
            diameters[node] = dfs(node, visited, adj, 0);
        }
        return Arrays.stream(diameters).max().getAsInt();
    }

    private int dfs(int node, boolean[] visited, List<List<Integer>> adj, int count) {
        visited[node] = true;
        int maxCount = count;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                int newCount = dfs(neighbor, visited, adj, count + 1);
                if (newCount > maxCount) maxCount = newCount;
            }
        }

        visited[node] = false;
        return maxCount;
    }
}
