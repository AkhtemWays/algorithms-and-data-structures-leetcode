package backtracking.findTheCheapestPath;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Solution3 {

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
//        cheapestPath(maze2, new Point(0, 0), new Point(0, 2)).forEach(System.out::println);
        cheapestPath(maze3, new Point(0, 0), new Point(2, 2)).forEach(System.out::println);
    }

    private static List<String> traverseOptimalPath(long[][] optimalPath, Point start, Point finish) {
        List<String> result = new ArrayList<>();
        int k = finish.x, l = finish.y;
        int i = start.x, j = start.y;
        while (true) {
            if (i == k && j == l) {
                Collections.reverse(result);
                return result;
            }
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
    }

    public static List<String> cheapestPath(int[][] t, Point start, Point finish) {
        if (start.x == finish.x && start.y == finish.y) return List.of();
        long[][] optimalPath = initOptimalPath(t);
        bfs(optimalPath, t, start);

        return traverseOptimalPath(optimalPath, finish, start);
    }

    private static long[][] initOptimalPath(int[][] t) {
        long[][] optimalPath = new long[t.length][t[0].length];
        for (int i = 0; i < optimalPath.length; i++) {
            for (int j = 0; j < optimalPath[0].length; j++) {
                optimalPath[i][j] = Integer.MAX_VALUE;
            }
        }
        return optimalPath;
    }

    private static boolean isOptimalAt(int i, int j, long[][] optimalPath, int[][] maze, long curWeight) {
        return curWeight + maze[i][j] < optimalPath[i][j];
    }

    private static void updateAt(int i, int j, long[][] optimalPath, int[][] maze, long curWeight) {
        optimalPath[i][j] = curWeight + maze[i][j];
    }

    private static void bfs(long[][] optimalPath, int[][] maze, Point start) {
        Queue<Point> q = new LinkedList<>();
        optimalPath[start.x][start.y] = maze[start.x][start.y];
        q.add(start);

        while (!q.isEmpty()) {
            Point point = q.poll();
            int i = point.x;
            int j = point.y;

            if (i-1 >= 0 && isOptimalAt(i-1, j, optimalPath, maze, optimalPath[i][j])) {
                q.add(new Point(i-1, j));
                updateAt(i-1, j, optimalPath, maze, optimalPath[i][j]);
            }
            if (j-1 >= 0 && isOptimalAt(i, j-1, optimalPath, maze, optimalPath[i][j])) {
                q.add(new Point(i, j-1));
                updateAt(i, j-1, optimalPath, maze, optimalPath[i][j]);
            }
            if (i+1 < maze.length && isOptimalAt(i+1, j, optimalPath, maze, optimalPath[i][j])) {
                q.add(new Point(i+1, j));
                updateAt(i+1, j, optimalPath, maze, optimalPath[i][j]);
            }
            if (j+1 < maze[0].length && isOptimalAt(i, j+1, optimalPath, maze, optimalPath[i][j])) {
                q.add(new Point(i, j+1));
                updateAt(i, j+1, optimalPath, maze, optimalPath[i][j]);
            }
        }
    }
}