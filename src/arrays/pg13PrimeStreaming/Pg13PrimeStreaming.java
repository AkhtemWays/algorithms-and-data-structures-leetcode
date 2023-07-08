package arrays.pg13PrimeStreaming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Pg13PrimeStreaming {

    static int LIMIT = 15 * 1000 * 1000 + 100000 * 5;
    static int MAX_NUMBER = 20000000;

    public static IntStream stream() {
        return IntStream.range(2, LIMIT)
                .parallel()
                .filter(Pg13PrimeStreaming::isPrime);
    }

    public static boolean isPrime(int num) {
        if (num%2 == 0 && num > 2) return false;
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < top; i+=2)
            if(num % i == 0) return false;
        return true;
    }

    public static IntStream stream2() {
        Pg13PrimeStreaming pg13PrimeStreaming = new Pg13PrimeStreaming();
        List<Integer> integerList = pg13PrimeStreaming.sieve();
        return integerList.stream().mapToInt(e -> e);
    }

    private List<Integer> sieve() {
        int[] sieve = new int[MAX_NUMBER];
        List<Integer> primes = new ArrayList<>();

        sieve[1] = 1;
        sieve[0] = 1;

        for (int p = 2; p < MAX_NUMBER; p++) {
            if (sieve[p] == 1) continue;
            primes.add(p);
            for (int j = p + p; j < MAX_NUMBER; j += p) sieve[j] = 1;
        }

        return primes;
    }
}
