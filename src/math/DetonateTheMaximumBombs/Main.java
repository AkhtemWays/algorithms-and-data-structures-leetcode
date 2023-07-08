package math.DetonateTheMaximumBombs;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] bombs1 = {{2,1,3},{6,1,4}};
        int[][] bombs2 = {{1,1,5},{10,10,5}};
        int[][] bombs3 = {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        int[][] bombs4 = {{656,619,56},{189,402,178},{513,373,276},{900,510,14},{188,173,129},{512,178,251},{145,685,47},{504,355,500},{554,131,214},{596,1,98},{358,230,197},{88,758,155},{72,340,419},{818,708,222}};
        System.out.println(main.maximumDetonation(bombs1));
        System.out.println(main.maximumDetonation(bombs2));
        System.out.println(main.maximumDetonation(bombs3));
        System.out.println(main.maximumDetonation(bombs4));
    }

    public int maximumDetonation(int[][] bombs) {
        int totalBombs = bombs.length;
        int answer = 0;

        HashSet<int[]> restOfTheBombs = new HashSet<>(Arrays.asList(bombs));
        for (int i = 0; i < bombs.length; i++) {
            int[] detonatedBomb = bombs[i];
            Queue<int[]> q = new LinkedList<>();
            q.add(detonatedBomb);
            restOfTheBombs.remove(detonatedBomb);
            answer = Math.max(answer, bfs(q, totalBombs, restOfTheBombs));
            restOfTheBombs.add(detonatedBomb);
        }
        return answer;
    }

    private int bfs(Queue<int[]> q, int totalBombs, Set<int[]> restOfTheBombs) {
        while (!q.isEmpty()) {
            int[] detonatedBomb = q.poll();
            HashSet<int[]> newRestOfTheBombs = new HashSet<>();
            for (int[] bomb : restOfTheBombs) {
                double distance = getDistance(detonatedBomb, bomb);
                if (distance <= detonatedBomb[2]) {
                    q.add(bomb);
                } else {
                    newRestOfTheBombs.add(bomb);
                }
            }
            restOfTheBombs = newRestOfTheBombs;
        }

        return totalBombs - restOfTheBombs.size();
    }

    private double getDistance(int[] bomb1, int[] bomb2) {
        double xDistanceSquared = Math.pow(Math.abs(bomb1[0] - bomb2[0]), 2);
        double yDistanceSquared = Math.pow(Math.abs(bomb1[1] - bomb2[1]), 2);
        return Math.sqrt(xDistanceSquared + yDistanceSquared);
    }
}
