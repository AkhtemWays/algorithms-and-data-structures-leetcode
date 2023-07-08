package math.TotalIncreasingOrDecreasingNumbersUpToAPowerOf10;

import java.math.BigInteger;

public class Main {
    enum SequenceDirection {
        DECREASING, INCREASING
    }

    public static void main(String[] args) {
        System.out.println(totalIncDec(3));
    }

    public static BigInteger totalIncDec(int x) {
        BigInteger total = BigInteger.ZERO;
        for (BigInteger i = BigInteger.ONE, num = BigInteger.TEN.pow(x); i.divide(num).equals(BigInteger.ONE); i = i.add(BigInteger.ONE)) {
            if (isSpecial(i, SequenceDirection.DECREASING) || isSpecial(i, SequenceDirection.INCREASING)) total = total.add(BigInteger.ONE);
        }
        return total;
    }

    static boolean isSpecial(BigInteger num, SequenceDirection direction) {
        String str = String.valueOf(num);
        if (direction == SequenceDirection.DECREASING) {
            for (int i = 1; i < str.length(); i++) {
                if (Character.getNumericValue(str.charAt(i)) >= Character.getNumericValue(str.charAt(i-1))) return false;
            }
        } else {
            for (int i = 1; i < str.length(); i++) {
                if (Character.getNumericValue(str.charAt(i)) <= Character.getNumericValue(str.charAt(i-1))) return false;
            }
        }
        return true;
    }

}
