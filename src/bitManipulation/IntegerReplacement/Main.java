package bitManipulation.IntegerReplacement;

import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
//        System.out.println(integerReplacement(7));
        System.out.println(integerReplacement(10000));
    }

    private static final TreeMap<Integer, Integer> definedResults = new TreeMap<>();
    public static int integerReplacement(int n) {
        if (definedResults.isEmpty()) {
            for (int i = 0; i < 31; i++) {
                definedResults.put(1 << i, i);
            }
        }

        int answer = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>= 1;
            } else {
                Map.Entry<Integer, Integer> higherEntry = definedResults.higherEntry(n);
                Map.Entry<Integer, Integer> lowerEntry = definedResults.lowerEntry(n);
                int plusChoice = higherEntry.getKey() - n + higherEntry.getValue();
                int minusChoice = n - lowerEntry.getKey() + lowerEntry.getValue();
                return answer + Math.min(Math.min(plusChoice, integerReplacement(n-1) + 1), minusChoice);
            }
            answer++;
        }
        return answer;
    }
}
