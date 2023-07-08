package greedy.MaximumIceCreamBars;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] costs = {1,3,2,4,1};
        Main main = new Main();
        System.out.println(main.maxIceCream(costs, 7));
    }

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int numCoins = 0;
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] > coins) break;
            coins -= costs[i];
            numCoins++;
        }
        return numCoins;
    }
}
