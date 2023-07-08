package arrays.ValidSudoku;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'}
                         ,{'6','.','.','1','9','5','.','.','.'}
                         ,{'.','9','8','.','.','.','.','6','.'}
                         ,{'8','.','.','.','6','.','.','.','3'}
                         ,{'4','.','.','8','.','3','.','.','1'}
                         ,{'7','.','.','.','2','.','.','.','6'}
                         ,{'.','6','.','.','.','.','2','8','.'}
                         ,{'.','.','.','4','1','9','.','.','5'}
                         ,{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        return rule1(board) && rule2(board) && rule3(board);
    }

    private static boolean rule1(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Set<Character> nums = new HashSet<>();
            for (char num : board[i]) {
                if (num == '.') continue;
                if (nums.contains(num)) return false;
                nums.add(num);
            }
        }
        return true;
    }

    private static boolean rule2(char[][] board) {
        for (int j = 0; j < board[0].length; j++) {
            Set<Character> nums = new HashSet<>();
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == '.') continue;
                if (nums.contains(board[i][j])) return false;
                nums.add(board[i][j]);
            }
        }
        return true;
    }

    private static boolean rule3(char[][] board) {
        for (int i = 0; i < board.length; i+=3) {
            for (int j = 0; j < board[i].length; j+=3) {
                if (!isCorrectSquare(board, i, j)) return false;
            }
        }
        return true;
    }

    private static boolean isCorrectSquare(char[][] board, int startRow, int startCol) {
        Set<Character> nums = new HashSet<>();
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == '.') continue;
                if (nums.contains(board[i][j])) return false;
                nums.add(board[i][j]);
            }
        }
        return true;
    }
}
