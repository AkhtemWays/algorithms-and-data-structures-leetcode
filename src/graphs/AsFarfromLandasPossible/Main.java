package graphs.AsFarfromLandasPossible;

import java.util.Arrays;

public class Main {
    private static int answer;
    public static void main(String[] args) {
        int[][] grid = {{1,0,0},{0,0,0},{0,0,0}};
        System.out.println(maxDistance(grid));
    }

    public static int maxDistance(int[][] grid) {
        int n = grid.length;
        answer = Integer.MIN_VALUE;
        int[][] minDistances = new int[n][n];
        for (int[] minDistance : minDistances) Arrays.fill(minDistance, Integer.MAX_VALUE);
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    minDistances[i][j] = 0;
                    dfs(i, j, grid, deltas, n, minDistances, i, j);
                }
            }
        }

        return answer == Integer.MIN_VALUE || answer == 0 ? -1 : answer;
    }

    private static void dfs(int i, int j, int[][] grid, int[][] deltas, int n, int[][] minDistances, int o, int p) {
        for (int[] delta : deltas) {
            int x = j + delta[0];
            int y = i + delta[1];
            int distance = getDistance(new int[]{o, p}, new int[]{y, x});
            if (isDefinedAt(x, y, n) && minDistances[y][x] > distance && grid[y][x] != 1) {
                minDistances[y][x] = distance;
                answer = Math.max(answer, distance);
                dfs(y, x, grid, deltas, n, minDistances, o, p);
            }
        }
    }

    private static boolean isDefinedAt(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static int getDistance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
}
