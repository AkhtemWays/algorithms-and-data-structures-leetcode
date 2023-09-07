package number_theory.FactorCombinations;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        System.out.println(getFactors(1));
//        System.out.println(getFactors(2));
//        System.out.println(getFactors(10000000));
//        System.out.println(getFactors(12));
//        System.out.println(getFactors(8));
//        System.out.println(getFactors(32));
        System.out.println(getFactors(4));
        System.out.println(Math.sqrt(10000000));
    }

    private static Set<List<Integer>> answer;
    public static List<List<Integer>> getFactors(int n) {
        answer = new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 2; i < Math.sqrt(n)+1; i++) {
            if (n % i == 0) {
                cur.add(i);
                getFactors(n / i, cur);
                cur.remove(cur.size()-1);
            }
        }
        return answer.stream().filter(l -> l.size() != 1).collect(Collectors.toList());
    }

    private static void getFactors(int n, List<Integer> cur) {
        if (n == 1) {
            List<Integer> copy = new ArrayList<>(cur);
            Collections.sort(copy);
            answer.add(copy);
        }
        else {
            for (int i = 2; i <= Math.sqrt(n)+1; i++) {
                if (n % i == 0) {
                    cur.add(i);
                    getFactors(n / i, cur);
                    cur.remove(cur.size()-1);
                }
            }
            List<Integer> copy = new ArrayList<>(cur);
            copy.add(n);
            Collections.sort(copy);
            answer.add(copy);
        }
    }
}
