package arrays.rotateImage;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        rotate(matrix);
        Arrays.stream(matrix).forEach(row -> System.out.println(Arrays.toString(row)));
        int[][] matrix2 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(matrix2);
        Arrays.stream(matrix2).forEach(row -> System.out.println(Arrays.toString(row)));
    }

    public static void rotate(int[][] matrix) {
        int left = 0, right = matrix.length-1;
        while (left < right) {
            int top = left, bottom = right;
            for (int i = 0; i < right-left; i++) {
                int topLeft = matrix[top][left+i];
                int topRight = matrix[top+i][right];
                int bottomRight = matrix[bottom][right-i];
                int bottomLeft = matrix[bottom-i][left];
                matrix[top+i][right] = topLeft;
                matrix[bottom][right-i] = topRight;
                matrix[bottom-i][left] = bottomRight;
                matrix[top][left+i] = bottomLeft;
            }
            left++;
            right--;
        }
    }
}
