package dynamicProgramming.CountAllPossibleRoutes;

public class Main {
    private static void test1() {
        int[] loc = {2,3,6,8,4};
        System.out.println(countRoutes(loc, 1, 3, 5));
    }
    private static void test2() {
        int[] loc = {4,3,1};
        System.out.println(countRoutes(loc, 1, 0, 6));
    }
    public static void main(String[] args) {
        test1(); // 4
        test2(); // 5
    }

    private static final int MOD = 100_000_000_7;
    public static int countRoutes(int[] locations, int start, int finish, int fuel) {
        int[][] memo = new int[fuel+1][locations.length];
        for (int f = 0; f <= fuel; f++) {
            memo[f][finish] = 1;
        }
        for (int f = 1; f <= fuel; f++) {
            for (int curCity = 0; curCity < locations.length; curCity++) {
                for (int city = 0; city < locations.length; city++) {
                    int travelDistance = Math.abs(locations[city] - locations[curCity]);
                    if (f >= travelDistance && city != curCity) {
                        memo[f][curCity] += memo[f - travelDistance][city];
                    }
                }
            }
        }
        return memo[fuel][start];
    }
}
