package graphs.Minesweeper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
    public static void main(String[] args) {
        char[][] board = {
                {'E','E','E','E','E'},
                {'E','E','M','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'}};
        int[] click = {3, 0};
        char[][] board2 = {
                {'B','1','E','1','B'},
                {'B','1','M','1','B'},
                {'B','1','1','1','B'},
                {'B','B','B','B','B'}};
        int[] click2 = {1, 2};
        printBoard(updateBoard(board, click));
//        printBoard(updateBoard(board2, click2));

//        [
//        ["B","1","E","1","B"],
//        ["B","1","M","1","B"],
//        ["B","1","1","1","B"],
//        ["B","B","B","B","B"]]
    }

    public static char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        int[][] adjacentPositions = {{-1, 0}, {-1, -1}, {-1, 1}, {0, 1}, {0, -1}, {1, 1}, {1, 0}, {1, -1}};
        Queue<int[]> q = new LinkedList<>();
        visited[click[0]][click[1]] = true;
        q.add(click);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] position = q.poll();
                int x = position[0];
                int y = position[1];
                int numAdjacentMines = checkAndAdd(q, position, visited, board);
                if (numAdjacentMines == 0) {
                    board[x][y] = 'B';
                    Arrays.stream(adjacentPositions).forEach(pos -> {
                        int curX = x - pos[0];
                        int curY = y - pos[1];
                        if (isDefinedAt(curX, curY, board) && !visited[curX][curY] && !Character.isDigit(board[curX][curY])) {
                            visited[curX][curY] = true;
                            q.add(new int[]{curX, curY});
                        }
                    });
                } else if (!Character.isDigit(board[x][y])) {
                    board[x][y] = (char) (48 + numAdjacentMines);
                }
            }
        }
        return board;
    }

    private static int checkAndAdd(Queue<int[]> q, int[] position, boolean[][] visited, char[][] board) {
        int[][] adjacentPositions = {{-1, 0}, {-1, -1}, {-1, 1}, {0, 1}, {0, -1}, {1, 1}, {1, 0}, {1, -1}};
        AtomicInteger count = new AtomicInteger();
        Arrays.stream(adjacentPositions).forEach(pos -> {
            int x = position[0] - pos[0];
            int y = position[1] - pos[1];
            if (isDefinedAt(x, y, board)) {
                if (board[x][y] == 'M') {
                    count.getAndIncrement();
                }
            }
        });
        return count.get();
    }

    private static boolean isDefinedAt(int x, int y, char[][] board) {
        int n = board.length;
        int m = board[0].length;
        return x < n && x >= 0 && y < m && y >= 0;
    }
}
