package bitManipulation.NumberComplement;

public class MyOwn {
    public static void main(String[] args) {
        System.out.println(bitwiseComplement(5));
        System.out.println(bitwiseComplement(7));
        System.out.println(bitwiseComplement(10));
    }

    public static int bitwiseComplement(int n) {
        int todo = n;
        int mask = 1;
        while (todo > 0) {
            n = n ^ mask;
            mask <<= 1;
            todo >>= 1;
        }
        return n;
    }
}
