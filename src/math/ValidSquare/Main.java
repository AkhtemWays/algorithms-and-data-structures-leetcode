package math.ValidSquare;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] restForP1 = {p2, p3, p4};
        int[][] restForP2 = {p1, p3, p4};
        int[][] restForP3 = {p2, p1, p4};
        int[][] restForP4 = {p2, p3, p1};
        return isValid(p1, restForP1) && isValid(p2, restForP2) && isValid(p3, restForP3) && isValid(p4, restForP4);
    }

    private boolean isValid(int[] curPoint, int[][] rest) {
        double[] distances = new double[3];
        for (int i = 0; i < distances.length; i++) {
            int[] point = rest[i];
            double distance = getDistance(curPoint, point);
            distances[i] = distance;
        }
        Arrays.sort(distances);
        if (distances[0] != distances[1]) return false;
        if (Math.sqrt(Math.pow(distances[0], 2) + Math.pow(distances[1], 2)) != distances[2]) return false;
        return true;
    }

    private double getDistance(int[] p1, int[] p2) {
        double xSquared = Math.pow(Math.abs(p1[0] - p2[0]), 2);
        double ySquared = Math.pow(Math.abs(p1[1] - p2[1]), 2);
        return Math.sqrt(xSquared + ySquared);
    }
}
