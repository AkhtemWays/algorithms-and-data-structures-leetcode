package math.MinimumNumberOfLinesToCoverPoints;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        int[][] points = {{0,1},{2,3},{4,5},{4,3}};
        int[][] points2 = {{0,2},{-2,-2},{1,4}};
        int[][] points3 = {{-4,5},{3,-4},{4,1}};
        System.out.println(main.minimumLines(points));
        System.out.println(main.minimumLines(points2));
        System.out.println(main.minimumLines(points3));
    }

    public int minimumLines(int[][] pointss) {
        if (pointss.length <= 1) return 0;
        int answer = Integer.MAX_VALUE;
        List<Point> points = Arrays.stream(pointss).map(point -> new Point(point[0], point[1])).collect(Collectors.toList());
        for (int i = 0; i < points.size(); i++) {
            List<Point> copyPoints = new ArrayList<>(points);
            Collections.swap(copyPoints, i, copyPoints.size() - 1);
            Point point = copyPoints.remove(copyPoints.size() - 1);
            answer = Math.min(answer, dfs(point, copyPoints));
        }
        return answer;
    }

    private int dfs(Point curPoint, List<Point> points) {
        if (points.size() <= 1) return 1;
        HashMap<Double, List<Integer>> coefficientIndices = new HashMap<>();
        double maxDegreeCoefficient = 0;
        int maxSize = 0;
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            double coefficient = getCoefficient(point, curPoint);
            if (coefficientIndices.containsKey(coefficient)) {
                List<Integer> indices = coefficientIndices.get(coefficient);
                indices.add(i);
                if (indices.size() > maxSize) {
                    maxSize = indices.size();
                    maxDegreeCoefficient = coefficient;
                }
            } else {
                coefficientIndices.put(coefficient, new ArrayList<>(List.of(i)));
                if (maxSize == 0) {
                    maxSize = 1;
                    maxDegreeCoefficient = coefficient;
                }
            }
        }
        List<Point> nextPoints = new ArrayList<>();
        for (Point point : points) {
            if (getCoefficient(point, curPoint) != maxDegreeCoefficient) {
                nextPoints.add(point);
            }
        }
        for (int i = 0; i < nextPoints.size(); i++) {
            List<Point> copyPoints = new ArrayList<>(nextPoints);
            Collections.swap(copyPoints, i, copyPoints.size() - 1);
            Point point = copyPoints.remove(copyPoints.size() - 1);
            answer = Math.min(answer, dfs(point, copyPoints));
        }
        return answer;
    }

    private double getCoefficient(Point curPoint, Point prevPoint) {
        if (prevPoint.x < curPoint.x) return getCoefficient(prevPoint, curPoint);
        return ((double) prevPoint.x - curPoint.x) / ((double) prevPoint.y - curPoint.y);
    }
}
