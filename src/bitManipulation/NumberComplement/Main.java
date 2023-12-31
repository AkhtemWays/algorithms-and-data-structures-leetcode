package bitManipulation.NumberComplement;

public class Main {
    public static void main(String[] args) {
        System.out.println(~-6);
    }

    public static int findComplement(int num) {
        int todo = num, bit = 1;
        while (todo != 0) {
            // flip current bit
            num = num ^ bit;
            // prepare for the next run
            bit = bit << 1;
            todo = todo >> 1;
        }
        return num;
    }
}
