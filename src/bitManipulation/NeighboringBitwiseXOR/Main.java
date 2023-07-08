package bitManipulation.NeighboringBitwiseXOR;

public class Main {
    private static void test1() {
        int[] derived = {1,1,0};
        System.out.println(doesValidArrayExist(derived));
    }
    private static void test2() {
        int[] derived = {1,1};
        System.out.println(doesValidArrayExist(derived));
    }
    private static void test3() {
        int[] derived = {1,0};
        System.out.println(doesValidArrayExist(derived));
    }
    public static void main(String[] args) {
        test1(); // true
        test2(); // true
        test3(); // false
    }

    public static boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int prev0 = 0;
        int prev1 = 1;
        int cur0 = 0, cur1 = 0;
        for (int i = 1; i < n; i++) {
            if (derived[i - 1] == 0) {
                cur0 = prev0;
                cur1 = prev1;
            } else {
                cur0 = Math.abs(prev0 - 1);
                cur1 = Math.abs(prev1 - 1);
            }
            prev0 = cur0;
            prev1 = cur1;
        }

        int lastDerived = derived[n - 1];
        return cur0 == lastDerived || (1 ^ cur1) == lastDerived;
    }
}
