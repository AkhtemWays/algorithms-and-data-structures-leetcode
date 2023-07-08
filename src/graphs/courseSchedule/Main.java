package graphs.courseSchedule;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] prerequisites1 = {{1,0}};
        int[][] prerequisites2 = {{1,0},{0,1}};
        int[][] prerequisites3 = {{0, 1}, {0, 2}, {1, 2}};
        int[][] prerequisites4 = {{1, 0}, {1, 2}, {0, 1}};
        System.out.println(canFinish2(2, prerequisites1));
        System.out.println(canFinish2(2, prerequisites2));
        System.out.println(canFinish2(3, prerequisites3));
        System.out.println(canFinish2(3, prerequisites4));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> coursesPrerequisites = new HashMap<>();
        for (int course = 0; course < numCourses; course++) coursesPrerequisites.put(course, new ArrayList<>());
        for (int[] prerequisite : prerequisites) coursesPrerequisites.get(prerequisite[0]).add(prerequisite[1]);

        for (int course = 0; course < numCourses; course++) {
            if (!bfs(course, coursesPrerequisites, new boolean[numCourses])) return false;
        }
        return true;
    }

    static boolean bfs(int course, Map<Integer, List<Integer>> coursesPrerequisites, boolean[] visited) {
        if (visited[course]) return false;
        visited[course] = true;
        if (coursesPrerequisites.get(course).isEmpty()) return true;

        for (int prerequisite : coursesPrerequisites.get(course)) {
            if (!bfs(prerequisite, coursesPrerequisites, visited)) return false;
        }
        return true;
    }

    public static boolean canFinish2(int n, int[][] prerequisites) {
        ArrayList<Integer>[] G = new ArrayList[n];
        int[] degree = new int[n];
        ArrayList<Integer> bfs = new ArrayList();
        for (int i = 0; i < n; ++i) G[i] = new ArrayList<Integer>();
        for (int[] e : prerequisites) {
            G[e[1]].add(e[0]);
            degree[e[0]]++;
        }
        for (int i = 0; i < n; ++i) if (degree[i] == 0) bfs.add(i);
        for (int i = 0; i < bfs.size(); ++i)
            for (int j: G[bfs.get(i)])
                if (--degree[j] == 0) bfs.add(j);
        return bfs.size() == n;
    }
}
