package arrays.nQueens;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println(solveNQueens(4).size());
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> combinations = new ArrayList<>();
        dfs(0, 0, combinations, constructBoard(n), n);
        return combinations;
    }

    private static List<String> constructBoard(int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < n; j++) {
                line.append('.');
            }
            board.add(line.toString());
        }
        return board;
    }

    private static void dfs(int i, int j, List<List<String>> combinations, List<String> board, int count) {
        if (count == 0) {
            combinations.add(List.copyOf(board));
            return;
        }
        if (i >= board.size()) return;
        if (j >= board.get(i).length()) {
            dfs(i+1, 0, combinations, board, count);
            return;
        }
        if (canBePlaced(board, i, j)) {
            placeQueenAt(board, i, j);
            dfs(i, j+1, combinations, board, count-1);
            removeQueenAt(board, i, j);
        }
        dfs(i, j+1, combinations, board, count);
    }

    private static boolean canBePlaced(List<String> board, int i, int j) {
        return verticalIsOk(board, j) && horizontalIsOk(board, i) && diagonalIsOk(board, i, j);
    }

    private static boolean verticalIsOk(List<String> board, int j) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).charAt(j) == 'Q') return false;
        }
        return true;
    }

    private static boolean horizontalIsOk(List<String> board, int i) {
        for (int j = 0; j < board.get(i).length(); j++) {
            if (board.get(i).charAt(j) == 'Q') return false;
        }
        return true;
    }

    private static boolean diagonalIsOk(List<String> board, int i, int j) {
        if (j > i) {
            for (int k = 0, l = j - i; k < board.size() && l < board.get(k).length(); k++, l++) {
                if (board.get(k).charAt(l) == 'Q') return false;
            }
            while (j < board.get(i).length() - 1 && i > 0) {
                j++;
                i--;
            }
            for (int k = i, l = j; l >= 0 && k < board.size(); l--, k++) {
                if (board.get(k).charAt(l) == 'Q') return false;
            }
        } else {
            for (int k = i - j, l = 0; k < board.size() && l < board.get(k).length(); k++, l++) {
                if (board.get(k).charAt(l) == 'Q') return false;
            }
            while (i < board.size() - 1 && j > 0) {
                i++;
                j--;
            }
            for (int k = i, l = j; k >= 0 && l < board.get(0).length(); k--, l++) {
                if (board.get(k).charAt(l) == 'Q') return false;
            }
        }
        return true;
    }

    private static void placeQueenAt(List<String> board, int i, int j) {
        String cur = board.get(i);
        String newCur = cur.substring(0, j) + 'Q' + cur.substring(j+1);
        board.set(i, newCur);
    }

    private static void removeQueenAt(List<String> board, int i, int j) {
        String cur = board.get(i);
        String newCur = cur.substring(0, j) + '.' + cur.substring(j+1);
        board.set(i, newCur);
    }
}
