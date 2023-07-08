package dynamicProgramming.CounttheNumberofIdealArrays;

public class StarsAndBars {
    public static void main(String[] args) {
        System.out.println(idealArrays(4, 9)); // 73
        System.out.println(idealArrays(37, 71)); // 12789125 1140
        System.out.println(idealArrays(3, 9)); // 44
        System.out.println(idealArrays(5, 9)); // 111
        System.out.println(idealArrays(4, 6)); // 39
        System.out.println(idealArrays(5, 3)); // 11
        System.out.println(idealArrays(2, 5)); // 10
        System.out.println(idealArrays(184, 389)); // 510488787
        System.out.println(idealArrays(9767, 9557)); // 510488787
    }

    private static int[][] comb = new int[10001][14], cnt = new int[10001][14];
    private static int mod = 1000000007;

    public static int idealArrays(int n, int maxValue) {
        comb[0][0] = 1;
        if (comb[1][1] == 0) { // one-time computation.
            for (int s = 1; s <= 10000; ++s) // nCr (comb)
                for (int r = 0; r < 14; ++r)
                    comb[s][r] = r == 0 ? 1 : (comb[s - 1][r - 1] + comb[s - 1][r]) % mod;
            for (int div = 1; div <= 10000; ++div) { // Sieve of Eratosthenes
                ++cnt[div][0];
                for (int i = 2 * div; i <= 10000; i += div)
                    for (int bars = 0; bars < 13; ++bars)
                        cnt[i][bars + 1] += cnt[div][bars];
            }
        }
        int res = 0;

        for (int i = 1; i <= maxValue; ++i)
            for (int bars = 0; bars < Math.min(14, n); ++bars)
                res = (cnt[i][bars] * comb[n - 1][bars] + res) % mod;
        return res;
    }
}
