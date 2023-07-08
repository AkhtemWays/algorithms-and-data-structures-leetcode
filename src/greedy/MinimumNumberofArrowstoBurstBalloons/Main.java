package greedy.MinimumNumberofArrowstoBurstBalloons;

import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        int[][] points2 = {{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};
        System.out.println(findMinArrowShots(points));
        System.out.println(findMinArrowShots(points2));
    }

    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 1;
        int[] interval1 = points[0];
        for (int i = 1; i < points.length; i++) {
            int[] interval2 = points[i];
            if (checkIntervals(interval1, interval2) || checkIntervals(interval2, interval1)) {
                interval1 = merge(interval1, interval2);
            } else {
                interval1 = interval2;
                ans++;
            }
        }
        return ans;
    }

    private static int[] merge(int[] interval1, int[] interval2) {
        return new int[]{Math.max(interval1[0], interval2[0]), Math.min(interval1[1], interval2[1])};
    }

    private static boolean checkIntervals(int[] interval1, int[] interval2) {
        return (interval1[0] >= interval2[0] && interval1[0] <= interval2[1]) ||
                (interval1[1] >= interval2[0] && interval1[1] <= interval2[1]);
    }
}
