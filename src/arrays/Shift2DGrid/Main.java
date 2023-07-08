package arrays.Shift2DGrid;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int totalShifts = n * m;
        k = k % totalShifts;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) res.add(new ArrayList<>());

        for (int pos = totalShifts - k, row = 0; pos < 2 * totalShifts - k; pos++, row++) {
            int normalPos = pos % totalShifts;
            res.get(row / m).add(grid[normalPos / m][normalPos % m]);
        }
        return res;
    }
}
