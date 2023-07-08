package graphs.minimumHeightTrees;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] edges1 = {{1,0},{1,2},{1,3}};
        int[][] edges2 = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        Main main = new Main();
        System.out.println(main.findMinHeightTrees2(4, edges1));
//        System.out.println(main.findMinHeightTrees2(6, edges2));
    }

    static class DistanceNode {
        int height;
        int val;

        DistanceNode(int height, int val) {this.height = height; this.val = val;}
    }

    // SOLUTION 1 brute force
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int h = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int root = 0; root < n; root++) {
            int height = getHeight(root, graph, new boolean[n], 0);
            if (height < h) {
                res = new ArrayList<>();
                h = height;
            }
            if (height == h) res.add(root);
        }
        return res;
    }

    public static int getHeight(int node, List<List<Integer>> graph, boolean[] visited, int h) {
        if (visited[node]) return h;
        visited[node] = true;

        int height = h;
        for (int neighbor : graph.get(node)) {
            height = Math.max(getHeight(neighbor, graph, visited, h + 1), height);
        }
        return height;
    }

    // solution 2
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        DistanceNode nodeA = new DistanceNode(1, 0);
        DistanceNode nodeB = getNode(nodeA, graph, new DistanceNode[n]);
        DistanceNode nodeC = getNode(new DistanceNode(1, nodeB.val), graph, new DistanceNode[n]);

        if (nodeC.height % 2 == 0) {
            return List.of(
                    findNodeByHeight(nodeB.val, 0, graph, new boolean[n], (nodeC.height / 2) - 1),
                    findNodeByHeight(nodeB.val, 0, graph, new boolean[n], nodeC.height / 2)
            );
        } else {
            return List.of(findNodeByHeight(nodeB.val, 0, graph, new boolean[n], nodeC.height / 2));
        }
    }

    public static DistanceNode getNode(DistanceNode node, List<List<Integer>> graph, DistanceNode[] visited) {
        if (visited[node.val] != null) return visited[node.val];
        visited[node.val] = node;

        DistanceNode furthermost = node;
        for (int neighborVal : graph.get(node.val)) {
            DistanceNode maybeFurthermost = getNode(new DistanceNode(node.height + 1, neighborVal), graph, visited);
            if (maybeFurthermost.height > furthermost.height) {
                furthermost = maybeFurthermost;
            }
        }
        return furthermost;
    }

    static int findNodeByHeight(int node, int height, List<List<Integer>> graph, boolean[] visited, int targetHeight) {
        if (visited[node]) return -1;
        visited[node] = true;

        if (height == targetHeight) return node;

        for (int neighbor : graph.get(node)) {
            int solutionNode = findNodeByHeight(neighbor, height + 1, graph, visited, targetHeight);
            if (solutionNode != -1) return solutionNode;
        }
        return -1;
    }
}
