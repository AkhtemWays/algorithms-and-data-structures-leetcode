package disjointSets_UnionFind.GreatestCommonDivisorTraversal;

import java.util.*;

class UnionFind {
    private final int[] parents;
    private final int[] ranks;
    int numComponents;

    UnionFind(int n) {
        parents = new int[n];
        ranks = new int[n];
        numComponents = n;
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public int find(int node) {
        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }

    public void union(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);

        if (parent1 == parent2) return;

        if (ranks[parent1] > ranks[parent2]) {
            parents[parent2] = parent1;
            ranks[parent1] += ranks[parent2];
        } else {
            parents[parent1] = parent2;
            ranks[parent2] += ranks[parent1];
        }
    }
}

public class Main {
    private static void test1() {
        int[] nums = {2,3,6};
        System.out.println(canTraverseAllPairs(nums));
    }
    private static void test2() {
        int[] nums = {3,9,5};
        System.out.println(canTraverseAllPairs(nums));
    }
    private static void test3() {
        int[] nums = {4,3,12,8};
        System.out.println(canTraverseAllPairs(nums));
    }
    private static void test4() {
        int[] nums = {6,20};
        System.out.println(canTraverseAllPairs(nums));
    }
    private static void test5() {
        int[] nums = {28,39};
        System.out.println(canTraverseAllPairs(nums));
    }
    private static final int MAX = 10000;
    public static void main(String[] args) {
        test1(); // true
        test2(); // false
        test3(); // true
        test4(); // true
        test5(); // false
    }

    public static boolean canTraverseAllPairs(int[] nums) {
        Set<Integer> uniques = new HashSet<>();
        int max = 0, min = 0;
        for (int num : nums) {
            uniques.add(num);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        List<Integer> sorted = new ArrayList<>(uniques);
        Collections.sort(sorted);
        UnionFind uf = new UnionFind(max+1);
        for (int num = 2; num <= max; num++) {
            List<Integer> cur = new ArrayList<>();
            if (uniques.contains(num)) cur.add(num);
            for (int divisible = num * 2; divisible <= max; divisible += num) {
                if (uniques.contains(divisible)) cur.add(divisible);
            }
            for (int i = 1; i < cur.size(); i++) {
                uf.union(cur.get(i-1), cur.get(i));
            }
        }

        int component = uf.find(uniques.stream().findAny().get().intValue());

        return uniques.stream().allMatch(value -> uf.find(value) == component);
    }
}
