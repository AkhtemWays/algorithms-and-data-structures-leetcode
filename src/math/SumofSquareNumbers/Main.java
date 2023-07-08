package math.SumofSquareNumbers;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(judgeSquareSum(5)); // true
        System.out.println(judgeSquareSum(3)); // false
        System.out.println(judgeSquareSum(12389)); // true
        System.out.println(judgeSquareSum(2147483600)); // true
    }

    public static boolean judgeSquareSum(int c) {
        int rightBound = ((int) Math.sqrt(c)) + 1;
        for (int a = 0; a <= rightBound; a++) {
            int left = 0 , right = rightBound;
            while (left < right) {
                int b = left + (right - left) / 2;
                int result = a * a + b * b;
                if (result == c) return true;
                else if (result > c) {
                    right = b - 1;
                } else {
                    left = b + 1;
                }
            }
        }
        return false;
    }
}
