package design.PrefixandSuffixSearch;

import java.util.*;

class Node {
    char val;
    //       size             hash             wordIndex    startIndex
    HashMap<Integer, HashMap<Integer, TreeMap<Integer, Integer>>> dictionary;
    Node[] children;
    Node(char val) {
        this.val = val;
        this.children = new Node[26];
        dictionary = new HashMap<>();
    }
}

public class WordFilter {

    private static void test1() {
        String[] words = {"apple"};
        WordFilter wordFilter = new WordFilter(words);
        System.out.println(wordFilter.f("a", "e")); // 0
    }

    private static void test2() {
        String[] words = {"abbba", "abba"};
        WordFilter wordFilter = new WordFilter(words);
        System.out.println(wordFilter.f("ab", "ba")); // 1
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
    private final Node root;
    private final HashMap<String, Integer> cache;
    private final int prime = 100_000_000_9;
    private final int base = 26;
    private final String[] words;

    public WordFilter(String[] words) {
        this.words = words;
        root = new Node('#');
        this.cache = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int[] hashes = getAllPossibleSuffixHashes(word);
            Node node = root;
            for (int k = 0; k < word.length(); k++) {
                char ch = word.charAt(k);
                bulkInsertHashes(k, hashes.length-1, hashes, node, i);
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Node(ch);
                }
                node = node.children[ch - 'a'];
            }
        }
    }

    private void bulkInsertHashes(int start, int end, int[] hashes, Node node, int wordIndex) {
        for (int i = start; i <= end; i++) {
            insertHash(hashes[i], node, end - i + 1, i, wordIndex);
        }
    }

    private int[] getAllPossibleSuffixHashes(String word) {
        int[] hashes = new int[word.length()];
        int n = word.length();
        int hash = getInitialHash(word);
        for (int i = 0; i < word.length(); i++) {
            hashes[i] = hash;
            char ch = word.charAt(i);
            hash = hash - ((int) Math.pow(base, n-1-i) * (ch - 'a' + 1) % prime);
        }
        return hashes;
    }

    public int f(String pref, String suff) {
        String key = pref + "_" + suff;
        if (cache.containsKey(key)) return cache.get(key);

        int suffixSize = suff.length();
        Node prefixNode = findPrefixNode(pref, 0, root);
        if (prefixNode == null) {
            cache.put(key, -1);
            return -1;
        }
        int suffixHash = getInitialHash(suff);
        if (!prefixNode.dictionary.containsKey(suffixSize) || !prefixNode.dictionary.get(suffixSize).containsKey(suffixHash)) {
            cache.put(key, -1);
            return -1;
        }
        TreeMap<Integer, Integer> indexMap = prefixNode.dictionary.get(suffixSize).get(suffixHash);
        for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()) {
            boolean found = true;
            int wordIndex = entry.getKey();
            int startIndex = entry.getValue();
            for (int i = 0; i < suff.length(); i++) {
                if (suff.charAt(i) != words[wordIndex].charAt(startIndex+i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                cache.put(key, wordIndex);
                return wordIndex;
            }
        }
        cache.put(key, -1);
        return -1;
    }

    private Node findPrefixNode(String pref, int i, Node node) {
        if (i == pref.length()) return node;
        char ch = pref.charAt(i);
        if (node.children[ch - 'a'] == null) return null;
        return findPrefixNode(pref, i+1, node.children[ch - 'a']);
    }

    private int getInitialHash(String word) {
        int hash = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            hash = hash + ((int) Math.pow(base, n-1-i) * (ch - 'a' + 1) % prime) % prime;
        }
        return hash;
    }

    private TreeMap<Integer, Integer> createIntervalManager(int index, int wordIndex) {
        TreeMap<Integer, Integer> intervalManager = new TreeMap<>((a, b) -> b - a);
        intervalManager.put(index, wordIndex);
        return intervalManager;
    }

    private void insertHash(int hash, Node node, int suffixSize, int startIndex, int wordIndex) {
        if (node.dictionary.containsKey(suffixSize)) {
            HashMap<Integer, TreeMap<Integer, Integer>> hashContainer = node.dictionary.get(suffixSize);
            if (hashContainer.containsKey(hash)) {
                hashContainer.get(hash).put(wordIndex, startIndex);
            } else {
                hashContainer.put(hash, createIntervalManager(wordIndex, startIndex));
            }
        } else {
            HashMap<Integer, TreeMap<Integer, Integer>> hashContainer = new HashMap<>();
            hashContainer.put(hash, createIntervalManager(wordIndex, startIndex));
            node.dictionary.put(suffixSize, hashContainer);
        }
    }
}
