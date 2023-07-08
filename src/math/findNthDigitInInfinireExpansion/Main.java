package math.findNthDigitInInfinireExpansion;

public class Main {
    public static void main(String[] args) {
        System.out.println(findDigit(-1));
    }

    public static int findDigit(int n) {
        if (n <= 0) return 0;
        StringBuilder x1 = new StringBuilder();
        StringBuilder x2 = new StringBuilder();
        for (long x = 1; x <= n; x++) {
            x1.append(x);
            x2.append(x*x);
        }
        String res = sumStrings(x1.toString(), x2.toString());
        System.out.println(res);
        return Character.getNumericValue(res.charAt(n));
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
