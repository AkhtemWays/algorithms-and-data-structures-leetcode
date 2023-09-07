package dynamicProgramming.NumberofMusicPlaylists;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(numMusicPlaylists(3, 3, 1)); // 6
        System.out.println(numMusicPlaylists(2, 3, 0)); // 6
        System.out.println(numMusicPlaylists(2, 3, 1)); // 2
        System.out.println(numMusicPlaylists(1, 3, 0)); // 1
        System.out.println(numMusicPlaylists(2, 2, 0)); // 2
        System.out.println(numMusicPlaylists(2, 4, 1)); // 2
        System.out.println(numMusicPlaylists(16, 16, 4)); // 789741546
    }
    private static int N;
    private static final int MOD = 100_000_000_7;

    public static int numMusicPlaylists(int n, int goal, int k) {
        N = n;
        return (int) dfs(0, n, 0, goal, k, getMemo());
    }

    private static long dfs(int totalListened, int notListened, int listened, int goal, int k, long[][] memo) {
        if (totalListened == goal) {
            return notListened == 0 ? 1 : 0;
        }

        if (memo[totalListened][notListened] != -1) return memo[totalListened][notListened];

        if (k == 0) listened = N - notListened;
        else if (totalListened > k) listened++;

        long answer = 0;
        answer = answer + (notListened > 0 ? notListened * dfs(totalListened + 1, notListened - 1, listened, goal, k, memo) : 0) % MOD;
        answer = answer + (listened > 0 ? listened * dfs(totalListened + 1, notListened, listened-1, goal, k, memo) : 0) % MOD;

        return memo[totalListened][notListened] = answer;
    }

    private static long[][] getMemo() {
        int maxSize = 101;
        long[][] memo = new long[maxSize][maxSize];
        for (int i = 0; i < maxSize; i++) {
            Arrays.fill(memo[i], -1);
        }
        return memo;
    }
}
