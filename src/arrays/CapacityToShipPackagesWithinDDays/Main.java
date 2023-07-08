package arrays.CapacityToShipPackagesWithinDDays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int[] weights2 = {3,2,2,4,1,4};
        int[] weights3 = {1,2,3,1,1};
        int[] weights4 = {147,73,265,305,191,152,192,293,309,292,182,157,381,287,73,162,313,366,346,47};
        System.out.println(shipWithinDays(weights, 5)); // 15
        System.out.println(shipWithinDays(weights2, 3)); // 6
        System.out.println(shipWithinDays(weights3, 4)); // 3
        System.out.println(shipWithinDays(weights4, 10));
    }

    public static int shipWithinDays(int[] weights, int days) {
        int l = Arrays.stream(weights).max().getAsInt();
        int r = Arrays.stream(weights).sum();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (canComplete(weights, days, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static boolean canComplete(int[] weights, int days, int capacity) {
        for (int i = 0, curWeight = 0; i < weights.length; i++) {
            curWeight += weights[i];
            if (curWeight > capacity) {
                curWeight = weights[i];
                days--;
                if (days == 0) return false;
            }
        }
        return days > 0;
    }
}
