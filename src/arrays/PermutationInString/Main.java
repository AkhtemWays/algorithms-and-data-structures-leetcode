package arrays.PermutationInString;

public class Main {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] targetPermutation = new int[26];
        int[] curPermutation = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            targetPermutation[s1.charAt(i) - 'a']++;
            curPermutation[s2.charAt(i) - 'a']++;
        }
        if (permutationsAreEqual(curPermutation, targetPermutation)) return true;
        for (int i = s1.length(); i < s2.length(); i++) {
            curPermutation[s2.charAt(i - s1.length()) - 'a']--;
            curPermutation[s2.charAt(i) - 'a']++;
            if (permutationsAreEqual(curPermutation, targetPermutation)) return true;
        }
        return false;
    }

    private static boolean permutationsAreEqual(int[] curPermutation, int[] targetPermutation) {
        for (int i = 0; i < 26; i++) {
            if (curPermutation[i] != targetPermutation[i]) return false;
        }
        return true;
    }
}
