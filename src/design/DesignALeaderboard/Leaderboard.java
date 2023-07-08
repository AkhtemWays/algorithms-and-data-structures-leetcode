package design.DesignALeaderboard;

import java.util.*;

class Leaderboard {

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard ();
        leaderboard.addScore(1,13);
        leaderboard.addScore(2,93);
        leaderboard.addScore(3,84);
        leaderboard.addScore(4,6);
        leaderboard.addScore(5,89);
        leaderboard.addScore(6,31);
        leaderboard.addScore(7,7);
        leaderboard.addScore(8,1);
        leaderboard.addScore(9,98);
        leaderboard.addScore(10,42);
        System.out.println(leaderboard.top(5));
        leaderboard.reset(1);
        leaderboard.reset(2);
        leaderboard.addScore(3,76);
        leaderboard.addScore(4,68);
        System.out.println(leaderboard.top(1)); // 160
        leaderboard.reset(3);
        leaderboard.reset(4);
        leaderboard.addScore(2,70);
        leaderboard.reset(2);
    }

    TreeMap<Integer, Set<Integer>> leaderboard;
    HashMap<Integer, Integer> playerScores;
    public Leaderboard() {
        this.leaderboard = new TreeMap<>(Collections.reverseOrder());
        this.playerScores = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        if (playerScores.containsKey(playerId)) {
            int prevScore = playerScores.get(playerId);
            int newScore = prevScore + score;
            playerScores.put(playerId, newScore);
            leaderboard.get(prevScore).remove(playerId);
            leaderboard.computeIfAbsent(newScore, (k) -> new HashSet<>()).add(playerId);
        } else {
            leaderboard.computeIfAbsent(score, (k) -> new HashSet<>()).add(playerId);
            playerScores.put(playerId, score);
        }
    }

    public int top(int k) {
        int sum = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : leaderboard.entrySet()) {
            sum += Math.min(k, entry.getValue().size()) * entry.getKey();
            k -= entry.getValue().size();
            if (k <= 0) return sum;
        }
        return sum;
    }

    public void reset(int playerId) {
        int playerScore = playerScores.remove(playerId);
        Set<Integer> players = leaderboard.get(playerScore);
        if (players.size() <= 1) {
            leaderboard.remove(playerScore);
        } else {
            players.remove(playerId);
        }
    }
}
