package greedy.MaximumBagsWithFullCapacityofRocks;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

    }

    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int fullBags = 0;
        int n = capacity.length;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) diffs[i] = capacity[i] - rocks[i];
        Arrays.sort(diffs);
        for (int diff : diffs) {
            if (additionalRocks >= diff) {
                additionalRocks -= diff;
                fullBags++;
            } else {
                break;
            }
        }
        return fullBags;
    }
}
