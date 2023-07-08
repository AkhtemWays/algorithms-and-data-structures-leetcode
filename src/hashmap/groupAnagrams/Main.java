package hashmap.groupAnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> result = groupAnagrams(strs);
        result.forEach(anagram -> System.out.println(anagram.toString()));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> recorder = new HashMap<>();
        for (String str: strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedString = Arrays.toString(charArray);
            if (recorder.containsKey(sortedString)) {
                recorder.get(sortedString).add(str);
            } else {
                recorder.put(sortedString, new ArrayList<>(List.of(str)));
            }
        }
        return new ArrayList<>(recorder.values());
    }
}
