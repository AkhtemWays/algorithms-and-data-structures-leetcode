package hashmap.setMatrixZeroes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void setZeroes(int[][] matrix) {
        Set<String> coordinates = new HashSet<>();
        dfs(matrix, 0, 0, matrix.length, matrix[0].length, coordinates);
        for (String coordinate : coordinates) {
            int row = Character.getNumericValue(coordinate.charAt(0));
            int col = Character.getNumericValue(coordinate.charAt(1));
            for (int j = 0; j < matrix[row].length; j++) {
                matrix[row][j] = 0;
            }
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][col] = 0;
            }
        }
    }

    public static void dfs(int[][] matrix, int i, int j, int n, int m, Set<String> coordinates) {
        if (i == n) return;

        if (j != m) {
            if (matrix[i][j] == 0) {
                coordinates.add(i + String.valueOf(j));
            }
            dfs(matrix, i, j + 1, n, m, coordinates);
        }
        else dfs(matrix, i + 1, 0, n, m, coordinates);
    }
}
