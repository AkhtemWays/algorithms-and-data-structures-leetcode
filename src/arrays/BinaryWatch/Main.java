package arrays.BinaryWatch;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(readBinaryWatch(1));
    }

    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int hour = 0; hour < 12; hour++) {
            int hourBits = constructHour(hour);
            for (int minute = 0; minute < 60; minute++) {
                int minuteBits = constructMinutes(minute);
                if (hourBits + minuteBits == turnedOn) {
                    res.add(hour + ":" + formatNumber(minute));
                }
            }
        }
        return res;
    }

    private static String formatNumber(int n) {
        return n > 9 ? "" + n : "0" + n;
    }

    private static int constructHour(int hour) {
        int[] hours = {8, 4, 2, 1};
        int count = 0;
        for (int i = 0; i < hours.length; i++) {
            if (hour >= hours[i]) {
                count++;
                hour -= hours[i];
            }
        }
        return count;
    }

    private static int constructMinutes(int minute) {
        int[] minutes = {32, 16, 8, 4, 2, 1};
        int count = 0;
        for (int i = 0; i < minutes.length; i++) {
            if (minute >= minutes[i]) {
                count++;
                minute -= minutes[i];
            }
        }
        return count;
    }
}
