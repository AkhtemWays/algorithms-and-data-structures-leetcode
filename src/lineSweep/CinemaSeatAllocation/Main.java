package lineSweep.CinemaSeatAllocation;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        int[][] reservedSeats = {{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        System.out.println(maxNumberOfFamilies(3, reservedSeats));
    }

    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, Comparator.comparingInt(a -> a[0]));
        int answer = 0;
        int rowsProcessed = 0;
        for (int i = 0; i < reservedSeats.length; i++) {
            int curRow = reservedSeats[i][0];
            rowsProcessed++;
            int[] seats = new int[10];
            while (i < reservedSeats.length && reservedSeats[i][0] == curRow) {
                int[] reservedSeat = reservedSeats[i];
                int column = reservedSeat[1];
                seats[column-1]++;
                i++;
            }
            i--;
            answer += findGroups(seats);
        }
        int rowsLeft = n - rowsProcessed;
        return answer + 2 * rowsLeft;
    }

    private static int findGroups(int[] seats) {
        int peopleAmt = 0;
        int answer = 0;
        for (int seat : seats) {
            if (seat > 0) {
                peopleAmt = 0;
            } else {
                peopleAmt++;
                if (peopleAmt == 4) {
                    peopleAmt = 0;
                    answer++;
                }
            }
        }
        return answer;
    }
}
