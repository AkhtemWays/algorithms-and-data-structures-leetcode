package math.CountLatticePointsInsideaCircle;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] circles = {{2,2,2},{3,4,1}};
        int[][] circles2 = {{2,2,1}};
        System.out.println(main.countLatticePoints(circles));
        System.out.println(main.countLatticePoints(circles2));
    }

    public int countLatticePoints(int[][] circles) {
        Set<Point> points = new HashSet<>();
        for (int[] circle : circles) {
            int x = circle[0], y = circle[1], r = circle[2];
            Point center = new Point(x, y);
            for (int yCur = y + r; yCur >= y - r; yCur--) {
                for (int xCur = x + r; xCur >= x - r; xCur--) {
                    Point point = new Point(xCur, yCur);
                    if (belongsToCircle(center, point, r)) {
                        points.add(point);
                    }
                }
            }
        }
        return points.size();
    }

    private boolean belongsToCircle(Point center, Point point, double r) {
        double xDiffSquared = Math.pow(Math.abs(center.x - point.x), 2);
        double yDiffSquared = Math.pow(Math.abs(center.y - point.y), 2);
        return Math.sqrt(xDiffSquared + yDiffSquared) <= r;
    }
}
