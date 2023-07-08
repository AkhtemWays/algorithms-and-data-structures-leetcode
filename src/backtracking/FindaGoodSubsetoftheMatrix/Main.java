package backtracking.FindaGoodSubsetoftheMatrix;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void test1() {
        int[][] grid = {{0,1,1,0},{0,0,0,1},{1,1,1,1}};
        System.out.println(goodSubsetofBinaryMatrix(grid));
    }
    private static void test2() {
        int[][] grid = {{0}};
        System.out.println(goodSubsetofBinaryMatrix(grid));
    }
    private static void test3() {
        int[][] grid = {{1,1,1},{1,1,1}};
        System.out.println(goodSubsetofBinaryMatrix(grid));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        List<Integer> res = dfs(0, grid, new ArrayList<>());
        return res == null ? List.of() : res;
    }

    private static List<Integer> dfs(int j, int[][] grid, List<Integer> taken) {
        if (j == grid.length) {
            if (taken.size() == 0) return null;
            int length = taken.size() / 2;
            for (int col : taken) {
                int sum = 0;
                for (int i = 0; i < grid.length; i++) {
                    sum += grid[i][col];
                }
                if (sum > length) return null;
            }
            return taken;
        }

        taken.add(j);
        List<Integer> res1 = dfs(j+1, grid, taken);
        if (res1 != null) return res1;
        taken.remove(taken.size()-1);
        return dfs(j+1, grid, taken);
    }
}
