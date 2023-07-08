package arrays.pascalTriangle;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        generate(5).forEach(l -> {
            l.forEach(System.out::print);
            System.out.println();
        });
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> r = new ArrayList<>();
        r.add(new ArrayList<>(List.of(1)));
        if (numRows == 1) return r;

        int curNumRows = 1;
        while (++curNumRows <= numRows) {
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < curNumRows; i++) {
                List<Integer> cur = r.get(r.size() - 1);
                int left = i-1 >= 0 ? cur.get(i-1) : 0;
                int right = i < cur.size() ? cur.get(i) : 0;
                l.add(left + right);
            }
            r.add(l);
        }
        return r;
    }
}
