package lineSweep.PerfectRectangle;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        int[][] recs = {{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}};
        int[][] recs2 = {{1,1,2,3},{1,3,2,4},{3,1,4,2},{3,2,4,4}};
        int[][] recs3 = {{1,1,3,3},{3,1,4,2},{1,3,2,4},{2,2,4,4}};
        int[][] recs4 = {{0,0,3,3},{1,1,2,2}};
        int[][] recs5 = {{0,0,4,1},{7,0,8,2},{6,2,8,3},{5,1,6,3},{4,0,5,1},{6,0,7,2},{4,2,5,3},{2,1,4,3},{0,1,2,2},{0,2,2,3},{4,1,5,2},{5,0,6,1}};
        int[][] recs6 = {{1,2,4,4},{1,0,4,1},{0,2,1,3},{0,1,3,2},{3,1,4,2},{0,3,1,4},{0,0,1,1}};
        System.out.println(isRectangleCover(recs));
        System.out.println(isRectangleCover(recs2));
        System.out.println(isRectangleCover(recs3));
        System.out.println(isRectangleCover(recs4));
        System.out.println(isRectangleCover(recs5));
        System.out.println(isRectangleCover(recs5));
        System.out.println(isRectangleCover(recs6)); // true
    }

    public static boolean isRectangleCover(int[][] rectangles) {
        int[] border = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        Arrays.sort(rectangles, (a, b) -> {
            int diff1 = a[0] - b[0];
            int diff2 = a[2] - b[2];
            int diff3 = a[3] - b[3];
            int diff4 = a[1] - b[1];
            return diff1 != 0 ? diff1 : diff2 != 0 ? diff2 : diff3 != 0 ? diff3 : diff4;
        });
        Set<Integer> segments = new HashSet<>();
        for (int[] rectangle : rectangles) {
            segments.add(rectangle[0]);
            segments.add(rectangle[2]);
            if (rectangle[1] < border[0]) border[0] = rectangle[1];
            if (rectangle[3] > border[1]) border[1] = rectangle[3];
        }
        int wishedYinterval = border[1] - border[0];
        List<Integer> sortedSegments = new ArrayList<>(segments).stream().sorted().collect(Collectors.toList());
        HashMap<String, String> myRectangle = new HashMap<>();
        Queue<int[]> q = new LinkedList<>();
        int i = 0;
        for (int k = 1; k < sortedSegments.size(); k++) {
            int xStart = sortedSegments.get(k-1);
            int xEnd = sortedSegments.get(k);
            String key = xStart + "_" + xEnd;
            while (!q.isEmpty() && xStart >= q.peek()[2]) {
                q.poll();
            }
            while (i < rectangles.length && xEnd > rectangles[i][0] && xEnd <= rectangles[i][2]) {
                q.add(rectangles[i]);
                i++;
            }
            List<int[]> yIntervals = new ArrayList<>();
            Iterator<int[]> it = q.iterator();
            while (it.hasNext()) {
                int[] rec = it.next();
                int[] yInterval = {rec[1], rec[3]};
                yIntervals.add(yInterval);
            }
            int[] merged = mergeAndCheckConflict(yIntervals);
            if (merged[0] == Integer.MIN_VALUE) return false;
            String value = merged[0] + "_" + merged[1];
            myRectangle.put(key, value);
        }
        if (myRectangle.size() == 0) return false;
        String interval = myRectangle.entrySet().stream().findAny().get().getValue();
        return myRectangle.values().stream().allMatch(val -> val.equals(interval));
    }

    private static boolean test(int wishedInterval, List<int[]> yIntervals) {
        int sum = 0;
        for (int[] interval : yIntervals) {
            sum += (interval[1] - interval[0]);
        }
        return sum == wishedInterval;
    }

    private static int[] mergeAndCheckConflict(List<int[]> yIntervals) {
        if (yIntervals.size() == 0) return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        yIntervals.sort((a, b) -> a[0] - b[0]);
        int[] cur = yIntervals.get(0);
        for (int i = 1; i < yIntervals.size(); i++) {
            int[] interval = yIntervals.get(i);
            if (conflict(cur, interval)) return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
            cur = merge(cur, interval);
        }
        return cur;
    }

    private static int[] merge(int[] interval1, int[] interval2) {
        return new int[]{Math.min(interval1[0], interval2[0]), Math.max(interval1[1], interval2[1])};
    }

    private static boolean conflict(int[] interval1, int[] interval2) {
        return interval1[1] <= interval2[0] || interval2[1] <= interval1[0];
    }
}
