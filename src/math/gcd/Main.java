package math.gcd;

public class Main {
    public static void main(String[] args) {
        System.out.println(gcd(7, 21));
    }

    public static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        while (Math.max(a, b) % Math.min(a, b) != 0) {
            if (b > a) b = b % a;
            else a = a % b;
        }
        return Math.min(a, b);
    }
}
