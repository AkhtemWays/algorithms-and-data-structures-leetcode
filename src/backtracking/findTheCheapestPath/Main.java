package backtracking.findTheCheapestPath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
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
        cheapestPath(maze3, new Point(1, 1), new Point(1, 1)).forEach(System.out::println);
    }

    private static int[][] initOptimalPath(int[][] t) {
        int[][] optimalPath = new int[t.length][t[0].length];
        for (int i = 0; i < optimalPath.length; i++) {
            for (int j = 0; j < optimalPath[0].length; j++) {
                optimalPath[i][j] = Integer.MAX_VALUE;
            }
        }
        return optimalPath;
    }

    static List<String> cheapestPath(int[][] t, Point start, Point finish) {
        if (start.x == finish.x && start.y == finish.y) return List.of();
        int[][] optimalPath = initOptimalPath(t);
        PathInstructor initialInstructor = new PathInstructor(start.x, start.y, t[0][0], new ArrayList<>());
        PathInstructor res = dfs(t, initialInstructor, finish, optimalPath);

        return res != null ? res.path : List.of();
    }

    private static boolean isOptimalAt(int i, int j, int[][] optimalPath, int[][] maze, int curWeight) {
        return curWeight + maze[i][j] < optimalPath[i][j];
    }

    private static boolean isFinish(PathInstructor instructor, Point finish) {
        return instructor.i == finish.x && instructor.j == finish.y;
    }

    private static PathInstructor createOptimalInstructor(int i, int j, PathInstructor instructor, int[][] maze, String pathToAdd, int weight) {
        List<String> path = new ArrayList<>(instructor.path);
        path.add(pathToAdd);
        return new PathInstructor(i, j, weight + maze[i][j], path);
    }

    private static PathInstructor dfs(int[][] maze, PathInstructor instructor, Point finish, int[][] optimalPath) {
        optimalPath[instructor.i][instructor.j] = instructor.weight;
        if (isFinish(instructor, finish)) {
            return instructor;
        }

        PathInstructor nextInstructor = null;
        if (instructor.i-1 >= 0 && isOptimalAt(instructor.i-1, instructor.j, optimalPath, maze, optimalPath[instructor.i][instructor.j])) {
            PathInstructor optimalInstructor = createOptimalInstructor(instructor.i-1, instructor.j, instructor, maze, "up", optimalPath[instructor.i][instructor.j]);
            PathInstructor candidate = dfs(maze, optimalInstructor, finish, optimalPath);
            if (candidate != null && nextInstructor != null && candidate.weight < nextInstructor.weight) nextInstructor = candidate;
            else if (nextInstructor == null && candidate != null) nextInstructor = candidate;
        }
        if (instructor.i+1 < maze.length && isOptimalAt(instructor.i+1, instructor.j, optimalPath, maze, optimalPath[instructor.i][instructor.j])) {
            PathInstructor optimalInstructor = createOptimalInstructor(instructor.i+1, instructor.j, instructor, maze, "down", optimalPath[instructor.i][instructor.j]);
            PathInstructor candidate = dfs(maze, optimalInstructor, finish, optimalPath);
            if (candidate != null && nextInstructor != null && candidate.weight < nextInstructor.weight) nextInstructor = candidate;
            else if (nextInstructor == null && candidate != null) nextInstructor = candidate;
        }
        if (instructor.j-1 >= 0 && isOptimalAt(instructor.i, instructor.j-1, optimalPath, maze, optimalPath[instructor.i][instructor.j])) {
            PathInstructor optimalInstructor = createOptimalInstructor(instructor.i, instructor.j-1, instructor, maze, "left", optimalPath[instructor.i][instructor.j]);
            PathInstructor candidate = dfs(maze, optimalInstructor, finish, optimalPath);
            if (candidate != null && nextInstructor != null && candidate.weight < nextInstructor.weight) nextInstructor = candidate;
            else if (nextInstructor == null && candidate != null) nextInstructor = candidate;
        }
        if (instructor.j+1 < maze[0].length && isOptimalAt(instructor.i, instructor.j+1, optimalPath, maze, optimalPath[instructor.i][instructor.j])) {
            PathInstructor optimalInstructor = createOptimalInstructor(instructor.i, instructor.j+1, instructor, maze, "right", optimalPath[instructor.i][instructor.j]);
            PathInstructor candidate = dfs(maze, optimalInstructor, finish, optimalPath);
            if (candidate != null && nextInstructor != null && candidate.weight < nextInstructor.weight) nextInstructor = candidate;
            else if (nextInstructor == null && candidate != null) nextInstructor = candidate;
        }
        return nextInstructor;
    }

    private static class PathInstructor {
        int i;
        int j;
        int weight;
        List<String> path;

        PathInstructor(int i, int j, int weight, List<String> path) {
            this.i = i;
            this.j = j;
            this.weight = weight;
            this.path = path;
        }
    }
}
