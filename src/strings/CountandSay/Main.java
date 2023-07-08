package strings.CountandSay;

public class Main {
    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }

    public static String countAndSay(int n) {
        if (n == 1) return "1";

        int k = 2;
        String cur = "11";
        while (k++ < n) {
            cur = countAndGet(cur);
        }
        return cur;
    }

    private static String countAndGet(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char ch = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ch) {
                count++;
            } else {
                sb.append(("" + count + ch));
                ch = s.charAt(i);
                count = 1;
            }
        }
        sb.append(("" + count + ch));
        return sb.toString();
    }
}
