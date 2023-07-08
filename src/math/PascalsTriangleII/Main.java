package math.PascalsTriangleII;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(getRow(11));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int r = 1; r <= rowIndex; r++) {
            double coef = getCoefficient(rowIndex, r);
            double value = ((double) res.get(res.size()-1)) * coef;
            int num = (int) Math.round(Double.valueOf(value));

            res.add(num);
        }
        return res;
    }

    private static double getCoefficient(int n, int r) {
        double numerator = (n - r + 1);
        double denominator = r;
        return numerator / denominator;
    }
}
