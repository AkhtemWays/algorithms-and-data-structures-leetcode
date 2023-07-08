package math.ProjectionAreaOf3DShapes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] shapes = {{1,2},{3,4}};
        Main main = new Main();
        System.out.println(main.projectionArea(shapes));
    }

    public int projectionArea(int[][] grid) {
        int[] yProjections = new int[grid.length];
        int[] zProjections = new int[grid.length];
        int yProjectionsSum = 0;
        int xProjectionsSum = 0;
        int zProjectionsSum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int val = grid[i][j];
                if (val > 0) {
                    xProjectionsSum++;
                    if (val > yProjections[i]) {
                        yProjectionsSum += (val - yProjections[i]);
                        yProjections[i] = val;
                    }
                    if (val > zProjections[j]) {
                        zProjectionsSum += (val - zProjections[j]);
                        zProjections[j] = val;
                    }
                }
            }
        }
        return xProjectionsSum + zProjectionsSum + yProjectionsSum;
    }
}
