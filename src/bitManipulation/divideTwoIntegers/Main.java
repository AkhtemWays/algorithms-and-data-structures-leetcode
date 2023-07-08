package bitManipulation.divideTwoIntegers;

public class Main {
    public static void main(String[] args) {
        System.out.println(-16 >>> 4);
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if ((dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE)
        || (dividend == Integer.MAX_VALUE && divisor == Integer.MAX_VALUE)) return 1;
        if (divisor == Integer.MIN_VALUE) return 0;
        int res = calculate(Math.abs(dividend), Math.abs(divisor));
        if ((dividend < 0 && divisor >= 0) || (divisor < 0 && dividend >= 0)) {
            res = -res;
        }
        return res;
    }

    public static int calculate(int dividend, int divisor) {
        if (divisor == 1) return dividend;
        if (dividend == Integer.MIN_VALUE) return 1 + calculate(Integer.MAX_VALUE - divisor + 1, divisor);
        if (dividend < divisor) return 0;
        int x = 0;
        while (dividend - (divisor << x) >= 0) {
            x++;
        }
        int count = ((int) Math.pow(2, x - 1));
        return count + divide(dividend - (divisor << (x - 1)), divisor);
    }
}
