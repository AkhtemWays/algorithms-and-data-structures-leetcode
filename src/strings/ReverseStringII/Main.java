package strings.ReverseStringII;

public class Main {
    public static void main(String[] args) {
//        System.out.println(reverseStr("abcdefg", 2));
//        System.out.println(reverseStr("abcd", 2));
        System.out.println(reverseStr("ab", 2));
    }

    public static String reverseStr(String s, int k) {
        StringBuilder res = new StringBuilder();
        int startIdx = 0;
        int endIndex = 2 * k;
        while (startIdx < s.length()) {
            res.append(reverseFirstK(s.substring(startIdx, Math.min(endIndex, s.length())), k));
            startIdx = endIndex;
            endIndex += 2 * k;
        }
        return res.toString();
    }

    private static String reverseFirstK(String s, int k) {
        return new StringBuilder(s.substring(0, Math.min(k, s.length()))).reverse() +
                (k >= s.length() ? "" : s.substring(k));
    }
}
