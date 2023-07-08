package graphs.numberOfIslands;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        char[][] grid2 = {{'0','1','0'},
                          {'1','0','1'},
                          {'0','1','0'}};
//        System.out.println(numIslands(grid));
        System.out.println(numIslands(grid2));
    }

    public static int numIslands(char[][] grid) {
        Set<Point> connectedComponents = new HashSet<>();
        int numIslands = 0;
        Point beginningOfIsland = findBeginningOfIsland(new Point(0, 0), grid, connectedComponents);
        while (beginningOfIsland != null) {
            numIslands++;
            findIslands(beginningOfIsland.x, beginningOfIsland.y, grid, connectedComponents);
            beginningOfIsland = findBeginningOfIsland(beginningOfIsland, grid, connectedComponents);
        }

        return numIslands;
    }

    private static void findIslands(int i, int j, char[][] grid, Set<Point> connectedComponents) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) return;

        Point point = new Point(i, j);
        if (grid[i][j] == '1' && !connectedComponents.contains(point)) {
            connectedComponents.add(point);
            findIslands(i, j+1, grid, connectedComponents);
            findIslands(i, j-1, grid, connectedComponents);
            findIslands(i-1, j, grid, connectedComponents);
            findIslands(i+1, j, grid, connectedComponents);
        }
    }

    private static Point findBeginningOfIsland(Point start, char[][] grid, Set<Point> connectedComponents) {
        for (int k = start.y; k < grid[start.x].length; k++) {
            if (grid[start.x][k] == '1' && !connectedComponents.contains(new Point(start.x, k))) return new Point(start.x, k);
        }
        for (int i = start.x+1; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !connectedComponents.contains(new Point(i, j))) return new Point(i, j);
            }
        }
        return null;
    }
}
