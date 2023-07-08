package number_theory.SelfDividingNumbers;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(selfDividingNumbers(47, 55));
    }

    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int num = left; num <= right; num++) {
            boolean isDivisible = true;
            for (char ch : String.valueOf(num).toCharArray()) {
                int val = Integer.parseInt(ch + "");
                if (val == 0 || num % Integer.parseInt(ch + "") != 0) {
                    isDivisible = false;
                    break;
                }
            }
            if (isDivisible) res.add(num);
        }
        return res;
    }
}
