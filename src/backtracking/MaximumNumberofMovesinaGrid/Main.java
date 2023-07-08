package backtracking.MaximumNumberofMovesinaGrid;

import java.util.Arrays;

public class Main {
    private static void test1() {
        int[][] grid = {{2,4,3,5},{5,4,9,3},{3,4,2,11},{10,9,13,15}};
        System.out.println(maxMoves(grid));
    }
    public static void main(String[] args) {
        test1();
    }

    private static int n;
    private static int m;
    private static final int[] deltas = {-1,0,1};
    public static int maxMoves(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int[][] memo = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(memo[i], -1);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(dfs(i, 0, grid, memo), answer);
        }
        return answer;
    }

    private static int dfs(int i, int j, int[][] grid, int[][] memo) {
        if (memo[i][j] != -1) return memo[i][j];
        int answer = 0;
        for (int delta : deltas) {
            int curJ = j + 1;
            int curI = i + delta;
            if (isDefinedAt(curI, curJ) && grid[curI][curJ] > grid[i][j]) {
                answer = Math.max(answer, 1 + dfs(curI, curJ, grid, memo));
            }
        }

        return memo[i][j] = answer;
    }

    private static boolean isDefinedAt(int i, int j) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }
}
