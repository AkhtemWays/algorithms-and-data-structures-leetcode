package dynamicProgramming.TallestBillboard;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    private static void test1() {
        int[] rods = {1,2,3,6};
        System.out.println(tallestBillboard(rods));
    }
    private static void test2() {
        int[] rods = {1,2};
        System.out.println(tallestBillboard(rods));
    }
    private static void test3() {
        int[] rods = {1,2,3,4,5,6};
        System.out.println(tallestBillboard(rods));
    }
    private static void test4() {
        int[] rods = {1,2,4,8,16,32,64,128,256,512,50,50,50,150,150,150,100,100,100,123};
        System.out.println(tallestBillboard(rods));
    }
    public static void main(String[] args) {
        test1(); // 6
        test2(); // 0
        test3(); // 10
        test4();
    }

    public static int tallestBillboard(int[] rods) {
        return dfs(0, new HashSet<>(), rods, Arrays.stream(rods).sum() / 2, 0, 0);
    }

    private static int dfs(int i, HashSet<String> visited, int[] rods, int threshold, int left, int right) {
        if (i == rods.length) {
            return left == right ? left : 0;
        }

        String key = i + "_" + Math.abs(left - right);
        if (visited.contains(key)) return 0;

        int addToLeft = dfs(i+1, visited, rods, threshold, left + rods[i], right);
        int addToRight = dfs(i+1, visited, rods, threshold, left, right + rods[i]);
        int skip = dfs(i+1, visited, rods, threshold, left, right);
        visited.add(key);
        return Math.max(Math.max(skip, addToLeft), addToRight);
    }
}
