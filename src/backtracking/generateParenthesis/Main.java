package backtracking.generateParenthesis;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        balancedParens(1).forEach(System.out::println);
    }

    public static List<String> balancedParens(int n) {
        if (n == 0) return List.of("");

        List<String> res = new ArrayList<>();
        dfs("", res, n, n, n);
        return res;
    }

    static void dfs(String cur, List<String> res, int left, int right, int n) {
        if (cur.length() == 2*n) {
            res.add(cur);
            return;
        }

        if (left > 0) dfs(cur + "(",  res, left - 1, right, n);
        if (right > 0 && right > left) dfs(cur + ")",  res, left, right - 1, n);

    }
}
