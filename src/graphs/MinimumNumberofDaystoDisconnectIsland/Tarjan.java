package graphs.MinimumNumberofDaystoDisconnectIsland;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Tarjan {
    public static void main(String[] args) {
        int[][] grid = {{0,1,1,0},{0,1,1,0},{0,0,0,0}};
        int[][] grid2 = {{1,1}};
        int[][] grid3 = {{1,0,1,0}};
        System.out.println(minDays(grid));
        System.out.println(minDays(grid2));
        System.out.println(minDays(grid3));
    }

    private static final int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int n;
    private static int m;
    private static int time = 0;

    public static int minDays(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        HashSet<Point> points = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    points.add(new Point(i, j));
                }
            }
        }
        int days = 0;
        if (points.isEmpty()) return days;


        HashMap<Point, Set<Point>> graph = getGraph(grid, points.stream().findAny().get());
        HashMap<Point, HashSet<Point>> starts = starts(graph);
        while (!starts.isEmpty()) {
            Point start = getStart(starts);
            boolean found = tarjan(graph, start) == 0;
            while (found) {
                days++;
                Set<Point> neighbours = starts.get(start);
                if (neighbours.isEmpty()) {
                    break;
                }
                Point removalPoint = neighbours.stream().findAny().get();
                removeFromGraph(graph, removalPoint);
                neighbours.remove(removalPoint);
                found = tarjan(graph, start) == 0;
            }
            if (!found) days++;
            starts.remove(start);
            removeFromGraph(graph, start);
        }

        return days;
    }

    private static Point getStart(HashMap<Point, HashSet<Point>> starts) {
        for (Point p : starts.keySet()) return p;
        return null;
    }

    private static HashMap<Point, HashSet<Point>> starts(HashMap<Point, Set<Point>> graph) {
        HashSet<Point> visited = new HashSet<>();
        HashMap<Point, HashSet<Point>> starts = new HashMap<>();
        for (Point point : graph.keySet()) {
            if (!visited.contains(point)) {
                starts.put(point, new HashSet<>());
                visit(point, point, graph, visited, starts);
            }
        }
        return starts;
    }

    private static void visit(Point point, Point start, HashMap<Point, Set<Point>> graph, HashSet<Point> visited, HashMap<Point, HashSet<Point>> starts) {
        visited.add(point);
        for (Point neighbour : graph.get(point)) {
            if (!visited.contains(neighbour)) {
                visited.add(neighbour);
                starts.get(start).add(neighbour);
                visit(neighbour, start, graph, visited, starts);
            }
        }
    }

    private static void removeFromGraph(HashMap<Point, Set<Point>> graph, Point point) {
        for (Point neighbour : graph.get(point)) {
            graph.get(neighbour).remove(point);
        }
        graph.remove(point);
    }

    private static int tarjan(HashMap<Point, Set<Point>> graph, Point start) {
        time = 0;
        HashMap<Point, Integer> disc = new HashMap<>(), low = new HashMap<>();
        HashSet<Point> ap = new HashSet<>();
        HashMap<Point, Point> parent = new HashMap<>();
        for (Point point : graph.keySet()) {
            disc.put(point, -1);
            low.put(point, -1);
            parent.put(point, defaultPoint());
        }

        dfs(start, disc, low, parent, graph, ap);
        return ap.size();
    }

    private static void dfs(Point u, HashMap<Point, Integer> disc, HashMap<Point, Integer> low, HashMap<Point, Point> parent,
                            HashMap<Point, Set<Point>> graph, HashSet<Point> ap) {
        disc.put(u, time);
        low.put(u, time++);
        int children = 0;

        for (Point v : graph.get(u)) {
            if (disc.get(v) == -1) {
                parent.put(v, u);
                children += 1;
                dfs(v, disc, low, parent, graph, ap);
                low.put(u, Math.min(low.get(u), low.get(v)));

                if (!parent.get(u).equals(defaultPoint()) && children>1) ap.add(u);
                if (!parent.get(u).equals(defaultPoint()) && low.get(v)>=disc.get(u)) ap.add(u);
            }
            else if (parent.get(u) != v) {
                low.put(u, Math.min(low.get(u), disc.get(v)));
            }
        }
    }

    private static HashMap<Point, Set<Point>> getGraph(int[][] grid, Point start) {
        HashMap<Point, Set<Point>> graph = new HashMap<>();
        build(start, grid, graph);
        return graph;
    }

    private static void build(Point cur, int[][] grid, HashMap<Point, Set<Point>> graph) {
        if (graph.containsKey(cur)) return;
        graph.put(cur, new HashSet<>());
        int i = cur.x;
        int j = cur.y;
        Arrays.stream(deltas).forEach(delta -> {
            int curI = i - delta[0];
            int curJ = j - delta[1];
            if (isDefinedAt(curI, curJ) && grid[curI][curJ] == 1) {
                Point neighbour = new Point(curI, curJ);
                graph.get(cur).add(neighbour);
                build(neighbour, grid, graph);
            }
        });
    }

    private static boolean isDefinedAt(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    private static Point defaultPoint() {
        return new Point(-1, -1);
    }
}
