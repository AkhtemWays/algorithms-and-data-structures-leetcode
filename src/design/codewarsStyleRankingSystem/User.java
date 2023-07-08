package design.codewarsStyleRankingSystem;

import java.util.Arrays;

public class User {
    private int[] rankTable = new int[] { -8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8 };
    public int rank = -8;
    public int progress;

    public int incProgress(int rank) {
        if (Arrays.binarySearch(rankTable, rank) < 0) {
            int exc = rankTable[Arrays.binarySearch(rankTable, rank)];// on purpose raise exception;
        }
        int point = calcPoint(rank);

        int rankPlus = calcRankPlus(point);

        //this.progress = progress % 100;
        this.rank = getRank(getRankIndex(this.rank) + rankPlus);
        if(this.rank != rankTable[rankTable.length - 1]){
            this.progress += point - (rankPlus * 100);
        }
        else{
            this.progress = 0;
        }

        return this.rank;
    }

    private int getRank(int rankIndex) {
        if(rankIndex > rankTable.length - 1){
            return rankTable[rankTable.length - 1];
        }
        return rankTable[rankIndex];
    }

    private int calcRankPlus(int point) {
        int remain = this.progress % 100;

        return (remain + point) / 100;
    }

    public int calcPoint(int actRank){
        int userIndex = getRankIndex(this.rank);
        int actIndex = getRankIndex(actRank);
        int diff = actIndex - userIndex;
        int point = 0;
        if(diff < -1){
            point = 0;
        }else if(diff == -1){
            point = 1;
        }else if(diff == 0){
            point = 3;
        }else{
            point = 10 * diff * diff;
        }

        return point;
    }

    private int getRankIndex(int rank) {
        return Arrays.binarySearch(rankTable, rank);
    }


}
