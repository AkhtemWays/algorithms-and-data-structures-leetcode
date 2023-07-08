package dynamicProgramming.FindaGoodSubsetoftheMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    private static void test4() {
        int[][] grid = {{1,1,0,0,1},{0,1,1,1,0},{1,0,0,0,0},{0,0,1,0,0},{0,0,0,1,0},{1,0,1,1,0},{1,0,0,0,1},{1,1,0,1,0},{1,0,1,0,1},{1,1,1,1,1}};
        System.out.println(goodSubsetofBinaryMatrix(grid));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        HashMap<Integer, Integer> nums = new HashMap<>();
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            int num = 0;
            for (int j = m-1; j >= 0; j--) {
                num = num | (grid[i][j] << m-1-j);
            }
            if (!nums.containsKey(num)) {
                nums.put(num, i);
            }
        }
        if (nums.containsKey(0)) return List.of(nums.get(0));

        List<Integer> bitmasks = new ArrayList<>(nums.keySet());

        HashSet<Integer> cache = new HashSet<>();
        List<Integer> res = dfs(0, 0, bitmasks, cache, new ArrayList<>(), nums);
        return res == null ? List.of() : res;
    }

    private static List<Integer> dfs(int i, int state, List<Integer> bitmasks, HashSet<Integer> cache, List<Integer> taken, HashMap<Integer, Integer> nums) {
        if (i == bitmasks.size()) {
            if (isGoodSubset(state, taken.size()/2)) {
                return taken;
            }
            return null;
        }

        if (cache.contains(state)) return null;

        List<Integer> res = dfs(i+1, state, bitmasks, cache, taken, nums);
        if (res != null) return res;
        taken.add(i);
        res = dfs(i+1, addToState(state, bitmasks.get(i)), bitmasks, cache, taken, nums);
        if (res != null) return res;
        taken.remove(taken.size()-1);
        cache.add(state);
        return null;
    }

    private static boolean isGoodSubset(int state, int halfLength) {
        if (halfLength == 0) return false;
        int i = 0;
        while ((state >> i) > 0) {
            int sum = (state >> i) & 31;
            if (sum > halfLength) return false;
            i += 5;
        }
        return true;
    }

    private static int addToState(int state, int num) {
        int i = 0;
        int k = 0;
        int newState = 0;
        while ((num >> i) > 0) {
            int sum = (state >> k) & 31;
            sum += (num >> i) & 1;
            newState |= (sum << k);
            i++;
            k += 5;
        }
        return newState;
    }
}
