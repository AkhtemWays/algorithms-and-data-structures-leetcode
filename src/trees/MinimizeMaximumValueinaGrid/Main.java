package trees.MinimizeMaximumValueinaGrid;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
    private static void test1() {
        int[][] grid = {{2, 4, 5}, {7, 3, 9}};
        System.out.println(Arrays.deepToString(minScore(grid)));
    }
    public static void main(String[] args) {
        test1();
    }

    private static class Value {
        int i;
        int j;
        int val;
        Value(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
    public static int[][] minScore(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Comparator<Value> comparator = Comparator.comparingInt(a -> a.val);
        PriorityQueue<Value> q = new PriorityQueue<>(comparator);
        TreeSet<Integer>[] rows = new TreeSet[n];
        TreeSet<Integer>[] cols = new TreeSet[m];
        for (int i = 0; i < n; i++) rows[i] = new TreeSet<>();
        for (int j = 0; j < m; j++) cols[j] = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Value value = new Value(i, j, grid[i][j]);
                rows[i].add(grid[i][j]);
                cols[j].add(grid[i][j]);
                q.offer(value);
            }
        }

        while (!q.isEmpty()) {
            Value value = q.poll();
            Integer lowerRowValue = rows[value.i].lower(value.val);
            Integer lowerColValue = cols[value.j].lower(value.val);

            int min = getMin(lowerColValue, lowerRowValue);

            value.val = min+1;
            grid[value.i][value.j] = value.val;
        }
        return grid;
    }

    private static int getMin(Integer value1, Integer value2) {
        if (value1 == null && value2 == null) return 0;
        if (value1 == null || value2 == null) return value1 == null ? value2 : value1;
        return Math.max(getOrMax(value1), getOrMax(value2));
    }

    private static int getOrMax(Integer value) {
        return value == null ? Integer.MAX_VALUE : value;
    }
}
