package divideAndConquer.MinimumNumberofArrowstoBurstBalloons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points));
    }

    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
        return divide(points, 0, points.length-1).size();
    }

    private static List<int[]> divide(int[][] points, int left, int right) {
        if (right - left > 0) {
            int mid = left + (right - left) / 2;
            List<int[]> leftPart = divide(points, left, mid);
            List<int[]> rightPart = divide(points, mid+1, right);
            return conquer(leftPart, rightPart);
        }
        return new ArrayList<>(List.of(points[right]));
    }

    private static List<int[]> conquer(List<int[]> leftPart, List<int[]> rightPart) {
        for (int i = 0; i < rightPart.size(); i++) {
            int[] interval2 = rightPart.get(i);
            boolean found = false;
            for (int j = 0; j < leftPart.size(); j++) {
                int[] interval = leftPart.get(j);
                if (checkIntervals(interval, interval2) || checkIntervals(interval2, interval)) {
                    found = true;
                    leftPart.set(j, merge(interval, interval2));
                    break;
                }
            }
            if (!found) {
                leftPart.add(interval2);
            }
        }
        return leftPart;
    }

    private static int[] merge(int[] interval1, int[] interval2) {
        return new int[]{Math.min(interval1[0], interval2[0]), Math.min(interval1[1], interval2[1])};
    }

    private static boolean checkIntervals(int[] interval1, int[] interval2) {
        return (interval1[0] >= interval2[0] && interval1[0] <= interval2[1]) ||
                (interval1[1] >= interval2[0] && interval1[1] <= interval2[1]);
    }
}
