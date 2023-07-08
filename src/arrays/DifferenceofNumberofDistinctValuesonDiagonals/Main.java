package arrays.DifferenceofNumberofDistinctValuesonDiagonals;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }

    private static int n;
    private static int m;
    public int[][] differenceOfDistinctValues(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int[][] answer = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int topleft = getDistinctValuesTopLeft(i,j, grid), bottomright = getDistinctValuesBottomRight(i,j,grid);
                answer[i][j] = Math.abs(topleft - bottomright);
            }
        }
        return answer;
    }

    private static int getDistinctValuesBottomRight(int y, int x, int[][] grid) {
        Set<Integer> unique = new HashSet<>();
        for (int i = y + 1, j = x + 1; isDefinedAt(i, j); i++, j++) {
            unique.add(grid[i][j]);
        }
        return unique.size();
    }

    private static int getDistinctValuesTopLeft(int y, int x, int[][] grid) {
        Set<Integer> unique = new HashSet<>();
        for (int i = y - 1, j = x - 1; isDefinedAt(i, j); i--, j--) {
            unique.add(grid[i][j]);
        }
        return unique.size();
    }

    private static boolean isDefinedAt(int i, int j) {
        return j >= 0 && j < m && i >= 0 && i < n;
    }
}
