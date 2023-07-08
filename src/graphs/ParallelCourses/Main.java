package graphs.ParallelCourses;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] relations1 = {{1,3},{2,3}};
        int[][] relations2 = {{1,2},{2,3},{3,1}};
        int[][] relations3 = {{1,5}, {2,3}, {2,5}, {2,4}, {2,1}, {5,3}};
        System.out.println(main.minimumSemesters(3, relations1));
        System.out.println(main.minimumSemesters(3, relations2));
        System.out.println(main.minimumSemesters(5, relations3));
    }

    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n+1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] relation : relations) {
            adj.get(relation[0]).add(relation[1]);
        }
        int[] semesters = new int[n+1];
        Arrays.fill(semesters, 1);
        Color[] colors = new Color[n+1];
        Arrays.fill(colors, Color.WHITE);

        for (int prerequisite = 1; prerequisite < n+1; prerequisite++) {
            for (int course : adj.get(prerequisite)) {
                dfs(course, colors, semesters, adj, 2);
            }
        }

        return getMinSemesters(semesters);
    }

    private static void dfs(int prerequisite, Color[] colors, int[] semesters, List<List<Integer>> adj, int semesterCount) {
        if (colors[prerequisite] == Color.GRAY) {
            semesters[prerequisite] = -1;
        }
        else if ((colors[prerequisite] == Color.BLACK && semesterCount < semesters[prerequisite]) || colors[prerequisite] == Color.WHITE) {
            colors[prerequisite] = Color.GRAY;
            semesters[prerequisite] = semesterCount;
            for (int course : adj.get(prerequisite)) {
                dfs(course, colors, semesters, adj, semesterCount + 1);
            }
            colors[prerequisite] = Color.BLACK;
        }
    }

    private int getMinSemesters(int[] semesters) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < semesters.length; i++) {
            if (semesters[i] == -1) return -1;
            if (semesters[i] > max) max = semesters[i];
        }
        return max;
    }

    private enum Color {
        WHITE, GRAY, BLACK
    }
}
