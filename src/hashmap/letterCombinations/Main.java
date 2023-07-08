package hashmap.letterCombinations;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // backtracking
        String digits = "";
        List<String> result = letterCombinations(digits);
        result.forEach(System.out::println);
    }

    public static List<String> letterCombinations(String digits) {
        if (Objects.equals(digits, "")) return List.of();
        Map<Character, String> dictionary = Map.of(
                '2', "abc",
                '3', "def",
                '4', "ghi",
                '5', "jkl",
                '6', "mno",
                '7', "pqrs",
                '8', "tuv",
                '9', "wxyz"
        );
        List<String> result = new ArrayList<>();
        dfs(digits, dictionary, result, 0,  "");
        return result;
    }

    public static void dfs(String digits, Map<Character, String> dictionary, List<String> result, int i, String combination) {
        if (combination.length() == digits.length()) {
            result.add(combination);
            return;
        }

        for (char letter : dictionary.get(digits.charAt(i)).toCharArray()) {
            dfs(digits, dictionary, result, i + 1, combination + letter);
        }
    }
}
