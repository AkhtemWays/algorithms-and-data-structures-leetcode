package strings.StringtoInteger;

public class Main {
    public static void main(String[] args) {
//        System.out.println(myAtoi("42"));
//        System.out.println(myAtoi("   -42"));
//        System.out.println(myAtoi("4193 with word"));
//        System.out.println(myAtoi("-419312434342132"));
//        System.out.println(myAtoi("words and 987"));
//        System.out.println(myAtoi("+-12"));
        System.out.println(myAtoi("+1"));
    }

    public static int myAtoi(String s) {
        s = s.stripLeading();
        if (s.isBlank()) return 0;
        StringBuilder sb = new StringBuilder();
        int start = s.charAt(0) == '-' || s.charAt(0) == '+' ? 1 : 0;
        if (start == 1) {
            sb.append(s.charAt(0));
        }
        for (int i = start; i < s.stripLeading().length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                break;
            }
        }
        String res = sb.toString();
        if (res.isBlank() || (res.length() == 1 && (res.charAt(0) == '-' || res.charAt(0) == '+'))) return 0;
        try {
            return Integer.parseInt(res);
        } catch (NumberFormatException e) {
            if (res.charAt(0) == '-') return Integer.MIN_VALUE;
            return Integer.MAX_VALUE;
        }
    }
}
