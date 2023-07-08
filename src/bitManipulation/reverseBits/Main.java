package bitManipulation.reverseBits;

public class Main {
    public static void main(String[] args) {
        printBinary(-160000);
        System.out.println(reverseBits(-16));
    }

    public static int reverseBits(int n) {
        StringBuilder sb = new StringBuilder(32);
        if (n > 0) {
            String firstValue = String.valueOf((n & ((1 << 31) - 1)) != 0 ? 1 : 0);
            sb.append(firstValue);
            for (int x = 30; x >= 0; x--) {
                if ((n & (1 << x)) != 0) sb.append(1);
                else sb.append(0);
            }
            String binaryReversed = sb.reverse().toString();
            int res = 0;
            for (int x = binaryReversed.length() - 1; x >= 0; x--) {
                int power = binaryReversed.length() - 1 - x;
                res += Character.getNumericValue(binaryReversed.charAt(x)) * Math.pow(2, power);
            }
            return res - 1;
        } else {
            sb.append(1);
            for (int x = 31; x >= 0; x--) {
                if ((n & (1 << x)) != 0) sb.append(1);
                else sb.append(0);
            }
            int res = 0;
            String binaryReversed = sb.reverse().toString();
            System.out.println(binaryReversed);
            for (int x = binaryReversed.length() - 1; x >= 0; x--) {
                int power = binaryReversed.length() - 1 - x;
                res += Character.getNumericValue(binaryReversed.charAt(x)) * Math.pow(2, power);
            }
            return res - 1;
        }
    }

    public static int reverseBits2(int num) {

        num = ((num & 0xffff0000) >>> 16) | ((num & 0x0000ffff) << 16);
        num = ((num & 0xff00ff00) >>> 8) | ((num & 0x00ff00ff) << 8);
        num = ((num & 0xf0f0f0f0) >>> 4) | ((num & 0x0f0f0f0f) << 4);
        num = ((num & 0xcccccccc) >>> 2) | ((num & 0x33333333) << 2);
        num = ((num & 0xaaaaaaaa) >>> 1) | ((num & 0x55555555) << 1);

        return num;

    }

    static void printBinary(int n) {
        for (int x = 31; x >= 0; x--) {
            if ((n & (1 << x)) != 0) System.out.print(1);
            else System.out.print(0);
        }
        System.out.println();
    }
}
