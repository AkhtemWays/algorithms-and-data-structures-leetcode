package graphs.ModifyGraphEdgeWeights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static void test1() {
        int n = 5, source = 0, destination = 1, target = 5;
        int[][] edges = {{4,1,-1},{2,0,-1},{0,3,-1},{4,3,-1}};
        System.out.println(Arrays.deepToString(modifiedGraphEdges(n, edges, source, destination, target)));
    }
    private static void test2() {
        int n = 3, source = 0, destination = 2, target = 6;
        int[][] edges = {{0,1,-1},{0,2,5}};
        System.out.println(Arrays.deepToString(modifiedGraphEdges(n, edges, source, destination, target)));
    }
    private static void test3() {
        int n = 4, source = 0, destination = 2, target = 6;
        int[][] edges = {{1,0,4},{1,2,3},{2,3,5},{0,3,-1}};
        System.out.println(Arrays.deepToString(modifiedGraphEdges(n, edges, source, destination, target)));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        List<List<Integer>> paths = new ArrayList<>();
        dfs(source, destination, target, 0, graph, new ArrayList<>(), paths, new boolean[n]);
        if (paths.isEmpty()) return new int[3][0];

        for (List<Integer> path : paths) {
            int node = source;
            int i = 0;
            var curNeighbour = path.get(0);

        }
        return null;
    }

    private static void dfs(int node, int destination, int threshold, int totalWeight, List<int[]>[] graph, List<Integer> path, List<List<Integer>> paths, boolean[] visited) {
        if (node == destination) {
            if (totalWeight <= threshold) {
                paths.add(List.copyOf(path));
            }
            return;
        }
        visited[node] = true;

        for (int[] neighbour : graph[node]) {
            if (!visited[neighbour[0]]) {
                path.add(neighbour[0]);
                dfs(neighbour[0], destination, threshold, totalWeight + neighbour[1], graph, path, paths, visited);
                path.remove(path.size()-1);
            }
        }

        visited[node] = false;
    }
}
