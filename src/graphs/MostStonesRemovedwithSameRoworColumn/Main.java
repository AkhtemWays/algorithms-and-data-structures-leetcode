package graphs.MostStonesRemovedwithSameRoworColumn;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        // [1, 1, 0]
        // [1, 0, 1]
        // [0, 1, 1]
        System.out.println(removeStones(stones));
    }

    public static int removeStones(int[][] stones) {
        HashMap<Integer, Set<Integer>> xs = new HashMap<>();
        HashMap<Integer, Set<Integer>> ys = new HashMap<>();
        for (int[] stoneCoordinate : stones) {
            xs.computeIfAbsent(stoneCoordinate[0], (key) -> new HashSet<>()).add(stoneCoordinate[1]);
            ys.computeIfAbsent(stoneCoordinate[1], (key) -> new HashSet<>()).add(stoneCoordinate[0]);
        }

        HashMap<Point, List<Point>> adj = new HashMap<>();

        for (int[] stoneCoordinate : stones) {
            int x = stoneCoordinate[0];
            int y = stoneCoordinate[1];
            List<Point> neighbours = new ArrayList<>();

            for (int curY : xs.get(x)) {
                if (curY == y) continue;
                neighbours.add(new Point(x, curY));
            }

            for (int curX : ys.get(y)) {
                if (curX == x) continue;
                neighbours.add(new Point(curX, y));
            }

            adj.put(new Point(stoneCoordinate[0], stoneCoordinate[1]), neighbours);
        }

        int numComponents = 0;
        Set<Point> visited = new HashSet<>();
        for (int[] stone : stones) {
            Point stonePoint = new Point(stone[0], stone[1]);
            if (!visited.contains(stonePoint)) {
                visited.add(stonePoint);
                numComponents++;
                dfs(stonePoint, visited, adj);
            }
        }

        return stones.length - numComponents;
    }

    private static void dfs(Point stone, Set<Point> visited, HashMap<Point, List<Point>> adj) {
        for (Point neighbourStone : adj.get(stone)) {
            if (!visited.contains(neighbourStone)) {
                visited.add(neighbourStone);
                dfs(neighbourStone, visited, adj);
            }
        }
    }
}
