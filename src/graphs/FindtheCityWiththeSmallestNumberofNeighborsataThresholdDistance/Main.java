package graphs.FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance;

import java.util.Arrays;

public class Main {
    private static void test1() {
        int[][] edges = {{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}};
        System.out.println(findTheCity(5, edges, 2));
    }
    private static void test2() {
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        System.out.println(findTheCity(4, edges, 4));
    }
    private static void test3() {

    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], (int) 1e9);
            dp[i][i] = 0;
        }
        for (int[] edge : edges) {
            dp[edge[0]][edge[1]] = edge[2];
            dp[edge[1]][edge[0]] = edge[2];
        }

        for (int source = 0; source < n; source++) {
            for (int destination = 0; destination < n; destination++) {
                for (int intermediateNode = 0; intermediateNode < n; intermediateNode++) {
                    if (dp[source][destination] > dp[source][intermediateNode] + dp[intermediateNode][destination]) {
                        dp[source][destination] = dp[source][intermediateNode] + dp[intermediateNode][destination];
                    }
                }
            }
        }

        int city = -1;
        int lessThanThreshold = Integer.MAX_VALUE;
        for (int source = 0; source < n; source++) {
            int count = 0;
            for (int destination = 0; destination < n; destination++) {
                if (source != destination && dp[source][destination] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= lessThanThreshold) {
                city = source;
                lessThanThreshold = count;
            }
        }
        return city;
    }
}
