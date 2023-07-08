package math.totalAreaCoveredByRectangles;

import java.util.*;

class Solution {
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
        if (rectangles.length == 0) return 0;
        int OPEN = 1, CLOSE = -1;
        int[][] events = new int[rectangles.length * 2][];
        Set<Integer> Xvals = new HashSet<>();
        int t = 0;

        for (int[] rec: rectangles) {
            if ((rec[0] < rec[2]) && (rec[1] < rec[3])) {
                events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
                events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
                Xvals.add(rec[0]);
                Xvals.add(rec[2]);
            }
        }

        Arrays.sort(events, 0, t, Comparator.comparingInt(a -> a[0]));

        Integer[] X = Xvals.toArray(new Integer[0]);
        Arrays.sort(X);
        Map<Integer, Integer> Xi = new HashMap<>();
        for (int i = 0; i < X.length; ++i)
            Xi.put(X[i], i);

        Node active = new Node(0, X.length - 1, X);
        long ans = 0;
        long cur_x_sum = 0;
        int cur_y = events[0][0];

        for (int[] event: events) {
            if (event == null) break;
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
            ans += cur_x_sum * (y - cur_y);
            cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
            cur_y = y;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }
}

class Node {
    int start, end;
    Integer[] X;
    Node left, right;
    int count;
    long total;

    public Node(int start, int end, Integer[] X) {
        this.start = start;
        this.end = end;
        this.X = X;
        left = null;
        right = null;
        count = 0;
        total = 0;
    }

    public int getRangeMid() {
        return start + (end - start) / 2;
    }

    public Node getLeft() {
        if (left == null) left = new Node(start, getRangeMid(), X);
        return left;
    }

    public Node getRight() {
        if (right == null) right = new Node(getRangeMid(), end, X);
        return right;
    }

    public long update(int i, int j, int val) {
        if (i >= j) return 0;
        if (start == i && end == j) {
            count += val;
        } else {
            getLeft().update(i, Math.min(getRangeMid(), j), val);
            getRight().update(Math.max(getRangeMid(), i), j, val);
        }

        if (count > 0) total = X[end] - X[start];
        else total = getLeft().total + getRight().total;

        return total;
    }
}
