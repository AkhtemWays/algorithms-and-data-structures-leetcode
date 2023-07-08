package number_theory.CountPrimes;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        sieveOfEratosthenes(2000);
    }

    public static int sieveOfEratosthenes(int n) {
        boolean[] visited = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!visited[i]) {
                for (int div = 2 * i; div < n; div += i) {
                    visited[div] = true;
                }
            }
        }
        int answer = 0;
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (!visited[i]) {
                answer++;
                primes.add(i);
            }
        }
        return answer;
    }
}
