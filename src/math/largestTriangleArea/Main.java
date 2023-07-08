package math.largestTriangleArea;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Point {
    public double x, y;

    public Point() {
        x = y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(int[] coords) {
        this.x = coords[0];
        this.y = coords[1];
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point other = (Point) obj;
            return x == other.x && y == other.y;
        } else {
            return false;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] points = {{0,0},{0,2},{2,0}};
        System.out.println(largestTriangleArea(points));
    }

    public static double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    maxArea = Math.max(maxArea, getArea(new Point(points[i]), new Point(points[j]), new Point(points[k])));
                }
            }
        }
        return maxArea;
    }

    private static double getArea(Point p1, Point p2, Point p3) {
        double[][] matrix = new double[3][3];
        matrix[0][0] = p1.x;
        matrix[0][1] = p1.y;
        matrix[0][2] = 1;
        matrix[1][0] = p2.x;
        matrix[1][1] = p2.y;
        matrix[1][2] = 1;
        matrix[2][0] = p3.x;
        matrix[2][1] = p3.y;
        matrix[2][2] = 1;
        return 0.5 * Math.abs(determinant(matrix));
    }

    private static double determinant(double[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];

        boolean plus = true;
        double result = 0;

        for (int i = 0; i < matrix[0].length; i++, plus = !plus) {
            double[][] subMatrix = extractSubMatrix(matrix, i);
            if (plus) result += matrix[0][i] * determinant(subMatrix);
            else result -= matrix[0][i] * determinant(subMatrix);
        }
        return result;
    }

    private static double[][] extractSubMatrix(double[][] matrix, int idx) {
        double[][] subMatrix = new double[matrix.length - 1][matrix.length - 1];
        for (int i = 1; i < matrix.length; i++) {
            int colIdx = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (j == idx) continue;
                subMatrix[i-1][colIdx++] = matrix[i][j];
            }
        }
        return subMatrix;
    }
}
