package bitManipulation.SumofTwoIntegers;

public class Main {
    public static void main(String[] args) {
        System.out.println(getSum(1, 2));
        System.out.println(getSum(2, 3));
    }

    public static int getSum(int a, int b) {
        int res = 0;
        for (int p = 0, carry = 0; p < 32; p++) {
            int sum = (a >> p & 1) + (b >> p & 1) + carry;
            carry = sum / 2;
            int remainder = sum % 2;
            res += remainder << p;
        }

        return res;
    }
}
