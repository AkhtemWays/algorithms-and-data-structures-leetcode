package backtracking.synonyms;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
        List<String> first = List.of("happy","joy");
        List<String> second = List.of("sad","sorrow");
        List<String> third = List.of("joy","cheerful");
        input.add(first);
        input.add(second);
        input.add(third);
        Main main = new Main();
        System.out.println(main.generateSentences(input, "I am happy today but was sad yesterday"));
    }

    private HashMap<String, Integer> wordIndices;
    private HashMap<Integer, String> indexToWord;
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        wordIndices = new HashMap<>();
        indexToWord = new HashMap<>();
        int idx = 0;
        for (List<String> synonym : synonyms) {
            if (!wordIndices.containsKey(synonym.get(0))) {
                wordIndices.put(synonym.get(0), idx);
                indexToWord.put(idx, synonym.get(0));
                idx++;
            }
            if (!wordIndices.containsKey(synonym.get(1))) {
                wordIndices.put(synonym.get(1), idx);
                indexToWord.put(idx, synonym.get(1));
                idx++;
            }
        }

        int n = wordIndices.size();
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (List<String> synonym : synonyms) {
            String word1 = synonym.get(0);
            String word2 = synonym.get(1);
            int idx1 = wordIndices.get(word1);
            int idx2 = wordIndices.get(word2);
            adj[idx1].add(idx2);
            adj[idx2].add(idx1);
        }

        String[] sentence = text.split(" ");
        List<String> answer = new ArrayList<>();
        dfs(0, sentence, adj, answer);
        Collections.sort(answer);
        return answer;
    }

    private void dfs(int i, String[] sentence, List<Integer>[] adj, List<String> answer) {
        if (i == sentence.length) {
            answer.add(String.join(" ", sentence));
            return;
        }
        String word = sentence[i];
        if (wordIndices.containsKey(word)) {
            Set<String> synonyms = getAllSynonyms(word, adj);
            for (String synonym : synonyms) {
                sentence[i] = synonym;
                dfs(i+1, sentence, adj, answer);
            }
            sentence[i] = word;
        } else {
            dfs(i+1, sentence, adj, answer);
        }
    }

    private Set<String> getAllSynonyms(String word, List<Integer>[] adj) {
        Set<Integer> result = new HashSet<>();
        int node = wordIndices.get(word);
        result.add(node);
        findSynonyms(node, adj, result);
        Set<String> answer = new HashSet<>();
        for (int n : result) {
            answer.add(indexToWord.get(n));
        }
        return answer;
    }

    private void findSynonyms(int node, List<Integer>[] adj, Set<Integer> result) {
        for (int neighbour : adj[node]) {
            if (!result.contains(neighbour)) {
                result.add(neighbour);
                findSynonyms(neighbour, adj, result);
            }
        }
    }
}

