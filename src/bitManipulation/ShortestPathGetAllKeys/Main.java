package bitManipulation.ShortestPathGetAllKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static void test1() {
        String[] grid = {"@.a..","###.#","b.A.B"};
        System.out.println(shortestPathAllKeys(grid));
    }
    private static void test2() {
        String[] grid = {"@..aA","..B#.","....b"};
        System.out.println(shortestPathAllKeys(grid));
    }
    private static void test3() {
        String[] grid = {"@Aa"};
        System.out.println(shortestPathAllKeys(grid));
    }
    private static void test4() {
        String[] grid = {"@...a",".###A","b.BCc"};
        System.out.println(shortestPathAllKeys(grid));
    }
    public static void main(String[] args) {
        test1(); // 8
        test2(); // 6
        test3(); // -1
        test4(); // 10
    }
    private static int n;
    private static int m;
    private static final HashMap<Character, Character> keyLock = getKeyLock();
    private static final HashMap<Character, Integer> keyState = getKeyState();
    private static final int CONSTANT = 100_000_0;
    private static final int[][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};

    public static int shortestPathAllKeys(String[] grid) {
        n = grid.length; m = grid[0].length();
        int n = grid.length;
        int m = grid[0].length();

        int[] startPoint = null;
        List<Character> keys = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = grid[i].charAt(j);
                if (keyLock.containsKey(ch)) {
                    keys.add(ch);
                }
                else if (ch == '@') startPoint = new int[]{i, j};
            }
        }

        int[][][] memo = new int[64][n][m];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        boolean[][] visited = new boolean[n][m];
        visited[startPoint[0]][startPoint[1]] = true;
        int answer = dfs(0, startPoint[0], startPoint[1], grid, (1 << keys.size()) - 1, visited);
        return answer == CONSTANT ? -1 : answer;
    }

    private static int dfs(int state, int i, int j, String[] grid, int targetState, boolean[][] visited) {
        if (state == targetState) return 0;

        int answer = CONSTANT;
        for (int[] delta : deltas) {
            int curI = i + delta[0];
            int curJ = j + delta[1];
            if (isDefined(curI, curJ) && !visited[curI][curJ]) {
                visited[curI][curJ] = true;
                char ch = grid[curI].charAt(curJ);
                if (Character.isLetter(ch)) {
                    if (Character.isLowerCase(ch)) {
                        int mask = 1 << keyState.get(ch);
                        answer = Math.min(answer, 1 + dfs(state | mask, curI, curJ, grid, targetState, visited));
                    } else {
                        boolean keyIsPresent = (state & (1 << keyState.get(Character.toLowerCase(ch)))) > 0;
                        if (keyIsPresent) {
                            answer = Math.min(answer, 1 + dfs(state, curI, curJ, grid, targetState, visited));
                        }
                    }
                } else if (grid[curI].charAt(curJ) == '.') {
                    answer = Math.min(answer, 1 + dfs(state, curI, curJ, grid, targetState, visited));
                }
                visited[curI][curJ] = false;
            }
        }

        return answer;
    }

    private static HashMap<Character, Integer> getKeyState() {
        HashMap<Character, Integer> keyMap = new HashMap<>();
        keyMap.put('a', 0);
        keyMap.put('b', 1);
        keyMap.put('c', 2);
        keyMap.put('d', 3);
        keyMap.put('e', 4);
        keyMap.put('f', 5);
        return keyMap;
    }

    private static HashMap<Character, Character> getKeyLock() {
        HashMap<Character, Character> keyMap = new HashMap<>();
        keyMap.put('a', 'A');
        keyMap.put('b', 'B');
        keyMap.put('c', 'C');
        keyMap.put('d', 'D');
        keyMap.put('e', 'E');
        keyMap.put('f', 'F');
        return keyMap;
    }

    private static boolean isDefined(int i, int j) {
        return i < n && i >= 0 && j < m && j >= 0;
    }
}
