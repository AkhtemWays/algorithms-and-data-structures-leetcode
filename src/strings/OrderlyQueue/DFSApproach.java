package strings.OrderlyQueue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DFSApproach {
    private static StringBuilder result;
    private static final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static void main(String[] args) {
        System.out.println(orderlyQueue("gxzv", 4));
        System.out.println(orderlyQueue("cba", 1));
        System.out.println(orderlyQueue("baaca", 3));
        System.out.println(orderlyQueue("nhtq", 1));
        System.out.println(orderlyQueue("mqvgtdfuiv", 10));
        System.out.println(orderlyQueue("tk", 1));
        System.out.println(orderlyQueue("adguvpsubc", 2));
    }

    public static String orderlyQueue(String s, int k) {
        if (k >= s.length()) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            StringBuilder res = new StringBuilder();
            for (char ch : chars) res.append(ch);
            return res.toString();
        }
        StringBuilder sb = new StringBuilder(s);
        result = new StringBuilder(sb);
        dfs(sb, k, new HashSet<>());
        return result.toString();
    }

    private static void dfs(StringBuilder sb, int k, Set<String> visited) {
        String s = sb.toString();
        if (visited.contains(s)) return;
        visited.add(s);
        for (int i = 0; i < k; i++) {
            char ch = sb.charAt(i);
            sb.deleteCharAt(i);
            sb.append(ch);
            if (optimal(sb)) {
                result = new StringBuilder(sb);
            }
            dfs(sb, k, visited);
            sb.deleteCharAt(sb.length()-1);
            sb.insert(i, ch);
        }
    }

    private static boolean optimal(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            char resultChar = result.charAt(i);
            char sbChar = sb.charAt(i);
            if (alphabet[sbChar-'a'] < alphabet[resultChar-'a']) return true;
            else if (alphabet[sbChar-'a'] > alphabet[resultChar-'a']) return false;
        }
        return false;
    }
}
