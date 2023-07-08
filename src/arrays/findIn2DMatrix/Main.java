package arrays.findIn2DMatrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int l = 0, r = matrix.length*matrix[0].length-1;
        int numCols = matrix[0].length;
        while (l < r) {
            int mid = (r + l) / 2;
            int pivot = matrix[mid/numCols][mid%numCols];
            if (target > pivot) {
                l = mid + 1;
            } else if (target < pivot) {
                r = mid - 1;
            } else return true;
        }
        return false;
    }
}
