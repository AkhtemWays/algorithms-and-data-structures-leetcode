package backtracking.wordSearch2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        char[][] board =  {{'o','a','b','n'},
                           {'o','t','a','e'},
                           {'a','h','k','r'},
                           {'a','f','l','v'}};
        String[] words = {"oa","oaa"};
        System.out.println("dsa".substring(0, 0));
        System.out.println(findWords(board, words));
    }

    private static int n;
    private static int m;
    private static final int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static List<String> findWords(char[][] board, String[] words) {
        n = board.length;
        m = board[0].length;
        List<Integer>[] startPositionLetters = new List[26];
        for (int i = 0, k = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++, k++) {
                char ch = board[i][j];
                if (startPositionLetters[ch - 'a'] == null) {
                    startPositionLetters[ch - 'a'] = new ArrayList<>();
                }
                startPositionLetters[ch - 'a'].add(k);
            }
        }
        List<String> answer = new ArrayList<>();

        for (String word : words) {
            char ch = word.charAt(0);
            if (startPositionLetters[ch - 'a'] != null) {
                for (int pos : startPositionLetters[ch - 'a']) {
                    int i = pos / m;
                    int j = pos % m;
                    Set<Integer> visited = new HashSet<>(List.of(pos));
                    if (backtrack(i, j, word, 1, visited, board)) {
                        answer.add(word);
                        break;
                    }
                }
            }
        }

        return answer;
    }

    private static boolean backtrack(int i, int j, String word, int k, Set<Integer> visited, char[][] board) {
        if (k == word.length()) return true;

        for (int[] delta : deltas) {
            int curI = i + delta[0];
            int curJ = j + delta[1];
            int pos = m * curI + curJ;
            if (isDefinedAt(curI, curJ) && board[curI][curJ] == word.charAt(k) && !visited.contains(pos)) {
                visited.add(pos);
                if (backtrack(curI, curJ, word, k+1, visited, board)) return true;
                visited.remove(pos);
            }
        }
        return false;
    }

    private static boolean isDefinedAt(int i, int j) {
        return i < n && i >= 0 && j < m && j >= 0;
    }
}
