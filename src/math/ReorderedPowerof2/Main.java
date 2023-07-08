package math.ReorderedPowerof2;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(125));
    }

    public static boolean reorderedPowerOf2(int n) {
        Set<Integer> powersOf2 = new HashSet<>();
        powersOf2.add(1);
        int base = 2;
        int pow = 2;
        while (Math.pow(base, pow) < Integer.MAX_VALUE) {
            powersOf2.add((int) Math.pow(base, pow++));
        }

        return dfs(String.valueOf(n), powersOf2, new HashSet<>());
    }

    private static boolean dfs(String str, Set<Integer> powersOf2, Set<String> visited) {
        for (int i = 0; i < str.length(); i++) {
            String cur = swap(0, i, str);
            if (!visited.contains(cur)) {
                if (isPowerOf2(powersOf2, cur)) return true;
                if (dfs(cur, powersOf2, visited)) return true;
            }
            visited.add(cur);
        }
        return false;
    }

    private static boolean isPowerOf2(Set<Integer> powersOf2, String s) {
        try {
            return powersOf2.contains(Integer.parseInt(s)) && s.charAt(0) != '0';
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String swap(int i, int j, String str) {
        return str.substring(0, i) + str.charAt(j) + str.substring(i+1, j) + str.charAt(i) + str.substring(j+1);
    }
}
