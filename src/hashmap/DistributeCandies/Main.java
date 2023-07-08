package hashmap.DistributeCandies;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }

    public static int distributeCandies(int[] candyType) {
        int n = candyType.length;
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int candy : candyType) {
            counts.put(candy, counts.getOrDefault(candy, 0) + 1);
        }
        return Math.min(n / 2, counts.size());
    }
}
