package math.lastDigitsOfNSquared;

import java.math.BigInteger;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        System.out.println(get(5));
    }

    public static BigInteger get(int n) {
        if (n == 1) {
            return BigInteger.ONE;
        }

        TreeSet<BigInteger> treeSet = new TreeSet<>();
        addNumber(treeSet, n + 1);
        BigInteger[] bigIntegers = treeSet.toArray(new BigInteger[]{});
        return bigIntegers[n - 2];
    }

    public static void addNumber(TreeSet<BigInteger> treeSet, int number) {
        int digitNo = 2;
        int counter = 2;
        BigInteger num = new BigInteger("5");
        treeSet.add(num);
        BigInteger num2 = new BigInteger("6");
        treeSet.add(num2);
        while (counter < number) {
            BigInteger numSquared = num.multiply(num);
            BigInteger powOfTen = new BigInteger("10").pow(digitNo);
            num = numSquared.mod(powOfTen);
            num2 = powOfTen.add(new BigInteger("1")).subtract(num);
            treeSet.add(num);
            treeSet.add(num2);
            counter += 2;
            digitNo++;
        }
    }
}
