package strings.MultiplyStrings;

public class Main {
    public static void main(String[] args) {
        System.out.println(multiply("623", "18"));
        System.out.println(multiply("3", "2"));
        System.out.println(multiply("123", "456"));
    }

    public static String multiply(String num1, String num2) {
        if (Integer.parseInt(num1) == 0 || Integer.parseInt(num2) == 0) return "0";
        if (num1.length() < num2.length()) return multiply(num2, num1);

        String[] multiplication = new String[num2.length()];
        for (int i = 0; i < multiplication.length; i++) {
            int value = Character.getNumericValue(num2.charAt(num2.length()-1-i));
            String res = multiply(num1, value);
            res += "0".repeat(i);
            multiplication[i] = res;
        }
        String res = multiplication[0];
        for (int i = 1; i < multiplication.length; i++) {
            res = sum(res, multiplication[i]);
        }
        return res.startsWith("0") ? "0" : res;
    }

    private static String multiply(String num1, int value) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length()-1; i >= 0; i--) {
            int num = Character.getNumericValue(num1.charAt(i));
            int product = (value * num) + carry;
            int remainder = product % 10;
            sb.append(remainder);
            carry = product / 10;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    private static String sum(String num1, String num2) {
        if (num1.length() < num2.length()) return sum(num2, num1);
        int carry = 0;
        int diff = num1.length() - num2.length();
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            int val1 = Character.getNumericValue(num1.charAt(i));
            int val2 = i - diff >= 0 ? Character.getNumericValue(num2.charAt(i - diff)) : 0;
            int sum = val1 + val2 + carry;
            int remainder = sum % 10;
            sb.append(remainder);
            carry = sum / 10;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
