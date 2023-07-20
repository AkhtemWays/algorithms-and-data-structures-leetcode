package strings.ValidNumber;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println(isNumber("0")); // true
        System.out.println(isNumber("e")); // false
        System.out.println(isNumber(".")); // false
        System.out.println(isNumber("44e016912630333")); // true
        System.out.println(isNumber("4335e6773123995")); // true
    }

    public static boolean isNumber(String s) {
        try {
            BigDecimal bigDecimal = new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            if (e.getMessage() != null && (e.getMessage().equals("Too many nonzero exponent digits.") || e.getMessage().equals("Exponent overflow."))) return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isNumberWithoutSign(String s) {
        if (s.charAt(0) == '+' || s.charAt(0) == '-') return false;
        try {
            BigDecimal bigDecimal = new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            if (e.getMessage() != null && (e.getMessage().equals("Too many nonzero exponent digits.") || e.getMessage().equals("Exponent overflow."))) return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean containsIllegalCharacters(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isAlphabetic(ch) && Character.toLowerCase(ch) != 'e') {
                return true;
            }
        }
        return false;
    }
}
