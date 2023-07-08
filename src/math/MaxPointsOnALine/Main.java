package math.MaxPointsOnALine;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int[][] points2 = {{1,1},{2,2},{3,3}};
        System.out.println(main.maxPoints(points));
        System.out.println(main.maxPoints(points2));
    }

    public int maxPoints(int[][] points) {
        if (points.length == 0) return 0;
        int maxPoints = 1;
        int k = points.length;
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> coefficientCounts = new HashMap<>();
            Point curPoint = new Point(points[i][0], points[i][1]);
            for (int j = i + 1; j != i + k; j++) {
                Point point = new Point(points[j % k][0], points[j % k][1]);
                double coefficient = getCoefficient(curPoint, point);
                int count = coefficientCounts.getOrDefault(coefficient, 1) + 1;
                coefficientCounts.put(coefficient, count);
                if (maxPoints < count) {
                    maxPoints = count;
                }
            }
        }
        return maxPoints;
    }

    private double getCoefficient(Point curPoint, Point prevPoint) {
        if (prevPoint.x < curPoint.x) return getCoefficient(prevPoint, curPoint);
        return ((double) prevPoint.x - curPoint.x) / ((double) prevPoint.y - curPoint.y);
    }
}
