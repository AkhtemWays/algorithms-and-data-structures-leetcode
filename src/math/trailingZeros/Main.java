package math.trailingZeros;

public class Main {
    public static void main(String[] args) {
        factorial(30);
    }

    public static int trailingZeroes(int n) {
        return n/5+n/25+n/125+n/625+n/3125;
    }

    static long factorial(int n) {
        long res = 1;
        for (int i = 2; i < n; i++) {
            res *= i;
            System.out.println(i + ": " + res);
        }

        return res;
    }
}
