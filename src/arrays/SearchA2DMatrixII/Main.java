package arrays.SearchA2DMatrixII;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(searchMatrix(matrix, 40));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        return find(0, 0, matrix, target);
    }

    private static boolean find(int i, int j, int[][] matrix, int target) {
        if (i == matrix.length || j == matrix[i].length) return false;
        if (rowBinarySearch(i, i, matrix[i].length, matrix, target)
                || columnBinarySearch(j, j, matrix.length, matrix, target)) return true;
        return find(i+1, j+1, matrix, target);
    }

    private static boolean rowBinarySearch(int i, int l, int r, int[][] matrix, int target) {
        while (l < r) {
            int m = (l + r) / 2;
            if (matrix[i][m] < target) l = m + 1;
            else r = m;
        }
        return l < matrix[i].length && matrix[i][l] == target;
    }

    private static boolean columnBinarySearch(int j, int l, int r, int[][] matrix, int target) {
        while (l < r) {
            int m = (l + r) / 2;
            if (matrix[m][j] < target) l = m + 1;
            else r = m;
        }
        return l < matrix.length && matrix[l][j] == target;
    }
}
