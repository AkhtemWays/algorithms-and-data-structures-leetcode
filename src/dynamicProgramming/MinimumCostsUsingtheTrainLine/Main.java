package dynamicProgramming.MinimumCostsUsingtheTrainLine;

import java.util.Arrays;

public class Main {
    private static void test1() {
        int[] regular = {1,6,9,5};
        int[] express = {5,2,3,10};
        System.out.println(Arrays.toString(minimumCosts(regular, express, 8)));
    }
    private static void test2() {
        int[] regular = {11,5,13};
        int[] express = {7,10,6};
        System.out.println(Arrays.toString(minimumCosts(regular, express, 3)));
    }
    private static void test3() {
        int[] regular = {30,5,9};
        int[] express = {1,10,1};
        System.out.println(Arrays.toString(minimumCosts(regular, express, 8)));
    }
    public static void main(String[] args) {
        test1(); // [1, 7, 14, 19]
        test2(); // [10, 15, 24]
        test3(); // [9, 14, 20]
    }

    private static long[][] memo;

    public static long[] minimumCosts(int[] regular, int[] express, int expressCost) {
        memo = new long[2][regular.length];
        solveByDp(regular, express, expressCost, regular.length - 1, 0); // start from end index because total cost expected at end station and second 0 because start from regular
        //0 always because we start from regular and anyway it will be minimum cost between regular and express route
        return memo[0];
    }

    private static long solveByDp(int[] regular, int[] express, int expressfromRegularCost, int index, int expressFlag) {
        if(index < 0) {
            return 0L;
        }
        if(memo[expressFlag][index] != 0) {
            return memo[expressFlag][index];
        }
        long expressSwitchcost = expressFlag == 1 ? 0 : expressfromRegularCost;
        // we add index - 1 cost as we want to count the total cost taken to reach her
        long regularCost = regular[index] + solveByDp(regular, express, expressfromRegularCost, index - 1, 0); // no cost to switch from express to regular
        long expressCost = express[index] + solveByDp(regular, express, expressfromRegularCost, index - 1, 1) + expressSwitchcost; // cost to switch from  regular to express
        return memo[expressFlag][index] = Math.min(regularCost, expressCost);
    }
}
