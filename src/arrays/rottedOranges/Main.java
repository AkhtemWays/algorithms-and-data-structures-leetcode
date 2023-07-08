package arrays.rottedOranges;

import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] grid1 = {{2,1,1},{1,1,0},{0,1,1}};
        int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}};
        int[][] grid3 = {{0, 2}};
        int[][] grid4 = {{1}};
        int[][] grid5 = {{0}};
        System.out.println(orangesRotting(grid1));
        System.out.println(orangesRotting(grid2));
        System.out.println(orangesRotting(grid3));
        System.out.println(orangesRotting(grid4));
        System.out.println(orangesRotting(grid5));
    }

    public static int orangesRotting(int[][] grid) {
        Queue<Point> rottenOrangesPositions = new LinkedList<>();
        int freshOrangesCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) rottenOrangesPositions.add(new Point(i, j));
                else if (grid[i][j] == 1) freshOrangesCount++;
            }
        }
        if (rottenOrangesPositions.isEmpty()) {
            if (freshOrangesCount > 0) return -1;
            else return 0;
        }
        int[][] ranges = new int[grid.length][grid[0].length];
        for (int i = 0; i < ranges.length; i++) Arrays.fill(ranges[i], Integer.MAX_VALUE);
        while (!rottenOrangesPositions.isEmpty()) {
            Point point = rottenOrangesPositions.poll();
            ranges[point.x][point.y] = 0;
            dfs(point, grid, ranges);
        }

        return getMin(ranges, grid);
    }

    private static int getMin(int[][] ranges, int[][] grid) {
        int minutes = -1;
        for (int i = 0; i < ranges.length; i++) {
            for (int j = 0; j < ranges[i].length; j++) {
                if (grid[i][j] == 1 && ranges[i][j] == Integer.MAX_VALUE) return -1;
                if (ranges[i][j] > minutes && grid[i][j] != 0) minutes = ranges[i][j];
            }
        }
        return minutes;
    }

    private static void dfs(Point point, int[][] grid, int[][] ranges) {
        if (point.x + 1 < grid.length && ranges[point.x][point.y]+1 < ranges[point.x+1][point.y] && grid[point.x+1][point.y] != 2 && grid[point.x+1][point.y] != 0) {
            ranges[point.x+1][point.y] = ranges[point.x][point.y]+1;
            dfs(new Point(point.x+1, point.y), grid, ranges);
        }
        if (point.y + 1 < grid[0].length && ranges[point.x][point.y]+1 < ranges[point.x][point.y+1] && grid[point.x][point.y+1] != 2 && grid[point.x][point.y+1] != 0) {
            ranges[point.x][point.y+1] = ranges[point.x][point.y]+1;
            dfs(new Point(point.x, point.y+1), grid, ranges);
        }
        if (point.y - 1 >= 0 && ranges[point.x][point.y]+1 < ranges[point.x][point.y-1] && grid[point.x][point.y-1] != 2 && grid[point.x][point.y-1] != 0) {
            ranges[point.x][point.y-1] = ranges[point.x][point.y]+1;
            dfs(new Point(point.x, point.y-1), grid, ranges);
        }
        if (point.x - 1 >= 0 && ranges[point.x][point.y]+1 < ranges[point.x-1][point.y] && grid[point.x-1][point.y] != 2 && grid[point.x-1][point.y] != 0) {
            ranges[point.x-1][point.y] = ranges[point.x][point.y]+1;
            dfs(new Point(point.x-1, point.y), grid, ranges);
        }
    }
}
