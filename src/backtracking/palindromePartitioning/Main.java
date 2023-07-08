package backtracking.palindromePartitioning;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        partition("abcacba").forEach(list -> {
            list.forEach(el -> System.out.print(el + ", "));
            System.out.println();
        });
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs(0, new ArrayList<>(), result, s);
        return result;
    }

    private static void dfs(int start, List<String> cur, List<List<String>> result, String s) {
        if (start >= s.length()) {
            result.add(List.copyOf(cur));
            return;
        }

        for (int i = start + 1; i < s.length()+1; i++) {
            String substr = s.substring(start, i);
            if (isPalindrome(substr)) {
                cur.add(substr);
                dfs(i, cur, result, s);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }
        return true;
    }
}
