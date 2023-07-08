package stacks_and_queues.MinimumTimetoMakeRopeColorful;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {

    }

    public static int minCost(String colors, int[] neededTime) {
        int minTime = 0;
        for (int i = 1, prev = 0; i < colors.length(); i++) {
            if (colors.charAt(i) == colors.charAt(prev)) {
                if (neededTime[i] < neededTime[prev]) {
                    minTime += neededTime[i];
                } else {
                    minTime += neededTime[prev];
                    prev = i;
                }
            } else {
                prev = i;
            }
        }
        return minTime;
    }
}
