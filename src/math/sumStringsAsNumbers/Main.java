package math.sumStringsAsNumbers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        System.out.println(sumStrings("99999", "9999"));
        System.out.println(sumStrings("99999", "99999"));
        System.out.println(sumStrings("11", "111"));
        System.out.println(sumStrings("082234466262184618378756455", "301703744438338414"));
        System.out.println(sumStrings("00103", "08567"));

        BigDecimal three = new BigDecimal(3);
        BigDecimal ten = new BigDecimal(10);
        System.out.println(ten.divide(three, MathContext.DECIMAL128));
    }

    public static String sumStrings(String a, String b) {
        boolean isASmaller = a.length() < b.length();
        int length = Math.max(a.length(), b.length());
        int diff = Math.abs(a.length() - b.length());
        if (isASmaller) {
            StringBuilder aBuilder = new StringBuilder(a);
            for (int i = 0; i < diff; i++) aBuilder.insert(0, "0");
            a = aBuilder.toString();
        }
        else if (diff != 0) {
            StringBuilder bBuilder = new StringBuilder(b);
            for (int i = 0; i < diff; i++) bBuilder.insert(0, "0");
            b = bBuilder.toString();
        }
        int carry = 0;
        StringBuilder res = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            int x = Character.getNumericValue(a.charAt(i));
            int y = Character.getNumericValue(b.charAt(i));
            res.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        if (carry == 1) res.append(carry);
        int i = res.length() - 1;
        while (res.charAt(i) == '0') res.deleteCharAt(i--);
        return res.reverse().toString();
    }

}
