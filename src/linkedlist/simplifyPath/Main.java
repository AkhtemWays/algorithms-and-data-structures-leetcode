package linkedlist.simplifyPath;

import java.util.Stack;


public class Main {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/../"));
    }

    public static String simplifyPath(String path) {
        path = path.replaceAll("[/]+", "/");
        if (path.endsWith("/") && path.length() != 1) path = path.substring(0, path.length() - 1);
        String[] directories = path.substring(1).split("/");
        Stack<String> result = new Stack<>();
        for (String directory : directories) {
            if (directory.equals(".")) continue;

            if (directory.equals("..")) {
                if (result.size() > 0) result.pop();
            }

            else result.add(directory);
        }
        String res = result.stream().collect(
                () -> new StringBuilder("/"),
                (StringBuilder a, String b) -> a.append(b).append("/"),
                StringBuilder::append
        ).toString();
        if (res.length() == 1) return res;
        return res.substring(0, res.length() - 1);
    }
}