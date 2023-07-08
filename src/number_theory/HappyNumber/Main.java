package number_theory.HappyNumber;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    public static boolean isHappy(int n) {
        Set<Integer> nums = new HashSet<>();
        while (!nums.contains(n) && n != 1) {
            nums.add(n);
            String s = String.valueOf(n);
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                res += Math.pow(Character.getNumericValue(s.charAt(i)), 2);
            }
            n = res;
        }
        return n == 1;
    }
}
