package arrays.FindColumnWidth;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] input = {{1},{22},{333}};
        int[][] input2 = {{-15,1,3},{15,7,12},{5,6,-2}};
        System.out.println(Arrays.toString(findColumnWidth(input)));
        System.out.println(Arrays.toString(findColumnWidth(input2)));
    }

    public static int[] findColumnWidth(int[][] grid) {
        int[] result = new int[grid[0].length];
        for (int j = 0; j < grid[0].length; j++) {
            int maxLength = Integer.MIN_VALUE;
            for (int i = 0; i < grid.length; i++) {
                int value = String.valueOf(grid[i][j]).length();
                if (value > maxLength) {
                    maxLength = value;
                }
            }
            result[j] = maxLength;
        }
        return result;
    }
}
