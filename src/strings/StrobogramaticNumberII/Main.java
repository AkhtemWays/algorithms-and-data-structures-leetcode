package strings.StrobogramaticNumberII;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(findStrobogrammatic(11));
    }
    private static final char[] nums = {'0','1','6','8','9'};

    public static List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, "", res);
        if (n > 1) {
            return res.stream().filter(num -> !num.startsWith("0")).collect(Collectors.toList());
        }
        return res;
    }

    private static void dfs(int n, String cur, List<String> res) {
        if (cur.length() == n) {
            if (isStrobogrammatic(cur)) res.add(cur);
        } else {
            for (char ch : nums) {
                dfs(n, cur + ch, res);
            }
        }
    }

    private static boolean isStrobogrammatic(String num) {
        StringBuilder sb = new StringBuilder();
        for (char ch : num.toCharArray()) {
            if (ch != '8' && ch != '9' && ch != '6' && ch != '1' && ch != '0') return false;
            if (ch == '6') {
                sb.append('9');
            } else if (ch == '9') {
                sb.append('6');
            } else {
                sb.append(ch);
            }
        }
        return new StringBuilder(num).reverse().toString().equals(sb.toString());
    }
}
