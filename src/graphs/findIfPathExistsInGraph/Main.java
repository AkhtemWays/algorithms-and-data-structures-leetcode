package graphs.findIfPathExistsInGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[][] edges1 = {{0,1},{1,2},{2,0}};
        int[][] edges2 = {{0,1},{0,2},{3,5},{5,4},{4,3}};
        System.out.println(validPath(3, edges1, 0, 2));
        System.out.println(validPath(6, edges2, 0, 5));
    }

    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Set<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < n; i++) adjacency.add(new HashSet<>());

        for (int[] edge : edges) {
            adjacency.get(edge[0]).add(edge[1]);
            adjacency.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        return path(source, destination, visited, adjacency);
    }

    public static boolean path(int source, int destination, boolean[] visited, List<Set<Integer>> adjacency) {
        if (visited[source]) return false;

        visited[source] = true;
        if (source == destination) return true;

        for (int node : adjacency.get(source)) {
            if (path(node, destination, visited, adjacency)) return true;
        }
        return false;
    }
}
