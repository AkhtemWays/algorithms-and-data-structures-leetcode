package strings.longestCommonPrefix;

public class Main {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String[] strs2 = {"dog","racecar","car"};
        String[] strs3 = {"ab", "a"};
        String[] strs4 = {"a", "ab"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix(strs2));
        System.out.println(longestCommonPrefix(strs3));
        System.out.println(longestCommonPrefix(strs4));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String lcp = strs[0];
        for (int j = 1; j < strs.length; j++) {
            String str = strs[j];
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < Math.min(lcp.length(), str.length()); i++) {
                if (str.charAt(i) != lcp.charAt(i)) {
                    break;
                }

                cur.append(str.charAt(i));
            }
            lcp = cur.toString();
            if (lcp.length() == 0) return "";
        }
        return lcp;
    }
}
