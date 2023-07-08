import java.util.*;

class SnapshotArray {
    private static void test1() {
        System.out.println(bitwiseComplement(5));
    }

    public static int bitwiseComplement(int n) {
        int sum = 0;
        for (int i = 0; i < Integer.toBinaryString(n).length(); i++) {
            if (((n >> i) & 1) == 0) {
                sum += 1 << i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        test1(); // 4
    }
}
