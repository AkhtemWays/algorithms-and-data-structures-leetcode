package suffix_arrays.notOptimizedSuffixArrayBuild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotOptimalBuild {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(buildSuffixArray("ABAABBB")));
    }

    public static int[] buildSuffixArray(String s) {
        List<String> substrings = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) substrings.add(s.substring(i));

        substrings.sort(String::compareTo);
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) result[i] = s.length() - substrings.get(i).length();
        return result;
    }
}
