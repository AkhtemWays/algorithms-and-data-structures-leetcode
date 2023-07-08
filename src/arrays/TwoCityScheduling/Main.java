package arrays.TwoCityScheduling;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] costs1 = {{10,20},{30,200},{400,50},{30,20}};
        int[][] costs2 = {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};
//        System.out.println(twoCitySchedCost(costs1));
        System.out.println(twoCitySchedCost(costs2));
    }

    public static int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]));
        int AcountLeft = costs.length/2;
        int BcountLeft = costs.length/2;
        int totalCost = 0;
        for (int[] cost : costs) {
            if (Math.min(cost[0], cost[1]) == cost[0]) {
                if (AcountLeft > 0) {
                    totalCost += cost[0];
                    AcountLeft--;
                } else {
                    totalCost += cost[1];
                    BcountLeft--;
                }
            } else {
                if (BcountLeft > 0) {
                    totalCost += cost[1];
                    BcountLeft--;
                } else {
                    totalCost += cost[0];
                    AcountLeft--;
                }
            }
        }
        return totalCost;
    }
}
