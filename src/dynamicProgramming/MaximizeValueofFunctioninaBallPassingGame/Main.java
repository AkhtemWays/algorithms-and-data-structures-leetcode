package dynamicProgramming.MaximizeValueofFunctioninaBallPassingGame;

import java.util.*;

class Node {
    int value;
    long distanceToCycle;
    long cycleDistance;
    long cycleWeight;
    long cycleCost;
    Node(int value, long distanceToCycle, long cycleDistance, long cycleWeight, long cycleCost) {
        this.value = value;
        this.distanceToCycle = distanceToCycle;
        this.cycleDistance = cycleDistance;
        this.cycleWeight = cycleWeight;
        this.cycleCost = cycleCost;
    }
}

public class Main {
    private static HashMap<Integer, Node> memo;
    private static void test1() {
        List<Integer> receiver = new ArrayList<>(List.of(2,0,1));
        System.out.println(getMaxFunctionValue(receiver, 4));
    }
    private static void test2() {
        List<Integer> receiver = new ArrayList<>(List.of(1,1,1,2,3));
        System.out.println(getMaxFunctionValue(receiver, 3));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static long getMaxFunctionValue(List<Integer> receiver, long k) {
        memo = new HashMap<>();
        for (int node = 0; node < receiver.size(); node++) {
            if (!memo.containsKey(node)) dfs(node, receiver, new ArrayList<>(), new HashSet<>());
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.distanceToCycle));
        pq.addAll(memo.values());

        long answer = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            answer = Math.max(answer, calculatePath(node, k, receiver));
        }

        return answer;
    }

    private static long calculatePath(Node node, long k, List<Integer> receiver) {
        return 1L;
    }

    private static long findPrimitivePath(List<Integer> receiver, int node, long k) {
        if (k == 0) return 0;
        return node + findPrimitivePath(receiver, receiver.get(node), k-1);
    }

    private static void dfs(int node, List<Integer> receiver, List<Integer> stack, Set<Integer> visited) {
        if (visited.contains(node)) {
            List<Integer> cycleNodes = new ArrayList<>();
            cycleNodes.add(node);
            long cycleWeight = node;
            while (stack.get(stack.size()-1) != node) {
                int nod = stack.remove(stack.size()-1);
                cycleNodes.add(nod);
                cycleWeight += nod;
            }
            stack.remove(stack.size()-1);
            long cycleDistance = cycleNodes.size();
            for (int nodeValue : cycleNodes) {
                memo.put(nodeValue, new Node(nodeValue, 0, cycleDistance, cycleWeight, 0));
            }
            long distanceToCycle = 0;
            long cycleCost = 0;
            while (!stack.isEmpty()) {
                distanceToCycle++;
                int nodeValue = stack.remove(stack.size()-1);
                cycleCost += nodeValue;
                memo.put(nodeValue, new Node(nodeValue, distanceToCycle, cycleDistance, cycleWeight, cycleCost));
            }
        }
        else {
            stack.add(node);
            visited.add(node);
            dfs(receiver.get(node), receiver, stack, visited);
        }
    }
}
