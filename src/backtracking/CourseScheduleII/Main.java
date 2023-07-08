package backtracking.CourseScheduleII;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}};
        int[][] prerequisites2 = {{1,0},{2,0},{3,1},{3,2}};
        int[][] prerequisites3 = {};
        int[][] prerequisites4 = {{0,1},{1,0}};
        System.out.println(Arrays.toString(findOrder(2, prerequisites)));
        System.out.println(Arrays.toString(findOrder(4, prerequisites2)));
        System.out.println(Arrays.toString(findOrder(1, prerequisites3)));
        System.out.println(Arrays.toString(findOrder(2, prerequisites3)));
        System.out.println(Arrays.toString(findOrder(2, prerequisites4)));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return new int[0];

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
        }
        Stack<Integer> stack = new Stack<>();
        Color[] colors = initColors(numCourses);
        for (int course = 0; course < numCourses; course++) {
            if (colors[course] == Color.WHITE) {
                if (!dfs(course, colors, adj, stack)) return new int[0];
            }
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) res[i] = stack.pop();
        return res;
    }

    private static boolean dfs(int prerequisite, Color[] colors, List<List<Integer>> adj, Stack<Integer> stack) {
        colors[prerequisite] = Color.GRAY;

        for (int course : adj.get(prerequisite)) {
            if (colors[course] == Color.WHITE) {
                if (!dfs(course, colors, adj, stack)) return false;
            } else if (colors[course] == Color.GRAY) return false;
        }

        colors[prerequisite] = Color.WHITE;
        stack.add(prerequisite);
        return true;
    }

    private static Color[] initColors(int numCourses) {
        Color[] colors = new Color[numCourses];
        for (int i = 0; i < numCourses; i++) colors[i] = Color.WHITE;
        return colors;
    }

    private enum Color {
        WHITE, BLACK, GRAY
    }
}
