package backtracking.ShoppingOffers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    private void test1() {
        List<Integer> price = List.of(2,5);
        List<List<Integer>> special = List.of(List.of(3,0,5),List.of(1,2,10));
        List<Integer> needs = List.of(3,2);
        System.out.println(shoppingOffers(price, special, needs));
    }
    private void test2() {
        List<Integer> price = List.of(2,3,4);
        List<List<Integer>> special = List.of(List.of(1,1,0,4),List.of(2,2,1,9));
        List<Integer> needs = List.of(1,2,1);
        System.out.println(shoppingOffers(price, special, needs));
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.test1(); // 14
        main.test2(); // 11
    }

    private int[] needs;
    private int[] price;
    private int n;
    private int offersSize;
    private int[][] special;
    HashSet<String> cache = new HashSet<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        n = needs.size();
        this.needs = new int[n];
        this.price = new int[n];
        this.offersSize = special.size();
        for (int i = 0; i < n; i++) {
            this.needs[i] = needs.get(i);
            this.price[i] = price.get(i);
        }

        this.special = new int[offersSize][n+1];
        for (int i = 0; i < offersSize; i++) {
            for (int j = 0; j < n+1; j++) {
                this.special[i][j] = special.get(i).get(j);
            }
        }
        int[] needsWithCost = new int[n+1];
        for (int i = 0; i < n; i++) needsWithCost[i] = this.needs[i];

        int[] res = dfs(needsWithCost);
        int offerCosts = res[n];
        for (int i = 0; i < n; i++) {
            offerCosts += this.price[i] * res[i];
        }
        return offerCosts;
    }

    private int[] dfs(int[] needsWithCost) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(needsWithCost[i]);
        String key = sb.toString();
        if (cache.contains(key)) {
            int[] cached = new int[n+1];
            Arrays.fill(cached, 10000);
            return cached;
        }
        int[] curOptimal = Arrays.copyOf(needsWithCost, needsWithCost.length);
        for (int i = 0; i < offersSize; i++) {
            int[] candidate = Arrays.copyOf(needsWithCost, n+1);
            int costWithSpecialOffer = special[i][n];
            int costWithoutSpecialOffer = 0;
            for (int j = 0; j < n; j++) {
                candidate[j] -= special[i][j];
                costWithoutSpecialOffer += special[i][j] * price[j];
            }
            if (costWithoutSpecialOffer > costWithSpecialOffer && !exceedsNeeds(candidate)) {
                candidate[n] += costWithSpecialOffer;
                int[] candidateNeeds = dfs(candidate);
                if (isMoreOptimal(candidateNeeds, curOptimal)) {
                    curOptimal = candidateNeeds;
                }
            }
        }
        cache.add(key);

        return curOptimal;
    }

    private boolean isMoreOptimal(int[] arr1, int[] arr2) {
        int cost1 = arr1[n], cost2 = arr2[n];
        for (int i = 0; i < n; i++) {
            cost1 += price[i] * arr1[i];
            cost2 += price[i] * arr2[i];
        }
        return cost1 < cost2;
    }

    private boolean exceedsNeeds(int[] curNeeds) {
        for (int i = 0; i < n; i++) {
            if (curNeeds[i] < 0) return true;
        }
        return false;
    }
}
