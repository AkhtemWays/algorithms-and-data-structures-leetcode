package bitManipulation.FindKthLargestXORCoordinateValue;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static void test1() {
        int[][] matrix = {{5,2},{1,6}};
        System.out.println(kthLargestValue(matrix, 1));
    }
    private static void test2() {
        int[][] matrix = {{5,2},{1,6}};
        System.out.println(kthLargestValue(matrix, 2));
    }
    private static void test3() {
        int[][] matrix = {{5,2},{1,6}};
        System.out.println(kthLargestValue(matrix, 3));
    }
    private static void test4() {
        int[][] matrix = {{5,2},{1,6}};
        System.out.println(kthLargestValue(matrix, 4));
    }
    private static void test5() {
        int[][] matrix = {{8,10,5,8,5,7,6,0,1,4,10,6,4,3,6,8,7,9,4,2}};
        System.out.println(kthLargestValue(matrix, 2));
    }
    private static void test6() {
        int[][] matrix = {{10,9,5},{2,0,4},{1,0,9},{3,4,8}};
        System.out.println(kthLargestValue(matrix, 10));
    }
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5(); // 14
        test6(); // 1
    }

    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix[0].length, n = matrix.length;
        int[][] xor = new int[n][m];
        Set<Integer> nums = new HashSet<>();

        xor[0][0] = matrix[0][0];
        nums.add(xor[0][0]);

        for (int j = 1; j < m; j++) {
            xor[0][j] = xor[0][j-1] ^ matrix[0][j];
            nums.add(xor[0][j]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    xor[i][j] = xor[i-1][j] ^ matrix[i][j];
                } else {
                    xor[i][j] = xor[i][j-1] ^ matrix[i-1][j] ^ matrix[i][j];
                }
                nums.add(xor[i][j]);
            }
        }

        List<Integer> l = new ArrayList<>(nums);
        l.sort((a, b) -> b - a);

        return l.get(k-1);
    }
}
