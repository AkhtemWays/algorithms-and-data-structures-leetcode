package backtracking.findTheCheapestPath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    private static void printOptimalPath(long[][] optimalPath) {
        for (int i = 0; i < optimalPath.length; i++) {
            System.out.print("[");
            for (int j = 0; j < optimalPath[0].length; j++) {
                System.out.print(optimalPath[i][j] + ", ");
            }
            System.out.print("]");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] maze1 = {
                {1, 1, 9},
                {9, 1, 9},
                {9, 9, 1}
        };
        int[][] maze2 = {
                {1,9,1},
                {1,9,1},
                {1,1,1}
        };
        int[][] maze3 = {
                {1,4,1},
                {1,9,1},
                {1,1,1}
        };
//        cheapestPath(maze1, new Point(0, 0), new Point(2, 2)).forEach(System.out::println);
        cheapestPath(maze2, new Point(0, 0), new Point(0, 2)).forEach(System.out::println);
//        cheapestPath(maze3, new Point(1, 1), new Point(1, 1)).forEach(System.out::println);
    }
    static List<String> cheapestPath(int[][] t, Point start, Point finish) {
        if (start.x == finish.x && start.y == finish.y) return null;
        long[][] optimalPath = getOptimalPath(t);
        return traverseOptimalPath(optimalPath, finish);
    }

    private static long[][] getOptimalPath(int[][] t) {
        long[][] optimalPath = new long[t.length][t[0].length];
        for (int i = 0; i < optimalPath.length; i++) {
            for (int j = 0; j < optimalPath[0].length; j++) {
                optimalPath[i][j] = Integer.MAX_VALUE;
            }
        }
        optimalPath[0][0] = t[0][0];
        for (int i = 0; i < optimalPath.length; i++) {
            for (int j = 0; j < optimalPath[0].length; j++) {
                if (i == 0 && j == 0) continue;
                long candidate1 = i-1 >= 0 ? optimalPath[i-1][j] : Integer.MAX_VALUE;
                long candidate2 = j-1 >= 0 ? optimalPath[i][j-1] : Integer.MAX_VALUE;
                long candidate3 = i+1 < optimalPath.length ? optimalPath[i+1][j] : Integer.MAX_VALUE;
                long candidate4 = j+1 < optimalPath[0].length ? optimalPath[i][j+1] : Integer.MAX_VALUE;
                long candidate = Math.min(Math.min(Math.min(candidate1, candidate2), candidate3), candidate4);
                optimalPath[i][j] = candidate + t[i][j];
            }
        }
        return optimalPath;
    }

    private static List<String> traverseOptimalPath(long[][] optimalPath, Point start) {
        List<String> result = new ArrayList<>();
        int i = start.x, j = start.y;
        while (i != 0 && j != 0) {
            long candidate1 = i-1 >= 0 ? optimalPath[i-1][j] : Integer.MAX_VALUE;
            long candidate2 = j-1 >= 0 ? optimalPath[i][j-1] : Integer.MAX_VALUE;
            long candidate3 = i+1 < optimalPath.length ? optimalPath[i+1][j] : Integer.MAX_VALUE;
            long candidate4 = j+1 < optimalPath[0].length ? optimalPath[i][j+1] : Integer.MAX_VALUE;
            long candidate = Math.min(Math.min(Math.min(candidate1, candidate2), candidate3), candidate4);
            if (candidate1 == candidate) {
                result.add("down");
                i--;
            }
            else if (candidate2 == candidate) {
                result.add("right");
                j--;
            }
            else if (candidate3 == candidate) {
                result.add("up");
                i++;
            }
            else {
                result.add("left");
                j++;
            }
        }
        return result;
    }
}
