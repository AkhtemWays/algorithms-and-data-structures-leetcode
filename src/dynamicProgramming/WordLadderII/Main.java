package dynamicProgramming.WordLadderII;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static void test1() {
        System.out.println(findLadders("hit", "cog", List.of("hot","dot","dog","lot","log","cog")));
    }
    public static void main(String[] args) {
        test1();
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<Integer>[] graph = new List[wordList.size()+1];
        for (int i = 0; i < wordList.size(); i++) {
            graph[i] = new ArrayList<>();
            for (int j = 0; j < wordList.size(); j++) {
                if (getDistance(wordList.get(i), wordList.get(j)) == 1) {
                    graph[i].add(j);
                }
            }
        }

        graph[wordList.size()] = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (getDistance(wordList.get(i), beginWord) == 1) {
                graph[wordList.size()].add(i);
            }
        }

        int end = wordList.indexOf(endWord);
        return dfs(wordList.size(), end, graph, new HashMap<>(), new ArrayList<>(), new HashSet<>()).stream().map(l -> l.stream().map(wordList::get).collect(Collectors.toList())).collect(Collectors.toList());
    }

    private static List<List<Integer>> dfs(int i, int target, List<Integer>[] graph, HashMap<Integer,
                                            List<List<Integer>>> cache, List<Integer> cur, Set<Integer> visited) {
        if (i == target) return new ArrayList<>(List.of(new ArrayList<>(List.of(target))));

        if (cache.containsKey(i)) {
            List<List<Integer>> cached = cache.get(i);
            List<List<Integer>> answer = new ArrayList<>();
            for (int j = 0; j < cached.size(); j++) {
                List<Integer> res = new ArrayList<>(cur);
                res.addAll(cached.get(j));
                answer.add(res);
            }
            return answer;
        }

        int size = Integer.MAX_VALUE;
        List<List<Integer>> answer = new ArrayList<>();
        for (int neighbour : graph[i]) {
            if (!visited.contains(neighbour)) {
                cur.add(neighbour);
                visited.add(neighbour);
                List<List<Integer>> candidate = dfs(neighbour, target, graph, cache, cur, visited);
                visited.remove(neighbour);
                cur.remove(cur.size()-1);
                if (!candidate.isEmpty() && candidate.get(0).size() < size) {
                    answer = candidate;
                    size = candidate.get(0).size();
                } else if (!candidate.isEmpty() && candidate.get(0).size() == size) {
                    for (int j = 0; j < candidate.size(); j++) {
                        List<Integer> res = new ArrayList<>(cur);
                        res.addAll(candidate.get(j));
                        answer.add(res);
                    }
                }
            }
        }

        cache.put(i, answer);
        return answer;
    }

    private static int getDistance(String word1, String word2) {
        int c = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) c++;
        }
        return c;
    }
}
