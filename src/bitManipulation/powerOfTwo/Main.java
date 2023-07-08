package bitManipulation.powerOfTwo;

import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1073741824));
    }

    public static boolean isPowerOfTwo(int n) {
        for (int x = 0; x < 31; x++) {
            int cur = (int) Math.pow(2, x);
            if (cur > n) return false;
            if ((cur ^ n) == 0) return true;
        }
        return false;
    }
}
