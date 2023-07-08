package strings.EqualRationalNumbers;

import java.util.Arrays;

public class Main {
    private static void test1() {
        System.out.println(isRationalEqual("0.(52)", "0.5(25)"));
    }
    private static void test2() {
        System.out.println(isRationalEqual("0.1666(6)", "0.166(66)"));
    }
    private static void test3() {
        System.out.println(isRationalEqual("0.(99)", "1."));
    }
    private static void test4() {
        System.out.println(isRationalEqual("0.9(9)", "1."));
        System.out.println();
    }
    public static void main(String[] args) {
        test1(); // true
        test2(); // true
        test3(); // true
        test4(); // true
    }

    public static boolean isRationalEqual(String s, String t) {
        s = pad(s);
        t = pad(t);
        if (s.contains("(")) s = expand(s);
        if (t.contains("(")) t = expand(t);
        return s.compareTo(t) == 0;
    }

    private static String pad(String s) {
        if (s.endsWith(".")) return s + "0";
        return s;
    }

    private static String expand(String s) {
        int idx = s.indexOf('(');
        String repeatedPart = s.substring(idx+1, s.length()-1);
        if (containsOnlyNines(repeatedPart)) {
            if (s.charAt(idx-1) == '.') {
                char lastDigit = s.charAt(idx-2);
                return s.substring(0, idx-2) + getNextDigit(lastDigit) + "." + "0";
            } else {
                char lastDigit = s.charAt(idx-1);
                return s.substring(0, idx-1) + getNextDigit(lastDigit) + "0";
            }
        } else {
            String res = (s.substring(0, idx) + repeatedPart.repeat(5));
            return res.substring(0, Math.min(10, res.length()));
        }
    }

    private static boolean containsOnlyNines(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '9') return false;
        }
        return true;
    }

    private static char getNextDigit(char digit) {
        return ((Character.getNumericValue(digit) + 1) + "").charAt(0);
    }
}
