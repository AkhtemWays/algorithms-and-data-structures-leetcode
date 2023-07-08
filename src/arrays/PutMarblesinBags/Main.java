package arrays.PutMarblesinBags;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] weights = {1,3,5,1};
        System.out.println(putMarbles(weights, 2));
    }

    public static long putMarbles(int[] weights, int k) {
        int n = weights.length;
        int cost = weights[0] + weights[n-1];
        int[] pairs = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            pairs[i] = weights[i] + weights[i+1];
        }
        Arrays.sort(pairs);
        long max = 0, min = 0;
        for (int i = 0; i < k; i++) {
            min += pairs[i];
            max += pairs[n-2-i];
        }
        return max - min;
    }
}
