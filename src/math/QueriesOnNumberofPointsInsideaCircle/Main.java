package math.QueriesOnNumberofPointsInsideaCircle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[][] points = {{1,3},{3,3},{5,3},{2,2}};
        int[][] queries = {{2,3,1},{4,3,1},{1,1,2}};
        System.out.println(Arrays.toString(countPoints(points, queries)));
    }

    public static int[] countPoints(int[][] points, int[][] queries) {
        HashMap<String, Integer> pointCounts = new HashMap<>();
        int[] res = new int[queries.length];
        for (int[] point : points) {
            String str = String.valueOf(point[0]) + point[1];
            pointCounts.put(str, pointCounts.getOrDefault(str, 0) + 1);
        }
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int x1 = query[0], y1 = query[1], r = query[2];
            for (Map.Entry<String, Integer> pointCount : pointCounts.entrySet()) {
                String point = pointCount.getKey();
                Integer count = pointCount.getValue();
                int x2 = Character.getNumericValue(point.charAt(0)), y2 = Character.getNumericValue(point.charAt(1));
                double distance = getDistance(x1, x2, y1, y2);
                if (distance <= r) {
                    res[i] += count;
                }
            }
        }
        return res;
    }

    private static double getDistance(int x1, int x2, int y1, int y2) {
        double xDistanceSquared = Math.pow(Math.abs(x1 - x2), 2);
        double yDistanceSquared = Math.pow(Math.abs(y1 - y2), 2);
        return Math.sqrt(xDistanceSquared + yDistanceSquared);
    }
}
