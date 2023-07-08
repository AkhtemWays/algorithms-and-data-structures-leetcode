package bitManipulation.MaximumStudentsTakingExam;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }

    private static int n;
    private static int m;
    public int maxStudents(char[][] seats) {
        n = seats.length;
        m = seats[0].length;
        HashMap<Integer, Integer> cache = new HashMap<>();
        return dfs(0, 0, 0, seats, cache);
    }

    private static int dfs(int pos, int prevRow, int curRow, char[][] seats, HashMap<Integer, Integer> cache) {
        if (pos == n * m) return 0;

        int i = pos / m;
        int j = pos % m;

        if (j == 0) {
            prevRow = curRow;
            curRow = 0;
        }

        int key = curRow | (i << 8) | (prevRow << 12);
        if (cache.containsKey(key)) return cache.get(key);

        int skipped = dfs(pos+1, prevRow, curRow, seats, cache);

        if (seats[i][j] == '.') {
            boolean tl = true, l = true, tr = true;

            if (j != 0) {
                if ((curRow & (1 << (j-1))) != 0) {
                    l = false;
                }
                if (((prevRow & (1 << (j-1))) != 0)) {
                    tl = false;
                }
            }
            if (j != m-1) {
                if ((((prevRow) & (1 << (j+1))) != 0)) {
                    tr = false;
                }
            }

            if (tl && tr && l) {
                int added = 1 + dfs(pos+1, prevRow, curRow | (1 << j), seats, cache);
                int result = Math.max(added, skipped);
                cache.put(key, result);
                return result;
            }
        }

        cache.put(key, skipped);
        return skipped;
    }
}
