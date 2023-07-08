package arrays.KClosestPointstoOrigin;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] points = {{3,3},{5,-1},{-2,4}};
        System.out.println(Arrays.deepToString(kClosest(points, 2)));
    }

    public static int[][] kClosest(int[][] points, int k) {
        int[][] res = new int[k][2];
        quickSelect(points, k, 0, points.length-1);
        for (int i = 0; i < k; i++) res[i] = points[i];
        return res;
    }

    private static void quickSelect(int[][] points, int k, int l, int r) {
        if (l < r) {
            int partitionIndex = partition(l, r, points);
            if (partitionIndex + 1 > k) {
                quickSelect(points, k, 0, r-1);
            } else if (partitionIndex + 1 < k) {
                quickSelect(points, k, l+1, r);
            }
        }
    }

    private static int partition(int l, int r, int[][] points) {
        double pivot = getDistanceToOrigin(points[r]);
        int j = -1;
        for (int i = l; i < r; i++) {
            if (getDistanceToOrigin(points[i]) < pivot) {
                swap(i, ++j, points);
            }
        }
        swap(++j, r, points);
        return j;
    }

    private static void swap(int i, int j, int[][] points) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private static double getDistanceToOrigin(int[] point) {
        return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
    }
}
