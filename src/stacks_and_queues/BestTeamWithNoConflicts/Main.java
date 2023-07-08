package stacks_and_queues.BestTeamWithNoConflicts;

import java.util.Arrays;
import java.util.HashMap;

class Player {
    int score;
    int age;
    Player(int score, int age) {
        this.score = score;
        this.age = age;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] scores = {4,5,6,5};
        int[] ages = {2,1,2,1};
        int[] scores2 = {596,277,897,622,500,299,34,536,797,32,264,948,645,537,83,589,770};
        int[] ages2 = {18,52,60,79,72,28,81,33,96,15,18,5,17,96,57,72,72};
        int[] scores3 = {729,419,573,20,84,532,108,646,199,320,892,899,810,649,997,572,387,667};
        int[] ages3 = {14,15,4,70,27,27,71,35,93,61,26,58,1,88,2,86,95,43};
        System.out.println(bestTeamScore(scores, ages)); // 16
        System.out.println(bestTeamScore(scores2, ages2)); // 3287
        System.out.println(bestTeamScore(scores3, ages3)); // 3163
    }

    private static int findMaxScore(int[][] ageScorePair) {
        int[] dp = new int[ageScorePair.length];

        int answer = 0;
        for (int i = 0; i < ageScorePair.length; i++) {
            dp[i] = ageScorePair[i][1];
            answer = Math.max(answer, dp[i]);
        }

        for (int i = 0; i < ageScorePair.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (ageScorePair[i][1] >= ageScorePair[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + ageScorePair[i][1]);
                }
                answer = Math.max(answer, dp[i]);
            }
        }

        return answer;
    }

    public static int bestTeamScore(int[] scores, int[] ages) {
        int N = ages.length;
        int[][] ageScorePair = new int[N][2];

        for (int i = 0; i < N; i++) {
            ageScorePair[i][0] = ages[i];
            ageScorePair[i][1] = scores[i];
        }

        // Sort in ascending order of age and then by score.
        Arrays.sort(ageScorePair, (a,b) -> a[0] == b[0] ? a[1]-b[1] : a[0]-b[0]);
        return findMaxScore(ageScorePair);
    }
}
