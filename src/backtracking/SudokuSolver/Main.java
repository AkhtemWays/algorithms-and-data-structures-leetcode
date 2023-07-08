package backtracking.SudokuSolver;


import java.util.Arrays;

public class Main {
    private static void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                          {'6','.','.','1','9','5','.','.','.'},
                          {'.','9','8','.','.','.','.','6','.'},
                          {'8','.','.','.','6','.','.','.','3'},
                          {'4','.','.','8','.','3','.','.','1'},
                          {'7','.','.','.','2','.','.','.','6'},
                          {'.','6','.','.','.','.','2','8','.'},
                          {'.','.','.','4','1','9','.','.','5'},
                          {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board);
        print(board);

    }
    private static int n;
    private static final int quadrantLength = 3;
    private static final char[] values = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static void solveSudoku(char[][] board) {
        n = board.length;
        fillQuadrants(0, 0, board);
    }

    private static boolean fillQuadrants(int i, int j, char[][] board) {
        if (i == n) return true;
        if (j == n) return fillQuadrants(i+1, 0, board);

        if (board[i][j] != '.') return fillQuadrants(i, j+1, board);

        for (char candidate : values) {
            if (canPlaceCandidate(candidate, i, j, board)) {
                board[i][j] = candidate;
                if (fillQuadrants(i, j+1, board)) return true;
                board[i][j] = '.';
            }
        }
        return false;
    }

    private static boolean canPlaceCandidate(char candidate, int i, int j, char[][] board) {
        int startRow = (i / quadrantLength) * quadrantLength;
        int startCol = (j / quadrantLength) * quadrantLength;
        for (int k = 0; k < n; k++) {
            if (board[i][k] == candidate || board[k][j] == candidate || board[startRow + (k / quadrantLength)][startCol + (k % quadrantLength)] == candidate) return false;
        }
        return true;
    }
}
