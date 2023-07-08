package backtracking.UniquePathsIII;

import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] grid = {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,2,-1}};
        int[][] grid2 = {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,2}};
        System.out.println(uniquePathsIII(grid));
        System.out.println(uniquePathsIII(grid2));
    }

    private static int n;
    private static int m;
    private static int uniquePaths;
    private static final int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static class Meta {
        int numWalks;
        Point start;
        Point finish;
        Meta(int numWalks, Point start, Point finish) {
            this.numWalks = numWalks;
            this.start = start;
            this.finish = finish;
        }
    }
    public static int uniquePathsIII(int[][] grid) {
        uniquePaths = 0;
        n = grid.length;
        m = grid[0].length;
        Meta meta = getMeta(grid);
        boolean[][] visited = new boolean[n][m];
        visited[meta.start.x][meta.start.y] = true;
        dfs(0, meta.start, meta.finish, visited, grid, meta.numWalks+1);
        return uniquePaths;
    }

    private static void dfs(int numWalks, Point cur, Point finish, boolean[][] visited, int[][] grid, int targetNumWalks) {
        if (cur.x == finish.x && cur.y == finish.y) {
            if (numWalks == targetNumWalks) {
                uniquePaths++;
            }
            return;
        }

        Arrays.stream(deltas).forEach(delta -> {
            Point point = new Point(cur.x - delta[0], cur.y - delta[1]);
            if (isDefinedAt(point) && !visited[point.x][point.y] && grid[point.x][point.y] != -1) {
                visited[point.x][point.y] = true;
                dfs(numWalks + 1, point, finish, visited, grid, targetNumWalks);
                visited[point.x][point.y] = false;
            }
        });
    }

    private static boolean isDefinedAt(Point point) {
        return point.x < n && point.x >= 0 && point.y < m && point.y >= 0;
    }

    private static Meta getMeta(int[][] grid) {
        Point start = null, finish = null;
        int numWalks = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    start = new Point(i, j);
                } else if (grid[i][j] == 0) {
                    numWalks++;
                } else if (grid[i][j] == 2) {
                    finish = new Point(i, j);
                }
            }
        }
        return new Meta(numWalks, start, finish);
    }
}
