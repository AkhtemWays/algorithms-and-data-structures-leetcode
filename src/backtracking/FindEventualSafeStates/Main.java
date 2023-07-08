package backtracking.FindEventualSafeStates;

import java.util.*;

enum Color {
    WHITE, BLACK, GRAY, GREEN
}

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        main.eventualSafeNodes(graph).forEach(System.out::println);
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> safeNodes = new ArrayList<>();
        Color[] colors = initColors(graph.length);
        for (int node = 0; node < graph.length; node++) {
            if (colors[node] == Color.WHITE) {
                dfs(node, safeNodes, graph, colors);
            }
        }

        Collections.sort(safeNodes);
        return safeNodes;
    }

    private boolean dfs(int node, List<Integer> safeNodes, int[][] graph, Color[] colors) {
        if (colors[node] == Color.GRAY || colors[node] == Color.BLACK) return false;
        colors[node] = Color.GRAY;

        if (graph[node].length == 0) {
            if (colors[node] != Color.GREEN) {
                safeNodes.add(node);
                colors[node] = Color.GREEN;
            }
            return true;
        }

        boolean allNodesHavePath = true;
        for (int neighbor : graph[node]) {
            if (colors[neighbor] == Color.WHITE) {
                boolean isSafeNode = dfs(neighbor, safeNodes, graph, colors);
                if (!isSafeNode) {
                    allNodesHavePath = false;
                }
            }
            else if (colors[neighbor] == Color.GRAY || colors[neighbor] == Color.BLACK) {
                allNodesHavePath = false;
            }
        }

        if (allNodesHavePath) colors[node] = Color.GREEN;
        else colors[node] = Color.BLACK;

        if (colors[node] == Color.GREEN) safeNodes.add(node);

        return colors[node] == Color.GREEN;
    }

    private static Color[] initColors(int numCourses) {
        Color[] colors = new Color[numCourses];
        for (int i = 0; i < numCourses; i++) colors[i] = Color.WHITE;
        return colors;
    }

}
