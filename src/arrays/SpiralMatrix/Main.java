package arrays.SpiralMatrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        Direction direction = Direction.RIGHT;
        List<Integer> res = new ArrayList<>();
        Point pos = new Point(0, -1);
        while (res.size() < matrix[0].length * matrix.length) {
            if (isSafeMove(direction, pos, matrix)) {
                move(direction, pos);
                res.add(matrix[pos.x][pos.y]);
                matrix[pos.x][pos.y] = 1000;
            } else {
                direction = changeDirection(direction);
            }
        }
        return res;
    }

    private static void move(Direction direction, Point pos) {
        if (direction == Direction.RIGHT) pos.y++;
        else if (direction == Direction.LEFT) pos.y--;
        else if (direction == Direction.UP) pos.x--;
        else pos.x++;
    }

    private static Direction changeDirection(Direction direction) {
        if (direction == Direction.RIGHT) {
            return Direction.DOWN;
        } else if (direction == Direction.DOWN) {
            return Direction.LEFT;
        } else if (direction == Direction.LEFT) {
            return Direction.UP;
        } else {
            return Direction.RIGHT;
        }
    }

    private static boolean isSafeMove(Direction direction, Point pos, int[][] matrix) {
        return nextPositionExists(direction, pos, matrix) && nextPositionWasntStepped(direction, pos, matrix);
    }

    private static boolean nextPositionWasntStepped(Direction direction, Point pos, int[][] matrix) {
        if (direction == Direction.RIGHT) return matrix[pos.x][pos.y+1] != 1000;
        if (direction == Direction.LEFT) return matrix[pos.x][pos.y-1] != 1000;
        if (direction == Direction.UP) return matrix[pos.x-1][pos.y] != 1000;
        return matrix[pos.x+1][pos.y] != 1000;
    }

    private static boolean nextPositionExists(Direction direction, Point pos, int[][] matrix) {
        if (direction == Direction.RIGHT) return pos.y + 1 < matrix[0].length;
        if (direction == Direction.LEFT) return pos.y - 1 >= 0;
        if (direction == Direction.UP) return pos.x - 1 >= 0;
        return pos.x + 1 < matrix.length;
    }
}
