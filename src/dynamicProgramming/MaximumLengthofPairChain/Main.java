package dynamicProgramming.MaximumLengthofPairChain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static void test1() {
        int[][] pairs = {{1,2},{2,3},{3,4}};
        System.out.println(findLongestChain(pairs));
    }
    public static void main(String[] args) {
        test1();
    }

    public static int findLongestChain(int[][] pairs) {
        HashMap<Integer, Integer> uniques = new HashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            uniques.put(pairs[i][0], Math.min(pairs[i][1], uniques.getOrDefault(pairs[i][0], Integer.MAX_VALUE)));
        }

        int[][] uniquePairs = new int[uniques.size()][2];
        int j = 0;
        for (Map.Entry<Integer, Integer> entry : uniques.entrySet()) {
            uniquePairs[j][0] = entry.getKey();
            uniquePairs[j][1] = entry.getValue();
            j++;
        }

        Arrays.sort(uniquePairs, (a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);

        TreeMap<Integer, Integer> visited = new TreeMap<>((a, b) -> a - b);

        int answer = 0;
        for (int i = uniquePairs.length-1; i >= 0; i--) {
            Map.Entry<Integer, Integer> bestScore = visited.higherEntry(uniquePairs[i][1]);
            if (bestScore == null) {
                visited.put(uniquePairs[i][0], 1);
            } else {
                visited.put(uniquePairs[i][0], bestScore.getValue() + 1);
            }

            answer = Math.max(answer, visited.get(uniquePairs[i][0]));
        }

        return answer;
    }
}
