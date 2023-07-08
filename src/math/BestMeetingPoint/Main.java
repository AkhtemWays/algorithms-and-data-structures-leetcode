package math.BestMeetingPoint;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

    }

    public int minTotalDistance(int[][] grid) {
        int[] meetingPoint = {0,0};
        int amtFriends = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    meetingPoint[0] += i;
                    meetingPoint[1] += j;
                    amtFriends++;
                }
            }
        }

        int x = meetingPoint[0];
        int y = meetingPoint[1];
        int newX = x / amtFriends;
        int newY = y / amtFriends;
        if (x - (newX * amtFriends) >= amtFriends / 2) newX++;
        if (y - (newY * amtFriends) >= amtFriends / 2) newY++;
        meetingPoint[0] = newX;
        meetingPoint[1] = newY;
        int distance = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    distance += getDistance(meetingPoint, new int[]{i, j});
                }
            }
        }
        return distance;
    }

    private int getDistance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
}
