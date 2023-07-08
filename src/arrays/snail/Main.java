package arrays.snail;

import java.util.ArrayList;
import java.util.List;

public class Main {
    enum Direction {
        RIGHT, LEFT, BOTTOM, UP
    }
    public static void main(String[] args) {
        int[][] array = {{}};
        for (int num : snail(array)) System.out.println(num);
    }

    public static int[] snail(int[][] array) {
        if (array[0].length == 0) return new int[0];
        int[] res = new int[array.length * array[0].length];
        boolean[][] visited = new boolean[array.length][array[0].length];
        int i = 0, j = 0, k = 0;
        visited[i][j] = true;
        Direction direction = Direction.RIGHT;
        res[k] = array[i][j];
        while (k < res.length - 1) {
            if (direction == Direction.RIGHT) {
                if (j + 1 < array[0].length && !visited[i][j+1]) {
                    visited[i][++j] = true;
                    res[++k] = array[i][j];
                }
                else direction = Direction.BOTTOM;
            }
            else if (direction == Direction.BOTTOM) {
                if (i + 1 < array.length && !visited[i+1][j]) {
                    visited[++i][j] = true;
                    res[++k] = array[i][j];
                }
                else direction = Direction.LEFT;
            }
            else if (direction == Direction.LEFT) {
                if (j - 1 >= 0 && !visited[i][j-1]) {
                    visited[i][--j] = true;
                    res[++k] = array[i][j];
                }
                else direction = Direction.UP;
            }
            else {
                if (i - 1 >= 0 && !visited[i-1][j]) {
                    visited[--i][j] = true;
                    res[++k] = array[i][j];
                }
                else direction = Direction.RIGHT;
            }
        }
        return res;
    }
}
