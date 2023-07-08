package arrays.pathFinderAlpinist;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main {

    public static int pathFinder(String maze) {
        int N = maze.indexOf("\n");
        maze = maze.replaceAll("\n", "");

        if (maze.length() == 1) return 0;

        LinkedList<int[]>[] link = new LinkedList[N * N];
        for (int i = 0; i < N * N; i++) {
            link[i] = new LinkedList<>();
            if (i - N >= 0) link[i].add(new int[] { i - N, Math.abs(maze.codePointAt(i - N) - maze.codePointAt(i)) });
            if (i / N == (i - 1) / N && i - 1 >= 0) link[i].add(new int[] { i - 1, Math.abs(maze.codePointAt(i - 1) - maze.codePointAt(i)) });
            if (i / N == (i + 1) / N) link[i].add(new int[] { i + 1, Math.abs(maze.codePointAt(i + 1) - maze.codePointAt(i)) });
            if (i + N < N * N) link[i].add(new int[] { i + N, Math.abs(maze.codePointAt(i + N) - maze.codePointAt(i)) });
        }

        int[] distances = IntStream.generate(() -> N * N * 9).limit((long) N * N).toArray();

        getDistance(link, distances, 0, N * N - 1);

        return distances[N * N - 1] == N * N * 9 ? -1 : distances[N * N - 1];
    }

    private static void getDistance(LinkedList<int[]>[] link, int[] distances, int start, int dest) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        queue.add(new int[] { start, start, 0 });
        distances[start] = 0;

        while (!queue.isEmpty()) {
            int[] item = queue.poll();

            if (distances[item[0]] + item[2] > distances[item[1]]) {
                continue;
            }
            for (int[] v : link[item[1]]) {

                if (distances[item[1]] + v[1] < distances[v[0]]) {
                    distances[v[0]] = distances[item[1]] + v[1];

                    queue.add(new int[] { item[1], v[0], v[1] });
                }
            }
        }
    }
}
