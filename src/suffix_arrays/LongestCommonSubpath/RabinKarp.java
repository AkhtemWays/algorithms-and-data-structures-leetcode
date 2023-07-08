package suffix_arrays.LongestCommonSubpath;

import java.util.*;

public class RabinKarp {

    public static void main(String[] args) {
        int[][] paths = {{0,1,2,3,4},
                {2,3,4},
                {4,0,1,2,3}};
        int[][] paths2 = {{1,2,3,4},{4,1,2,3},{4}};
        int[][] paths3 = {{1,2,3,4},{1,2,3},{4}};
        int[][] paths4 = {{0,1,0,1,0,1,0,1,0},{0,1,3,0,1,4,0,1,0}};
        System.out.println(longestCommonSubpath(5, paths));
        System.out.println(longestCommonSubpath(5, paths2));
        System.out.println(longestCommonSubpath(5, paths3));
        System.out.println(longestCommonSubpath(5, paths4));
    }
    static class Meta {
        int start;
        int end;
        int pathIndex;
        Meta(int start, int end, int pathIndex) {
            this.start = start;
            this.end = end;
            this.pathIndex = pathIndex;
        }
        public boolean equals(Object obj) {
            if (obj instanceof Meta) {
                Meta pt = (Meta)obj;
                return (start == pt.start) && (end == pt.end) && (pathIndex == pt.pathIndex);
            }
            return super.equals(obj);
        }
    }

    public static int longestCommonSubpath(int n, int[][] paths) {
        int left = 0;
        int right = Arrays.stream(paths).min(Comparator.comparingInt(a -> a.length)).get().length;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (allHave(mid, n, paths)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left == -1 || right == -1) return 0;
        return allHave(left, n, paths) ? left : right;
    }

    private static boolean allHave(int k, int n, int[][] paths) {
        Map<Long, HashMap<Meta, Integer>> hashFrequency = new HashMap<>();
        int prime = 100_000_000_9;
        for (int c = 0; c < paths.length; c++) {
            int[] path = paths[c];
            for (int i = 0; i < path.length; i++) {
                if (i + k - 1 >= path.length) break;
                long hash = path[i];
                for (int j = i + 1; j <= i + k - 1; j++) {
                    hash = (hash * n) % prime;
                    hash = (hash + path[j]) % prime;
                }
                insertHash(hash, hashFrequency, i, i + k - 1, c, paths);
            }
        }
        for (HashMap<Meta, Integer> meta : hashFrequency.values()) {
            for (int freq : meta.values()) {
                if (freq >= paths.length) return true;
            }
        }
        return false;
    }

    private static void insertHash(long hash, Map<Long, HashMap<Meta, Integer>> hashFrequency, int start, int end, int pathIndex, int[][] paths) {
        if (hashFrequency.containsKey(hash)) {
            HashMap<Meta, Integer> accessPath = hashFrequency.get(hash);
            for (Meta meta : accessPath.keySet()) {
                int curPathIndex = meta.pathIndex;
                boolean found = true;
                for (int i = meta.start, j = start; i <= meta.end; i++, j++) {
                    if (paths[curPathIndex][i] != paths[pathIndex][j]) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    if (curPathIndex != pathIndex) {
                        accessPath.put(meta, accessPath.get(meta) + 1);
                    }
                    return;
                }
            }
            accessPath.put(new Meta(start, end, pathIndex), 1);
        } else {
            HashMap<Meta, Integer> metaFrequency = new HashMap<>();
            metaFrequency.put(new Meta(start, end, pathIndex), 1);
            hashFrequency.put(hash, metaFrequency);
        }
    }
}
