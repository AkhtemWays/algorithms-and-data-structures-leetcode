package graphs.NumberofClosedIslands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static void test1() {
        int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        System.out.println(closedIsland(grid));
    }
    private static void test2() {
        int[][] grid = {{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}};
        System.out.println(closedIsland(grid));
    }
    private static void test3() {
        int[][] grid = {{1,1,1,1,1,1,1},
        {1,0,0,0,0,0,1},
        {1,0,1,1,1,0,1},
        {1,0,1,0,1,0,1},
        {1,0,1,1,1,0,1},
        {1,0,0,0,0,0,1},
        {1,1,1,1,1,1,1}};
        System.out.println(closedIsland(grid));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static int n;
    private static int m;
    private static final int[][] deltas = {{0,1},{0,-1},{-1,0},{1,0}};
    private static class Point {
        int i;
        int j;
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
        @Override
        public boolean equals(Object o) {
            if (o == null || o.hashCode() != this.hashCode()) return false;
            if (o == this) return true;
            Point point = (Point) o;
            return this.i == point.i && this.j == point.j;
        }
    }

    public static int closedIsland(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        List<List<int[]>> components = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 0) {
                    List<int[]> component = new ArrayList<>();
                    dfs(i, j, grid, component, visited);
                    components.add(component);
                }
            }
        }

        int answer = 0;
        for (List<int[]> component : components) {
            if (component.stream().allMatch(Main::check)) answer++;
        }

        return answer;
    }

    private static boolean check(int[] point) {
        return Arrays.stream(deltas).allMatch(delta -> isDefinedAt(point[0] - delta[0], point[1] - delta[1]));
    }

    private static void dfs(int i, int j, int[][] grid, List<int[]> component, boolean[][] visited) {
        visited[i][j] = true;
        component.add(new int[]{i, j});
        for (int[] delta : deltas) {
            int curI = i - delta[0];
            int curJ = j - delta[1];
            if (isDefinedAt(curI, curJ) && grid[curI][curJ] == 0 && !visited[curI][curJ]) {
                dfs(curI, curJ, grid, component, visited);
            }
        }
    }

    private static boolean isDefinedAt(int i, int j) {
        return i < n && j < m && i >= 0 && j >= 0;
    }
}
