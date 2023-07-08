package graphs.MinCosttoConnectAllPoints;

import java.awt.*;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println(minCostConnectPoints(points));
    }

    public static int minCostConnectPoints(int[][] points) {
        HashSet<Point> visited = new HashSet<>();
        int[] first = points[0];
        Point startPoint = new Point(first[0], first[1]);
        return (int) solve(startPoint, visited, points);
    }

    private static double solve(Point point, HashSet<Point> visited, int[][] points) {
        visited.add(point);
        if (visited.size() == points.length) return 0;
        double minCost = Double.MAX_VALUE;
        Point closestPoint = null;
        for (int i = 0; i < points.length; i++) {
            Point curPoint = new Point(points[i][0], points[i][1]);
            if (!visited.contains(curPoint)) {
                double distance = getDistance(curPoint, point);
                if (distance < minCost) {
                    closestPoint = curPoint;
                    minCost = distance;
                }
            }
        }
        return minCost + solve(closestPoint, visited, points);
    }

    private static double getDistance(Point point1, Point point2) {
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }
}
