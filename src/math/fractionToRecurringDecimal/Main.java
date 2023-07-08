package math.fractionToRecurringDecimal;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(4, 333)); // "0.(012)"
        System.out.println(fractionToDecimal(10, 3)); // "3.(3)"
        System.out.println(fractionToDecimal(18, 4)); // "4.5"
        System.out.println(fractionToDecimal(2, 1));
        System.out.println(fractionToDecimal(1, 15));
        System.out.println(fractionToDecimal(-50, 8));
        System.out.println(fractionToDecimal(-4, 333));
        System.out.println(fractionToDecimal(-4, -333));
        System.out.println(fractionToDecimal(-1, Integer.MIN_VALUE));
        System.out.println();
        System.out.println(fractionToDecimal2(-1, Integer.MAX_VALUE));
    }

    public static String fractionToDecimal(int numer, int denom) {
        long numerator = numer;
        long denominator = denom;
        if (numerator % denominator == 0) return String.valueOf(numerator / denominator);
        String base = "";
        if (numerator < 0 ^ denominator < 0) base += "-";
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        base = base + (numerator / denominator) + ".";
        long remainder = Math.abs(numerator % denominator);
        StringBuilder res = new StringBuilder();
        Map<Long, Integer> map = new HashMap<>();
        int i = 0;
        while (remainder != 0 && (!map.containsKey(remainder))) {
            map.put(remainder, i++);
            remainder *= 10;
            res.append(remainder / denominator);
            if (remainder > denominator) remainder %= denominator;
        }
        if (remainder == 0) return base + res;
        int startIdx = map.get(remainder);
        return base + res.substring(0, startIdx) + "(" + res.substring(startIdx, res.length()) + ")";
    }

    public static String fractionToDecimal2(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        String sign = (numerator < 0 == denominator < 0 || numerator == 0) ? "" : "-";
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        result.append(sign);
        result.append(num / den);
        long remainder = num % den;
        if (remainder == 0)
            return result.toString();
        result.append(".");
        HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();
        while (!hashMap.containsKey(remainder)) {
            hashMap.put(remainder, result.length());
            result.append(10 * remainder / den);
            remainder = 10 * remainder % den;
        }
        int index = hashMap.get(remainder);
        result.insert(index, "(");
        result.append(")");
        return result.toString().replace("(0)", "");
    }
}
