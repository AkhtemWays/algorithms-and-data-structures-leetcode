package arrays.TeemoAttacking;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (i+1 == timeSeries.length) {
                res += duration;
            } else {
                res += Math.min(timeSeries[i+1] - timeSeries[i], duration);
            }
        }
        return res;
    }
}
