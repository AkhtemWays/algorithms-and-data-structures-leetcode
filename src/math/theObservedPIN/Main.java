package math.theObservedPIN;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Integer> expected = new HashSet<>(List.of(236, 238, 239, 256, 258, 259, 266, 268, 269, 296, 298, 299, 336, 338, 339, 356, 358, 359, 366, 368, 369, 396, 398, 399, 636, 638, 639, 656, 658, 659, 666, 668, 669, 696, 698, 699));
        List<String> res = getPINs("369");

        Set<String> actual = new HashSet<>(res);
        System.out.println(actual.size() == expected.size());
    }

    public static List<String> getPINs(String observed) {
        Map<Character, List<String>> adjacency = getAdjacency();
        List<String> res = new ArrayList<>();

        backtrack(0, res, adjacency, observed, "");

        return res;
    }

    static void backtrack(int i, List<String> res, Map<Character, List<String>> adjacency, String observed, String cur) {
        if (i == observed.length()) {
            res.add(cur);
            return;
        }
        for (String code : adjacency.get(observed.charAt(i))) {
            backtrack(i + 1, res, adjacency, observed, cur + code);
        }
    }

    static Map<Character, List<String>> getAdjacency() {
        Map<Character, List<String>> adjacency = new HashMap<>();
        adjacency.put('1', List.of("2", "4", "1"));
        adjacency.put('2', List.of("1", "3", "5", "2"));
        adjacency.put('3', List.of("2", "6", "3"));
        adjacency.put('4', List.of("1", "7", "5", "4"));
        adjacency.put('5', List.of("4", "2", "6", "8", "5"));
        adjacency.put('6', List.of("9", "5", "6", "3"));
        adjacency.put('7', List.of("8", "4", "7"));
        adjacency.put('8', List.of("7", "9", "5", "0", "8"));
        adjacency.put('9', List.of("8", "6", "9"));
        adjacency.put('0', List.of("8", "0"));
        return adjacency;
    }
}
