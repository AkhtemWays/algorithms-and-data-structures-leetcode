package math.totalAreaCoveredByRectangles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        int[][] rectangles = {{8, 0, 9, 7}, {2, 1, 4, 8}, {2, 2, 6, 5}, {2, 0, 6, 1}};
        int[][] rectangles2 = {{0, 4, 11, 6}};
        int[][] rectangles4 = {{3,3,8,5}, {6,3,8,9}, {11,6,14,12}};
        int[][] rectangles5 = {{0,0,1000000000,1000000000}};
        System.out.println(rectangleArea(rectangles));
        System.out.println(rectangleArea(rectangles2));
        System.out.println(rectangleArea(rectangles4));
        System.out.println(rectangleArea(rectangles5));
    }

    public static int rectangleArea(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1;
        int[][] events = new int[rectangles.length * 2][];
        int t = 0;
        for (int[] rec: rectangles) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        ArrayList<int[]> active = new ArrayList<>();
        int cur_y = events[0][0];
        long ans = 0;
        for (int[] event: events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

            // Calculate query
            long width = 0;
            int cur = -1;
            for (int[] xs: active) {
                cur = Math.max(cur, xs[0]);
                width += Math.max(xs[1] - cur, 0);
                cur = Math.max(cur, xs[1]);
            }

            ans += width * (y - cur_y);

            if (typ == OPEN) {
                active.add(new int[]{x1, x2});
                Collections.sort(active, (a, b) -> Integer.compare(a[0], b[0]));
            } else {
                for (int i = 0; i < active.size(); ++i)
                    if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                        active.remove(i);
                        break;
                    }
            }

            cur_y = y;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }
}
