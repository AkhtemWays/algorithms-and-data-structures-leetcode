package lineSweep.PerfectRectangle;

import java.util.*;
import java.util.stream.Collectors;

public class SecondSolution {
    public static void main(String[] args) {
        int[][] recs = {{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}};
        int[][] recs2 = {{1,1,2,3},{1,3,2,4},{3,1,4,2},{3,2,4,4}};
        int[][] recs3 = {{1,1,3,3},{3,1,4,2},{1,3,2,4},{2,2,4,4}};
        int[][] recs4 = {{0,0,3,3},{1,1,2,2}};
        int[][] recs5 = {{0,0,4,1},{7,0,8,2},{6,2,8,3},{5,1,6,3},{4,0,5,1},{6,0,7,2},{4,2,5,3},{2,1,4,3},{0,1,2,2},{0,2,2,3},{4,1,5,2},{5,0,6,1}};
        int[][] recs6 = {{1,2,4,4},{1,0,4,1},{0,2,1,3},{0,1,3,2},{3,1,4,2},{0,3,1,4},{0,0,1,1}};
//        System.out.println(isRectangleCover(recs));
//        System.out.println(isRectangleCover(recs2));
//        System.out.println(isRectangleCover(recs3));
        System.out.println(isRectangleCover(recs4));
        System.out.println(isRectangleCover(recs5));
        System.out.println(isRectangleCover(recs5));
        System.out.println(isRectangleCover(recs6)); // true

    }
    public static class Event implements Comparable<Event> {
        int time;
        int[] rect;

        public Event(int time, int[] rect) {
            this.time = time;
            this.rect = rect;
        }

        public int compareTo(Event that) {
            if (this.time != that.time) return this.time - that.time;
            else return this.rect[0] - that.rect[0];
        }
    }

    public static boolean isRectangleCover(int[][] rectangles) {
        PriorityQueue<Event> pq = new PriorityQueue<Event> ();
        // border of y-intervals
        int[] border= {Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int[] rect : rectangles) {
            Event e1 = new Event(rect[0], rect);
            Event e2 = new Event(rect[2], rect);
            pq.add(e1);
            pq.add(e2);
            if (rect[1] < border[0]) border[0] = rect[1];
            if (rect[3] > border[1]) border[1] = rect[3];
        }
        TreeSet<int[]> set = new TreeSet<int[]> (new Comparator<int[]> () {
            @Override
            // if two y-intervals intersects, return 0
            public int compare (int[] rect1, int[] rect2) {
                if (rect1[3] <= rect2[1]) return -1;
                else if (rect2[3] <= rect1[1]) return 1;
                else return 0;
            }
        });
        int yRange = 0;
        while (!pq.isEmpty()) {
            int time = pq.peek().time;
            while (!pq.isEmpty() && pq.peek().time == time) {
                Event e = pq.poll();
                int[] rect = e.rect;
                if (time == rect[2]) {
                    set.remove(rect);
                    yRange -= rect[3] - rect[1];
                } else {
                    if (!set.add(rect)) return false;
                    yRange += rect[3] - rect[1];
                }
            }
            // check intervals' range
            if (!pq.isEmpty() && yRange != border[1] - border[0]) {
                return false;
                //if (set.isEmpty()) return false;
                //if (yRange != border[1] - border[0]) return false;
            }
        }
        return true;
    }
}
