package stacks_and_queues.MinimumTimetoCompleteTrips;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] time = {1,2,3};
        int[] time2 = {2};
        int[] time3 = {100};
        int[] time4 = {5,10,10};
//        System.out.println(minimumTime(time, 5));
//        System.out.println(minimumTime(time2, 1));
//        System.out.println(minimumTime(time3, 10000000));
        System.out.println(minimumTime(time4, 9));
    }

    public static boolean timeEnough(int[] time, long givenTime, int totalTrips) {
        long actualTrips = 0;
        for (int t : time)
            actualTrips += givenTime / t;
        return actualTrips >= totalTrips;
    }

    public static long minimumTime(int[] time, int totalTrips) {
        // Initialize the left and right boundaries.
        int max_time = 0;
        for (int t : time) {
            max_time = Math.max(max_time, t);
        }
        long left = 1, right = (long) max_time * totalTrips;

        // Binary search to find the minimum time to finish the task.
        while (left < right) {
            long mid = (left + right) / 2;
            if (timeEnough(time, mid, totalTrips)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
