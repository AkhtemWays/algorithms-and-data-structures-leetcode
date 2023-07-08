package disjointSets_UnionFind.SurroundedRegions;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
//        [['X','X','X','X'],['X','X','X','X'],['X','X','X','X'],['X','O','X','X']]
        solve(board);
        System.out.println(board);
    }

    public static void solve(char[][] board) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O' && isNotSurrounded(board, i, j)) points.add(new Point(i, j));
            }
        }
        for (Point point : points) {
            board[point.x][point.y] = 'X';
        }
    }

    public static boolean isNotSurrounded(char[][] board, int i, int j) {
        if (i - 1 >= 0 && board[i-1][j] == 'O') return true;
        if (i + 1 < board.length && board[i+1][j] == 'O') return true;
        if (j - 1 >= 0 && board[i][j-1] == 'O') return true;
        if (j + 1 < board[i].length && board[i][j+1] == 'O') return true;
        return false;
    }
}
