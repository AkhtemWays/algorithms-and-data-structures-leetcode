package hashmap.RelativeRanks;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }

    public static String[] findRelativeRanks(int[] score) {
        int[] copyScore = Arrays.copyOf(score, score.length);
        Arrays.sort(copyScore);
        HashMap<Integer, Integer> scoreToRank = new HashMap<>();
        for (int i = 0; i < copyScore.length; i++) {
            scoreToRank.put(copyScore[i], copyScore.length-i);
        }
        String[] res = new String[score.length];
        for (int i = 0; i < score.length; i++) {
            int rank = scoreToRank.get(score[i]);
            res[i] = getRank(rank);
        }
        return res;
    }

    private static String getRank(int rank) {
        if (rank == 1) return "Gold Medal";
        else if (rank == 2) return "Silver Medal";
        else if (rank == 3) return "Bronze Medal";
        return "" + rank;
    }
}
