package graphs.evaluateDivision;

import java.text.DecimalFormat;
import java.util.*;

public class Main {

    private static DecimalFormat roundFormatter = new DecimalFormat("########0.00000");

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>(List.of(new ArrayList<>(List.of("a","b")),new ArrayList<>(List.of("e","f")), new ArrayList<>(List.of("b","e"))));
        double[] values = {3.4,1.4,2.3};
        List<List<String>> queries = new ArrayList<>(List.of(
                new ArrayList<>(List.of("b","a")),
                new ArrayList<>(List.of("a","f")),
                new ArrayList<>(List.of("f","f")),
                new ArrayList<>(List.of("e","e")),
                new ArrayList<>(List.of("c","c")),
                new ArrayList<>(List.of("a", "c")),
                new ArrayList<>(List.of("f", "e"))));
        for (double value : calcEquation(equations, values, queries)) System.out.println(value);
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<List<Object>>> graph = new HashMap<>();
        double[] answers = new double[queries.size()];
        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);
            double value = values[i];
            String from = equation.get(0);
            String to = equation.get(1);

            List<Object> edge = new ArrayList<>(List.of(to, value));
            if (graph.containsKey(from)) graph.get(from).add(edge);
            else graph.put(from, new ArrayList<>(List.of(edge)));

            List<Object> reversedEdge = new ArrayList<>(List.of(from, 1/value));
            if (graph.containsKey(to)) graph.get(to).add(reversedEdge);
            else graph.put(to, new ArrayList<>(List.of(reversedEdge)));
        }

        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String from = query.get(0);
            String to = query.get(1);
            if (!graph.containsKey(from) || !graph.containsKey(to)) {
                answers[i] = -1D;
                continue;
            }
            double value = from.equals(to) ? 1.00000D : dfs(from, to, graph, initVisited(graph), 1.00000);
            answers[i] = value;
            if (value != -1D) {
                graph.get(from).add(new ArrayList<>(List.of(new ArrayList<>(List.of(to, value)))));
            }
        }

        return answers;
    }

    static double dfs(String cur, String target, Map<String, List<List<Object>>> graph, Map<String, Boolean> visited, double value) {
        if (visited.get(cur)) return -1D;
        if (cur.equals(target)) return value;
        visited.put(cur, true);

        List<List<Object>> edges = graph.get(cur);
        for (List<Object> edge : edges) {
            double val = dfs((String) edge.get(0), target, graph, visited, value * (Double) edge.get(1));
            if (val != -1D) return val;
        }
        return -1D;
    }

    static Map<String, Boolean> initVisited(Map<String, List<List<Object>>> graph) {
        Map<String, Boolean> visited = new HashMap<>();
        for (String key : graph.keySet()) {
            visited.put(key, false);
        }
        return visited;
    }
}
