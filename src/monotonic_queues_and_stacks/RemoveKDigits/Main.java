package monotonic_queues_and_stacks.RemoveKDigits;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("10", 2));
        System.out.println(removeKdigits("10", 1));
    }

    public static String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            if (st.isEmpty() || Character.getNumericValue(num.charAt(i)) > st.peek()) {
                st.add(Character.getNumericValue(num.charAt(i)));
                continue;
            }
            while (!st.isEmpty() && st.peek() > Character.getNumericValue(num.charAt(i)) && k > 0) {
                st.pop();
                k--;
            }
            st.add(Character.getNumericValue(num.charAt(i)));
        }
        StringBuilder res = new StringBuilder();
        while (k-- > 0) {
            st.pop();
        }
        for (int i = 0; i < st.size(); i++) {
            res.append(st.get(i));
        }
        int i = 0;
        while (i < res.length() && Character.getNumericValue(res.charAt(i)) == 0) {
            i++;
        }
        return res.substring(i).equals("") ? "0" : res.substring(i);
    }
}
