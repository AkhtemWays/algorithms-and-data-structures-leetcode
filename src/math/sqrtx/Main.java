package math.sqrtx;

public class Main {
    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }

    public static int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int lowerBound = 0;
        int upperBound = 46340;
        int prev = -1;
        int y = -1;
        while (lowerBound <= upperBound) {
            y = (upperBound + lowerBound) / 2;
            if (prev == y) return y;
            int square = y*y;
            if (square == x) return y;
            else if (square > x) {
                upperBound = y-1;
            } else {
                lowerBound = y+1;
            }
            prev = y;
        }
        return y;
    }
}
