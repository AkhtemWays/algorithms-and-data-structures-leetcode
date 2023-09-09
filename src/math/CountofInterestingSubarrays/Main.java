package math.CountofInterestingSubarrays;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final BigInteger[] cache = initCache();
    private static void test1() {
        List<Integer> nums = List.of(3,2,4);
        System.out.println(countInterestingSubarrays(nums, 2, 1));
    }
    public static void main(String[] args) {
        test1();
    }

    public static long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long res = 0;
        int size = (int) nums.stream().filter(num -> num % modulo == k).count();
        for (int count = k; count <= size; count += modulo) {
            res += cache[size].divide(cache[count].multiply(cache[size-count])).longValue();
        }

        return res;
    }

    private static BigInteger[] initCache() {
        BigInteger[] cache = new BigInteger[10001];
        cache[0] = new BigInteger("1");
        cache[1] = new BigInteger("1");
        BigInteger res = new BigInteger("1");
        for (int i = 2; i < 10001; i++) {
            res = res.multiply(new BigInteger(String.valueOf(i)));
            cache[i] = res;
        }

        return cache;
    }
}
