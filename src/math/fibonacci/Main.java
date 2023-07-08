package math.fibonacci;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println(new BigInteger("-1").compareTo(BigInteger.ZERO) < 0);

    }

    public static BigInteger fib(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) > 0) return calc(n)[0];
        BigInteger res = calc(n)[0];
        if (n.compareTo(BigInteger.ZERO) < 0) {
            return n.mod(BigInteger.TWO).equals(BigInteger.ZERO) ? new BigInteger("-" + res) : res;
        }
        return res;
    }

    public static BigInteger[] calc(BigInteger n) {
        if (n.equals(BigInteger.ZERO)) return new BigInteger[]{BigInteger.ZERO, BigInteger.ONE};
        else if (n.equals(BigInteger.ONE)) return new BigInteger[]{BigInteger.ONE, BigInteger.ONE};

        BigInteger[] res = calc(n.divide(BigInteger.TWO));
        BigInteger a = res[0];
        BigInteger b = res[1];
        BigInteger p = a.multiply(BigInteger.TWO.multiply(b).subtract(a));
        BigInteger q = b.multiply(b).add(a.multiply(a));
        return n.mod(BigInteger.TWO).equals(BigInteger.ZERO) ? new BigInteger[]{p, q} : new BigInteger[]{q, p.add(q)};
    }
}
