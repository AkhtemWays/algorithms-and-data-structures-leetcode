package stacks_and_queues.BrightestPositiononStreet;

import java.util.PriorityQueue;

public class PQApproach {
    public static void main(String[] args) {
        int[][] intervals = {{-3,2},{1,2},{3,3}};
        int[][] intervals2 = {{1,0},{0,1}};
        int[][] intervals3 = {{1,2}};
        int[][] intervals4 = {{-10,2},{0,3},{5,1}};
        int[][] intervals5 = {{1,1},{2,4},{-1,0},{-3,5},{1,2}};
        System.out.println(brightestPosition(intervals));
        System.out.println(brightestPosition(intervals2));
        System.out.println(brightestPosition(intervals3));
        System.out.println(brightestPosition(intervals4));
        System.out.println(brightestPosition(intervals5));
    }

    public static int brightestPosition(int[][] lights) {

        PriorityQueue<int[]> startQueue = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for (int[] light : lights) {
            startQueue.add(new int[]{light[0] - light[1], light[0] + light[1]});
        }
        int max = 0;
        int answer = 0;
        PriorityQueue<int[]> currentTracker = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        while (!startQueue.isEmpty()) {
            int[] interval = startQueue.poll();

            while (!currentTracker.isEmpty() && currentTracker.peek()[1] < interval[0]) {
                currentTracker.poll();
            }
            currentTracker.add(interval);
            if (currentTracker.size() > max) {
                max = currentTracker.size();
                answer = interval[0];
            }
        }
        return answer;
    }
}
