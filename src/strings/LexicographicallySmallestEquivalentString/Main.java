package strings.LexicographicallySmallestEquivalentString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    private static void test1() {
        System.out.println(smallestEquivalentString("parker", "morris", "parser"));
    }
    private static void test2() {
        System.out.println(smallestEquivalentString("hello", "world", "hold"));
    }
    private static void test3() {
        System.out.println(smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        HashSet<String> groups = new HashSet<>();
        for (int i = 0; i < s1.length(); i++) {
            String group1 = findGroup(s1.charAt(i), groups);
            String group2 = findGroup(s2.charAt(i), groups);
            if (group1 == null && group2 == null) {
                String group = "" + s1.charAt(i) + s2.charAt(i);
                groups.add(group);
            } else if (group1 == null || group2 == null) {
                if (group1 != null) {
                    groups.remove(group1);
                    group1 += s2.charAt(i);
                    groups.add(group1);
                } else {
                    groups.remove(group2);
                    group2 += s1.charAt(i);
                    groups.add(group2);
                }
            } else if (!group1.equals(group2)) {
                groups.remove(group1);
                groups.remove(group2);
                groups.add(group1 + group2);
            }
        }

        List<String> groupsList = new ArrayList<>();
        for (String group : groups) {
            char[] temp = group.toCharArray();
            Arrays.sort(temp);
            groupsList.add(new String(temp));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            char ch = baseStr.charAt(i);
            boolean found = false;
            for (int j = 0; j < groupsList.size(); j++) {
                if (groupsList.get(j).contains(ch + "")) {
                    res.append(groupsList.get(j).charAt(0));
                    found = true;
                    break;
                }
            }
            if (!found) res.append(ch);
        }
        return res.toString();
    }

    private static String findGroup(char ch, HashSet<String> groups) {
        for (String group : groups) {
            if (group.contains(ch + "")) return group;
        }
        return null;
    }
}
