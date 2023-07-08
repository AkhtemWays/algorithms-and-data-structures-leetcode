package graphs.CheckingExistenceofEdgeLengthLimitedPaths;

import java.util.Arrays;
import java.util.Comparator;

class UnionFind {
    int[] groups;
    int[] ranks;
    public int find(int node) {
        while (groups[node] != node) {
            node = groups[node];
        }
        return groups[node];
    }

    public void union(int node1, int node2) {
        int group1 = find(node1);
        int group2 = find(node2);

        if (group1 == group2) return;

        if (ranks[group1] > ranks[group2]) {
            ranks[group1] += ranks[group2];
            groups[group2] = group1;
        } else {
            ranks[group2] += ranks[group1];
            groups[group1] = group2;
        }
    }

    UnionFind(int n) {
        groups = new int[n];
        ranks = new int[n];
        for (int i = 0; i < n; i++) {
            groups[i] = i;
            ranks[i] = 1;
        }
    }
}

public class Main {
    private static void test1() {
        int[][] edges = {{0,1,2},{1,2,4},{2,0,8},{1,0,16}};
        int[][] queries = {{0,1,2},{0,2,5}};
        System.out.println(Arrays.toString(distanceLimitedPathsExist(3, edges, queries)));
    }
    private static void test2() {
        int[][] edges = {{0,1,10},{1,2,5},{2,3,9},{3,4,13}};
        int[][] queries = {{0,4,14},{1,4,13}};
        System.out.println(Arrays.toString(distanceLimitedPathsExist(5, edges, queries)));
    }
    private static void test3() {
       int[][] edges = {{9,14,88},{11,27,51},{29,22,58},{29,27,26},{18,20,97},{25,4,12},{2,4,16},{17,18,40},{21,9,37},{3,14,6},{8,23,24},{7,27,39},{24,16,95},{9,29,19},{9,18,22},{26,21,12},{12,14,81},{6,14,79},{3,16,47},{13,19,18},{24,15,59},{14,20,26},{24,20,14},{25,26,7},{13,12,81},{1,0,51},{17,4,39},{8,16,77},{28,9,53},{23,6,40},{29,18,31},{10,9,35},{29,27,7},{1,29,91},{10,19,70},{28,29,58},{20,17,86},{21,14,77},{19,4,43},{26,21,22},{2,26,61},{24,22,16}};
       int[][] queries = {{21,10,1},{14,2,21},{15,11,64},{21,27,17},{3,26,1},{26,18,93},{8,6,5},{18,19,62},{19,18,30},{7,25,76},{0,20,78},{11,6,16},{16,2,91},{22,21,66},{28,24,95},{19,4,18},{14,23,37},{19,27,7},{15,10,83},{23,5,59},{17,12,9},{26,5,90},{26,24,46},{21,29,30},{24,18,54},{27,3,94},{1,23,75},{28,22,75},{27,11,32},{11,20,62},{12,11,10},{17,14,4},{27,22,67},{19,0,25},{16,24,38},{23,6,35},{11,21,96},{28,14,38},{29,17,8},{10,21,85},{2,27,97},{25,13,98}};
        System.out.println(Arrays.toString(distanceLimitedPathsExist(30, edges, queries)));
    }
    public static void main(String[] args) {
        test1(); // [false, true]
        test2(); // [true, false]
        test3();
    }

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int[][] queriesIndexed = new int[queries.length][4];
        for (int i = 0; i < queriesIndexed.length; i++) {
            int[] query = queries[i];
            queriesIndexed[i] = new int[]{query[0], query[1], query[2], i};
        }
        boolean[] answer = new boolean[queries.length];
        Arrays.sort(queriesIndexed, Comparator.comparingInt(a -> a[2]));
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));

        UnionFind unionFind = new UnionFind(n);

        for (int i = 0, j = 0; i < queriesIndexed.length && j < edgeList.length; i++) {
            int[] query = queriesIndexed[i];
            int startNode = query[0];
            int targetNode = query[1];
            int limit = query[2];
            int index = query[3];

            while (j < edgeList.length && edgeList[j][2] < limit) {
                int node1 = edgeList[j][0];
                int node2 = edgeList[j][1];
                unionFind.union(node1, node2);
                j++;
            }

            int group1 = unionFind.find(startNode);
            int group2 = unionFind.find(targetNode);
            answer[index] = group1 == group2;
        }
        return answer;
    }
}
