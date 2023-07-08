package arrays.SpiralMatrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SecondApproach {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int up = 0;
        int left = 0;
        int right = matrix[0].length - 1;
        int down = matrix.length - 1;
        while (res.size() < matrix.length * matrix[0].length) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            for (int j = up + 1; j <= down; j++) {
                res.add(matrix[j][right]);
            }
            for (int i = right - 1; i >= left; i--) {
                res.add(matrix[down][i]);
            }
            for (int i = down - 1; i > up; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}
