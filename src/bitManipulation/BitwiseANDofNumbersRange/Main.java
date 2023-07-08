package bitManipulation.BitwiseANDofNumbersRange;

public class Main {
    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(1, 1));
    }

    public static int rangeBitwiseAnd(int left, int right) {
        int shift = 30;
        int res = 0;
        while (shift >= 0) {
            if ((left >> shift & 1) != (right >> shift & 1)) break;
            res += ((left >> shift & 1) << shift);
            shift--;
        }
        return res;
    }
}
