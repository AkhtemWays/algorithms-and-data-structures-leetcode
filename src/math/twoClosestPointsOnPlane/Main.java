package math.twoClosestPointsOnPlane;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
    public static List<Point> closestPair(final List<Point> points) {
        points.sort((a, b) -> Double.compare(a.x, b.x) == 0 ? Double.compare(a.y, b.y) : Double.compare(a.x, b.x));

        return find((points));
    }

    private static List<Point> find(final List<Point> points) {
        if (points.size() <= 3) {
            return bruteForce(points);
        }

        final int mid = points.size() / 2;
        final List<Point> pointsLeft = find(points.subList(0, mid));
        final List<Point> pointsRight = find(points.subList(mid, points.size()));

        final double distanceL = calcDistance(pointsLeft.get(0), pointsLeft.get(1));
        final double distanceR = calcDistance(pointsRight.get(0), pointsRight.get(1));
        final double delta = Math.min(distanceL, distanceR);
        final List<Point> stripPoints = stripPoint(points, delta, mid);

        if (stripPoints.isEmpty()) {
            return distanceL < distanceR ? pointsLeft : pointsRight;
        }

        return stripPoints;
    }

    private static List<Point> stripPoint(final List<Point> points, final double delta, final int mid) {
        final List<Point> strips = points.stream()
                .filter(p -> Math.abs(p.x - points.get(mid).x) < delta)
                .sorted(Comparator.comparing(p -> p.y))
                .collect(Collectors.toList());

        double min = delta;
        Point p1 = null;
        Point p2 = null;
        for (int i = 0; i < strips.size(); ++i) {
            for (int j = i + 1; j < strips.size() && (strips.get(j).y - strips.get(i).y) < min; ++j) {
                final double dist = calcDistance(strips.get(i), strips.get(j));
                if (dist < min) {
                    min = dist;
                    p1 = strips.get(i);
                    p2 = strips.get(j);
                }
            }
        }

        if (Objects.nonNull(p1)) {
            return Arrays.asList(p1, p2);
        }

        return Collections.emptyList();
    }

    private static double calcDistance(final Point point1, final Point point2) {
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }

    private static List<Point> bruteForce(final List<Point> points) {
        Point p1 = null;
        Point p2 = null;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                final double dist = calcDistance(points.get(i), points.get(j));

                if (dist < min) {
                    min = dist;
                    p1 = points.get(i);
                    p2 = points.get(j);
                }
            }
        }

        return Arrays.asList(p1, p2);
    }
}