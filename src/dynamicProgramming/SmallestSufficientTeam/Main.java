package dynamicProgramming.SmallestSufficientTeam;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static void test1() {
        String[] req_skills = {"java","nodejs","reactjs"};
        List<List<String>> people = new ArrayList<>(List.of(
            List.of("java"), List.of("nodejs"), List.of("nodejs","reactjs")
        ));
        System.out.println(Arrays.toString(smallestSufficientTeam(req_skills, people)));
    }
    private static void test2() {
        String[] req_skills = {"algorithms","math","java","reactjs","csharp","aws"};
        List<List<String>> people = new ArrayList<>(List.of(
                List.of("algorithms","math","java"), List.of("algorithms","math","reactjs"), List.of("java","csharp","aws"), List.of("reactjs","csharp"), List.of("aws","java")
        ));
        System.out.println(Arrays.toString(smallestSufficientTeam(req_skills, people)));
    }
    private static void test3() {
        String[] req_skills = {"gvp","jgpzzicdvgxlfix","kqcrfwerywbwi","jzukdzrfgvdbrunw","k"};
        List<List<String>> people = new ArrayList<>(List.of(
                List.of(), List.of(), List.of(), List.of(),
                List.of("jgpzzicdvgxlfix"), List.of("jgpzzicdvgxlfix","k"),
                List.of("jgpzzicdvgxlfix","kqcrfwerywbwi"), List.of("gvp"), List.of("jzukdzrfgvdbrunw"),
                List.of("gvp","kqcrfwerywbwi")
        ));
        System.out.println(Arrays.toString(smallestSufficientTeam(req_skills, people)));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static List<Integer> answer;
    private static int requiredSkills;
    private static HashMap<String, Integer> dictionary;

    public static int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        answer = getDefaultPeople(people);
        requiredSkills = (1 << (req_skills.length)) - 1;
        dictionary = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++) dictionary.put(req_skills[i], i);
        List<List<Integer>> newPeople = people.stream().map(person -> person.stream().map(str -> dictionary.get(str)).collect(Collectors.toList())).collect(Collectors.toList());
        dfs(0L, 0, newPeople, new HashSet<>(), new ArrayList<>());
        int[] res = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) res[i] = answer.get(i);
        return res;
    }

    private static void dfs(long state, int skills, List<List<Integer>> people, HashSet<Integer> memo, List<Integer> taken) {
        if (skills == requiredSkills) {
            if (taken.size() < answer.size()) answer = List.copyOf(taken);
            return;
        }

        if (memo.contains(skills)) return;

        for (int i = 0; i < people.size(); i++) {
            List<Integer> person = people.get(i);
            if (((state >> i) & 1) == 0) {
                int updatedSkills = skills;
                for (Integer skill : person) updatedSkills |= (1 << skill);
                if (updatedSkills != skills) {
                    taken.add(i);
                    dfs(state | (1L << i), updatedSkills, people, memo, taken);
                    taken.remove(taken.size()-1);
                }
            }
        }

        memo.add(skills);
    }

    private static List<Integer> getDefaultPeople(List<List<String>> people) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < people.size()+1; i++) l.add(i);
        return l;
    }
}
