package graphs.DistancetoaCycleinUndirectedGraph;

import java.util.*;

public class Main {
    private static int CONSTANT = Integer.MAX_VALUE;
    private static void test1() {
        int[][] edges = {{3,0},{2,0},{2,1},{0,1},{4,2}};
        System.out.println(Arrays.toString(distanceToCycle(5, edges)));
    }
    private static void test2() {
        int[][] edges = {{1,2},{3,7},{5,6},{3,0},{2,3},{7,4},{8,2},{9,3},{6,1},{8,4}};
        System.out.println(Arrays.toString(distanceToCycle(10, edges)));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static int[] distanceToCycle(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Set<Integer> cyclicNodes = findCyclicNodes(0, -1, graph, new boolean[n], new Stack<>());
        int[] answer = new int[n];
        Arrays.fill(answer, CONSTANT);
        for (int cyclicNode : cyclicNodes) answer[cyclicNode] = 0;
        for (int cyclicNode : cyclicNodes) {
            dfs(cyclicNode, -1, answer, graph);
        }
        return answer;
    }

    private static void dfs(int node, int parent, int[] answer, List<Integer>[] graph) {
        for (int neighbour : graph[node]) {
            if (neighbour != parent) {
                if (answer[neighbour] > answer[node] + 1) {
                    answer[neighbour] = answer[node] + 1;
                    dfs(neighbour, node, answer, graph);
                }
            }
        }
    }

    private static Set<Integer> findCyclicNodes(int node, int parent, List<Integer>[] graph, boolean[] visited, Stack<Integer> callStack) {
        visited[node] = true;
        callStack.add(node);
        for (int neighbour : graph[node]) {
            if (neighbour != parent) {
                if (visited[neighbour]) {
                    Set<Integer> s = new HashSet<>();
                    while (callStack.peek() != neighbour) {
                        s.add(callStack.pop());
                    }
                    s.add(neighbour);
                    return s;
                } else {
                    Set<Integer> s = findCyclicNodes(neighbour, node, graph, visited, callStack);
                    if (s != null) return s;
                }
            }
        }
        callStack.pop();
        return null;
    }
}
