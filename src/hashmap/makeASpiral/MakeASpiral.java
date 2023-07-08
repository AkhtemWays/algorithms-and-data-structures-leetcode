package hashmap.makeASpiral;

import java.util.Arrays;


public class MakeASpiral {
    public static int[][] spiralize(int size) {
        if (size < 5) return null;


        return spiralize(new int[size][size]);
    }

    private static int[][] fill(int[][] arr) {
        for (int[] row : arr) Arrays.fill(row, 1);
        return arr;
    }

    public static int[][] spiralize(int[][] arr) {
        int[][] fill = fill(arr);

        int height = fill.length;
        int width = fill[0].length;
        int lastY = height / 2;
        int lastX = width % 2 == 0 ? width / 2 - 1 : width / 2;

        if (height == 5) {
            lastX = 1;
            lastY = 3;
        } else if ((height - 5) % 4 == 0) {
            lastX--;
            lastY++;
        }

        int y = 1, x = 0, dirX = 1, dirY = 0, i = 0;
        int leftBorder = 0, rightBorder = width - 1;
        int upperBorder = 0, lowerBorder = height - 1;

        while (true) {
            if (x == rightBorder && y == upperBorder + 1) {
                x--;
                y++;
                dirX = 0;
                dirY = 1;
                upperBorder = upperBorder + 2;
            } else if (x == leftBorder && y == lowerBorder - 1) {
                x++;
                y--;
                dirX = 0;
                dirY = -1;
                lowerBorder -= 2;
            } else if (y == lowerBorder && x == rightBorder - 1) {
                y--;
                x--;
                dirX = -1;
                dirY = 0;
                rightBorder = rightBorder - 2;
            } else if (y == upperBorder && x == leftBorder + 1) {
                y++;
                x++;
                dirX = 1;
                dirY = 0;
                leftBorder = leftBorder + 2;
            }
            fill[y][x] = 0;

            if (y == lastY && x == lastX) break;


            x += dirX;
            y += dirY;
            i++;
        }

        return fill;
    }

}