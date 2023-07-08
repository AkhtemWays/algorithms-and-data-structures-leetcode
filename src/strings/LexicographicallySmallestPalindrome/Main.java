package strings.LexicographicallySmallestPalindrome;

public class Main {
    public static void main(String[] args) {
        System.out.println(makeSmallestPalindrome("abcd"));
    }

    public static String makeSmallestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            sb.append(getMax(s.charAt(i), s.charAt(n-1-i)));
        }
        String end = sb.reverse().toString();
        if (n % 2 == 1) end = s.charAt(n/2) + end;
        return sb.reverse() + end;
    }

    private static char getMax(char ch1, char ch2) {
        if (ch1 - 'a' <= ch2 - 'a') return ch1;
        return ch2;
    }
}
