package suffix_arrays.LongestCommonSubpath;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int[][] paths = {{0,1,2,3,4},
                         {2,3,4},
                         {4,0,1,2,3}};
        int[][] paths2 = {{1,2,3,4},{4,1,2,3},{4}};
        int[][] paths3 = {{1,2,3,4},{1,2,3},{4}};
        System.out.println(longestCommonSubpath(5, paths));
        System.out.println(longestCommonSubpath(5, paths2));
        System.out.println(longestCommonSubpath(5, paths3));
    }

    public static int longestCommonSubpath(int n, int[][] paths) {
        int fullLength = getFullLengthOfSuffixArray(paths);
        int[] allPaths = new int[fullLength];
        for (int i = 0, k = 0, sentinel = -paths.length; i < paths.length; i++) {
            for (int j = 0; j < paths[i].length; j++, k++) {
                allPaths[k] = paths[i][j];
            }
            allPaths[k++] = sentinel++;
        }
        int[][] suffixArray = new int[fullLength][2];
        for (int i = 0; i < suffixArray.length; i++) {
            suffixArray[i] = new int[]{i, fullLength-1};
        }
        int[][] substrings = Arrays.copyOf(suffixArray, suffixArray.length);
        Arrays.sort(suffixArray, (a, b) -> compare(a, b, allPaths));
        int[] lcpArray = kasai(suffixArray, substrings, allPaths);
        int[] colors = new int[lcpArray.length];
        for (int i = 0; i < suffixArray.length; i++) {
            int k = suffixArray[i][0];
            while (allPaths[k] >= 0) {
                k++;
            }
            colors[i] = Math.abs(allPaths[k]) - 1;
        }
        HashMap<Integer, Integer> slidingWindow = new HashMap<>();
        int answer = 0;
        Deque<Integer> q = new LinkedList<>();
        for (int right = paths.length; right < lcpArray.length; right++) {
            if (q.isEmpty() || lcpArray[q.peekLast()] <= lcpArray[right]) {
                q.addLast(right);
                slidingWindow.put(colors[right], slidingWindow.getOrDefault(colors[right], 0) + 1);
            } else {
                while (!q.isEmpty() && (lcpArray[q.peekLast()] == 0 || lcpArray[q.peekLast()] > lcpArray[right])) {
                    if (slidingWindow.size() == paths.length) {
                        answer = Math.max(answer, getMin(q, lcpArray, paths.length));
                    }
                    int index = q.pollLast();
                    slidingWindow.put(colors[index], slidingWindow.get(colors[index]) - 1);
                    if (slidingWindow.getOrDefault(colors[index], 0) == 0) {
                        slidingWindow.remove(colors[index]);
                    }
                }
                q.addLast(right);
                slidingWindow.put(colors[right], slidingWindow.getOrDefault(colors[right], 0) + 1);
            }
        }

        return Math.max(answer, getMin(q, lcpArray, paths.length));
    }

    private static int getMin(Deque<Integer> q, int[] lcpArray, int k) {
        if (q.size() < k) return 0;
        int zero = q.pollFirst();
        int result = q.peekFirst();
        q.addFirst(zero);
        return lcpArray[result];
    }

    private static int[] kasai(int[][] suffixArray, int[][] substrings, int[] allPaths) {

        HashMap<int[], Integer> sortedPositions = new HashMap<>();
        for (int index = 0; index < suffixArray.length; index++) {
            sortedPositions.put(suffixArray[index], index);
        }

        int[] lcpArray = new int[suffixArray.length];
        for (int i = 0, k = 0; i < suffixArray.length; i++) {
            int[] cur = substrings[i];
            int l = cur[0] + k;
            int sortedPosition = sortedPositions.get(cur);
            if (sortedPosition - 1 >= 0) {
                int[] prev = suffixArray[sortedPosition-1];
                int m = prev[0] + k;
                while (allPaths[l] == allPaths[m]) {
                    k++;
                    l++;
                    m++;
                }
                lcpArray[sortedPosition] = k;
                k = Math.max(0, k-1);
            }
        }
        return lcpArray;
    }

    private static int compare(int[] prefix1, int[] prefix2, int[] allPaths) {
        for (int i = prefix1[0], j = prefix2[0]; i <= prefix1[1] && j <= prefix2[1]; i++, j++) {
            if (allPaths[i] > allPaths[j]) return 1;
            else if (allPaths[i] < allPaths[j]) return -1;
        }
        return prefix1[0] > prefix2[1] ? -1 : 1;
    }

    private static int getFullLengthOfSuffixArray(int[][] paths) {
        int res = 0;
        for (int[] path : paths) {
            res += path.length + 1;
        }
        return res;
    }
}
