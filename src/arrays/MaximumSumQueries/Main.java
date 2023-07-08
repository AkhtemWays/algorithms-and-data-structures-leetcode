package arrays.MaximumSumQueries;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static void test1() {
        int[] nums1 = {4,3,1,2};
        int[] nums2 = {2,4,9,5};
        int[][] queries = {{4,1},{1,3},{2,5}};
        System.out.println(Arrays.toString(maximumSumQueries(nums1, nums2, queries)));
    }
    private static void test2() {
        int[] nums1 = {3,2,5};
        int[] nums2 = {2,3,4};
        int[][] queries = {{4,4},{3,2},{1,1}};
        System.out.println(Arrays.toString(maximumSumQueries(nums1, nums2, queries)));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int q = queries.length;
        int[][] pairs = new int[n][2];
        int[][] indexedQueries = new int[q][3];
        int[] answer = new int[q];
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{nums1[i], nums2[i]};
        }

        for (int i = 0; i < q; i++) {
            indexedQueries[i] = new int[]{queries[i][0], queries[i][1], i};
        }

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(indexedQueries, (a, b) -> b[0] - a[0]);

        int j = n-1;
        TreeMap<Integer, Integer> prev = new TreeMap<>((a, b) -> b - a);
        for (int i = 0; i < q; i++) {
            int[] query = indexedQueries[i];
            int sum = query[0] + query[1];
            int idx = query[2];
            while (j >= 0 && pairs[j][0] >= query[0]) {
                prev.put(pairs[j][0] + pairs[j][1], pairs[j][0]);
                j--;
            }
            for (Map.Entry<Integer, Integer> entry : prev.entrySet()) {
                if (sum > entry.getKey()) {
                    answer[idx] = -1;
                    break;
                } else if (entry.getKey() - entry.getValue() >= query[1]) {
                    answer[idx] = entry.getKey();
                    break;
                }
            }
            if (answer[idx] == 0) answer[idx] = -1;
        }
        return answer;
    }
}
