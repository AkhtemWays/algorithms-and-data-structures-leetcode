package backtracking.StickerstoSpellWord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static void test1() {
        String[] stickers = {"with","example","science"};
        System.out.println(minStickers(stickers, "thehat"));
    }
    private static void test2() {
        String[] stickers = {"notice","possible"};
        System.out.println(minStickers(stickers, "basicbasic"));
    }
    private static void test3() {
        String[] stickers = {"these","guess","about","garden","him"};
        System.out.println(minStickers(stickers, "atomher"));
    }
    private static void test4() {
        String[] stickers = {"feed", "industry", "let", "pair", "milk", "hope"};
        System.out.println(minStickers(stickers, "likehuman"));
    }
    public static void main(String[] args) {
        test1(); // 3
        test2(); // -1
        test3(); // 3
        test4(); // 5
    }

    public static int minStickers(String[] stickers, String target) {
        List<int[]> activeStickers = new ArrayList<>();
        int n = stickers.length;
        int[][] allStickers = new int[n][26];
        for (int i = 0; i < n; i++) {
            allStickers[i] = getRepr(stickers[i]);
        }

        for (int i = 0; i < target.length(); i++) {
            boolean found = false;
            for (int[] sticker : allStickers) {
                if (sticker[target.charAt(i) - 'a'] > 0) {
                    found = true;
                    break;
                }
            }
            if (!found) return -1;
        }
        HashMap<String, Integer> memo = new HashMap<>();

        return dfs(0, target, activeStickers, allStickers, memo);
    }

    private static int dfs(int i, String target, List<int[]> activeStickers, int[][] allStickers, HashMap<String, Integer> memo) {
        if (i == target.length()) return 0;
        String key = target.substring(0, i);
        if (memo.containsKey(key)) return memo.get(key);
        for (int[] activeSticker : activeStickers) {
            if (activeSticker[target.charAt(i) - 'a'] > 0) {
                activeSticker[target.charAt(i) - 'a']--;
                int res = dfs(i+1, target, activeStickers, allStickers, memo);
                activeSticker[target.charAt(i) - 'a']++;
                memo.put(key, res);
                return res;
            }
        }

        int minStickers = Integer.MAX_VALUE;
        for (int[] sticker : allStickers) {
            if (sticker[target.charAt(i) - 'a'] > 0) {
                int[] copy = Arrays.copyOf(sticker, 26);
                copy[target.charAt(i) - 'a']--;
                activeStickers.add(copy);
                minStickers = Math.min(minStickers, 1 + dfs(i+1, target, activeStickers, allStickers, memo));
                activeStickers.remove(activeStickers.size()-1);
            }
        }
        memo.put(key, minStickers);
        return minStickers;
    }

    private static int[] getRepr(String s) {
        int[] res = new int[26];
        for (int i = 0; i < s.length(); i++) {
            res[s.charAt(i) - 'a']++;
        }
        return res;
    }
}
