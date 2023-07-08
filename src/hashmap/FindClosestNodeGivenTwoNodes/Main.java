package hashmap.FindClosestNodeGivenTwoNodes;

import java.util.HashMap;

public class Main {
    private static void test1() {
        int[] edges = {2,2,3,-1};
        System.out.println(closestMeetingNode(edges, 0, 1));
    }
    private static void test2() {
        int[] edges = {1,2,-1};
        System.out.println(closestMeetingNode(edges, 0, 2));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static int closestMeetingNode(int[] edges, int node1, int node2) {
        HashMap<Integer, Integer> distanceMap1 = getDistanceMap(edges, node1);
        HashMap<Integer, Integer> distanceMap2 = getDistanceMap(edges, node2);
        int maxDistance = -1;
        int node = -1;
        distanceMap2.put(node2, 0);distanceMap1.put(node1, 0);
        for (int key : distanceMap1.keySet()) {
            if (distanceMap2.containsKey(key)) {
                int d = Math.min(distanceMap1.get(key), distanceMap2.get(key));
                if (d > maxDistance) {
                    maxDistance = d;
                    node = key;
                }
            }
        }
        return node;
    }

    private static HashMap<Integer, Integer> getDistanceMap(int[] edges, int node) {
        int n = edges.length;
        HashMap<Integer, Integer> distanceMap = new HashMap<>();
        for (int i = 0, d = 0; i < n; i++) {
            if (distanceMap.containsKey(node)) break;
            distanceMap.put(node, d++);
            node = edges[node];
            if (node == -1) break;
        }
        return distanceMap;
    }
}
