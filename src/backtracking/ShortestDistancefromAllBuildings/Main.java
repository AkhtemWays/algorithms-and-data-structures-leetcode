package backtracking.ShortestDistancefromAllBuildings;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(shortestDistance(grid));
    }

    private static int n;
    private static int m;
    private static final int[][] deltas = {{0,-1},{0,1},{1,0},{-1,0}};
    public static int shortestDistance(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    answer = Math.min(answer, dijkstra(new int[]{i, j}, grid));
                }
            }
        }
        return answer;
    }

    private static int dijkstra(int[] start, int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        int[][] optimalDistances = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(optimalDistances[i], -1);
        int answer = 0;
        int distance = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] point = q.poll();
                int x = point[0];
                int y = point[1];
                for (int[] delta : deltas) {
                    int i = x + delta[0];
                    int j = y + delta[1];
                    if (isDefinedAt(i, j) && grid[i][j] != 2 && optimalDistances[i][j] == -1) {
                        optimalDistances[i][j] = distance + 1;
                        if (grid[i][j] == 1) answer += optimalDistances[i][j];
                        q.add(new int[]{i, j});
                    }
                }
            }
            distance++;
        }
        return answer;
    }

    private static boolean isDefinedAt(int i, int j) {
        return i < n && i >= 0 && j < m && j >= 0;
    }
}
