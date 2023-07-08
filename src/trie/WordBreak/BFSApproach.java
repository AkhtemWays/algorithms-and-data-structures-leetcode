package trie.WordBreak;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSApproach {
    public static void main(String[] args) {
        String s = "cars";
        String s2 = "leetcode";
        String s3 = "aaaaaaa";
        String s4 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        BFSApproach main = new BFSApproach();
        System.out.println(main.wordBreak(s2, List.of("leet", "code")));
        System.out.println(main.wordBreak(s, List.of("car", "ca", "rs")));
        System.out.println(main.wordBreak(s3, List.of("aaaa", "aa")));
        System.out.println(main.wordBreak(s4, List.of("a", "aa")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boolean[] visited = new boolean[s.length()];
        while (!q.isEmpty()) {
            Integer startIndex = q.poll();
            if (visited[startIndex]) continue;
            for (String word : wordDict) {
                int endIndex = startIndex + word.length();
                if (endIndex <= s.length() && s.substring(startIndex, endIndex).contains(word)) {
                    q.add(startIndex + word.length());
                    if (endIndex == s.length()) return true;
                }
            }
            visited[startIndex] = true;
        }
        return false;
    }
}
