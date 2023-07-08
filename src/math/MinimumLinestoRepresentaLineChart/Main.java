package math.MinimumLinestoRepresentaLineChart;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] stockLines = {{1,7},{2,6},{3,5},{4,4},{5,4},{6,3},{7,2},{8,1}};
        int[][] stockLines2 = {{3,4},{1,2},{7,8},{2,3}};
        System.out.println(main.minimumLines(stockLines));
        System.out.println(main.minimumLines(stockLines2));
    }

    public int minimumLines(int[][] stockPrices) {
        if (stockPrices.length <= 1) return 0;

        Arrays.sort(stockPrices, (a,b) -> a[0] - b[0] != 0 ? a[0] - b[0] : a[1] - b[1]);
        double coefficient = 0.0;
        int answer = 0;
        for (int i = 1; i < stockPrices.length; i++) {
            int[] curPoint = stockPrices[i];
            int[] prevPoint = stockPrices[i-1];
            double curCoefficient = getCoefficient(curPoint, prevPoint);
            if (curCoefficient != coefficient) {
                answer++;
                coefficient = curCoefficient;
            }
        }
        return answer;
    }

    private double getCoefficient(int[] curPoint, int[] prevPoint) {
        return ((double) prevPoint[0] - curPoint[0]) / ((double) prevPoint[1] - curPoint[1]);
    }
}
