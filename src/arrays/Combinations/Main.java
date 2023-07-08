package arrays.Combinations;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.combine(4, 2).forEach(combination -> {
            combination.forEach(System.out::print);
            System.out.println();
        });
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void dfs(int i, int n, int k, List<Integer> cur, List<List<Integer>> result) {
        if (cur.size() == k) {
            result.add(List.copyOf(cur));
            return;
        }
        if (i > n) return;

        cur.add(i);
        dfs(i+1, n, k, cur, result);
        cur.remove(cur.size() - 1);
        dfs(i+1, n, k, cur, result);
    }
}
