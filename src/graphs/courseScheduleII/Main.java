package graphs.courseScheduleII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private boolean canFinish = true;
    public static void main(String[] args) {
        Main main = new Main();
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[][] prerequisites2 = {{1, 0}};
        System.out.println(Arrays.toString(main.findOrder(4, prerequisites)));
        System.out.println(Arrays.toString(main.findOrder(2, prerequisites2)));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = buildAdjacencyList(prerequisites, numCourses);
        List<Integer> ordering = new ArrayList<>();
        Color[] colors = new Color[numCourses];
        for (int i = 0; i < numCourses; i++) colors[i] = Color.WHITE;
        for (int course = 0; course < numCourses; course++) {
            if (colors[course] == Color.WHITE) {
                dfs(course, ordering, adj, colors);
            }
        }
        if (ordering.size() != numCourses || !canFinish) return new int[0];
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) result[i] = ordering.get(i);
        return result;
    }

    private void dfs(int course, List<Integer> ordering, List<List<Integer>> adj, Color[] colors) {
        colors[course] = Color.GRAY;

        for (int prerequisite : adj.get(course)) {
            if (colors[prerequisite] == Color.GRAY) {
                canFinish = false;
                return;
            } else if (colors[prerequisite] == Color.WHITE) {
                dfs(prerequisite, ordering, adj, colors);
            }
        }

        colors[course] = Color.BLACK;
        ordering.add(course);
    }

    private List<List<Integer>> buildAdjacencyList(int[][] prerequisites, int numCourses) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());

        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[0]).add(prerequisite[1]);
        }
        return adj;
    }

    private enum Color {
        GRAY, WHITE, BLACK
    }
}
