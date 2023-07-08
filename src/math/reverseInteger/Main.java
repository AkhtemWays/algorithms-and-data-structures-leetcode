package math.reverseInteger;

public class Main {
    public static void main(String[] args) {
//        System.out.println(reverse(121));
//        System.out.println(reverse(-121));
//        System.out.println(reverse(-120));
        System.out.println(reverse(-2147483412));
    }

    public static int reverse(int x) {
        String num = String.valueOf(x);
        StringBuilder res = new StringBuilder();
        int endIndex = 0;
        if (x < 0) {
            res.append("-");
            endIndex = 1;
        }
        for (int i = num.length() - 1; i >= endIndex; i--) {
            res.append(num.charAt(i));
        }
        if (isCornerCase(res.toString())) return 0;
        return Integer.parseInt(res.toString());
    }

    static int reverse2(int x) {
        long rev= 0;
        while( x != 0){
            rev= rev*10 + x % 10;
            x= x/10;
            if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return (int) rev;
    }

    static boolean isCornerCase(String num) {
        String maxValue = String.valueOf(Integer.MAX_VALUE);
        int startIndex = 0;
        if (num.startsWith("-")) {
            maxValue = String.valueOf(Integer.MIN_VALUE);
            startIndex = 1;
        }
        if (num.length() > maxValue.length()) return true;
        if (num.length() < maxValue.length()) return false;
        for (int i = startIndex; i < num.length(); i++) {
            if (num.charAt(i) > maxValue.charAt(i)) return true;
            else if (num.charAt(i) < maxValue.charAt(i)) return false;
        }
        return false;
    }
}
