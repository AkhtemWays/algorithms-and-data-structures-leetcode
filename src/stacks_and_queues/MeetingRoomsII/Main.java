package stacks_and_queues.MeetingRoomsII;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
//        int[][] input = {{7, 10},{2,4}};
//        System.out.println(minMeetingRooms(input));
//        int[][] input2 = {{0, 30},{5,10}, {15, 20}};
//        System.out.println(minMeetingRooms(input2));
        int[][] input3 = {{9,14},{5,6},{3,5},{12,19}};
        System.out.println(minMeetingRooms(input3));
    }

    public static int minMeetingRooms(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        for(int startPointer=0, endPointer=0; startPointer<starts.length; startPointer++) {
            if(starts[startPointer]<ends[endPointer])
                rooms++;
            else
                endPointer++;
        }
        return rooms;
    }
}
