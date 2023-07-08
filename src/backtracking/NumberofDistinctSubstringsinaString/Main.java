package backtracking.NumberofDistinctSubstringsinaString;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println(countDistinct("aabbaba"));
    }

    public static int countDistinct(String s) {
        HashSet<String> set = new HashSet<>();
        dfs(0, s, set, new HashSet<>(), new StringBuilder());
        return set.size();
    }

    private static void dfs(int i, String s, HashSet<String> set, HashSet<String> memo, StringBuilder cur) {
        if (i >= s.length()) {
            return;
        }
        String curString = cur.toString();
        String str = i + "_" + cur;
        if (memo.contains(str)) {
            return;
        }

        memo.add(str);
        cur.append(s.charAt(i));
        set.add(curString);

        dfs(i+1, s, set, memo, cur);

        cur.deleteCharAt(cur.length()-1);
        dfs(i+1, s, set, memo, cur);
        memo.add(str);
    }
}
