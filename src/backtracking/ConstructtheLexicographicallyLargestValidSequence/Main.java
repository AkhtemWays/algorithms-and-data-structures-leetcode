package backtracking.ConstructtheLexicographicallyLargestValidSequence;

import java.util.Arrays;

public class Main {
    private static int[] answer;
    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructDistancedSequence(3)));
        System.out.println(Arrays.toString(constructDistancedSequence(5)));
        System.out.println(Arrays.toString(constructDistancedSequence(10)));
        System.out.println(Arrays.toString(constructDistancedSequence(13)));
        System.out.println(Arrays.toString(constructDistancedSequence(20)));
    }

    public static int[] constructDistancedSequence(int n) {
        answer = new int[2*n-1];
        dfs(0, new int[2*n-1], new int[n+1]);
        return answer;
    }

    private static void dfs(int i, int[] current, int[] counts) {
        if (i >= current.length) {
            answer = Arrays.copyOf(current, current.length);
            return;
        }
        if (current[i] > 0) {
            dfs(i+1, current, counts);
            return;
        }

        for (int num = counts.length-1; num >= 2; num--) {
            if (canPlace(num, current, i, counts)) {
                counts[num]++;
                current[i] = num;
                current[i+num] = num;
                if (isLexicographicallyOptimal(current)) {
                    dfs(i+1, current, counts);
                }
                counts[num]--;
                current[i] = 0;
                current[i+num] = 0;
            }
        }

        if (counts[1] < 1) {
            counts[1]++;
            current[i] = 1;
            dfs(i+1, current, counts);
            counts[1]--;
            current[i] = 0;
        }
    }

    private static boolean canPlace(int num, int[] current, int i, int[] counts) {
        return counts[num] == 0 && i + num < current.length && current[i+num] == 0;
    }

    private static boolean isLexicographicallyOptimal(int[] current) {
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] < current[i]) return true;
            if (answer[i] > current[i]) return false;
        }
        return false;
    }
}
