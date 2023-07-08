package suffix_arrays.NumberofDistinctSubstringsinaString;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println(countDistinct("aabbaba"));
    }

    private static int[] kasai(String s) {
        s = s + "$";
        String[] suffixArray = new String[s.length()];
        String[] substrings = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            String substr = s.substring(i);
            suffixArray[i] = substr;
            substrings[i] = substr;
        }

        Arrays.sort(suffixArray);
        HashMap<String, Integer> sortedPositions = new HashMap<>();
        for (int index = 0; index < suffixArray.length; index++) {
            sortedPositions.put(suffixArray[index], index);
        }

        int[] lcpArray = new int[s.length()];
        for (int i = 0, k = 0; i < s.length(); i++) {
            String cur = substrings[i];
            int sortedPosition = sortedPositions.get(cur);
            if (sortedPosition - 1 >= 0) {
                String prev = suffixArray[sortedPosition-1];
                while (cur.charAt(k) == prev.charAt(k)) {
                    k++;
                }
                lcpArray[sortedPosition] = k;
                k = Math.max(0, k-1);
            }
        }
        return lcpArray;
    }

    public static int countDistinct(String s) {
        int totalStrings = (s.length()*(s.length()+1))/2;
        int[] lcpArray = kasai(s);
        return totalStrings - Arrays.stream(lcpArray).sum();
    }
}
