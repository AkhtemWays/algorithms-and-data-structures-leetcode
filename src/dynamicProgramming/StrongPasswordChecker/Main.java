package dynamicProgramming.StrongPasswordChecker;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        System.out.println(strongPasswordChecker("a")); // 5
//        System.out.println(strongPasswordChecker("aA1")); // 3
//        System.out.println(strongPasswordChecker("1337C0d3")); // 0
        System.out.println(strongPasswordChecker("1111111111")); // 3
    }

    public static int strongPasswordChecker(String password) {
        int n = password.length();
        Set<Integer> threeRepeatingStartIndices = new HashSet<>();
        int lowercase = 0, uppercase = 0, digit = 0;
        int repCount = 1;
        for (int i = 0; i < n; i++) {
            char ch = password.charAt(i);
            if (i > 0 && ch == password.charAt(i-1)) {
                repCount++;
                if (repCount >= 3) threeRepeatingStartIndices.add(i - repCount + 1);
            } else {
                repCount = 1;
            }
            if (Character.isLowerCase(ch)) lowercase++;
            else if (Character.isUpperCase(ch)) uppercase++;
            else if (Character.isDigit(ch)) digit++;
        }

        return dfs(password, threeRepeatingStartIndices, lowercase, uppercase, digit);
    }

    private static int dfs(String password, Set<Integer> threeRepeatingStartIndices, int lowercase, int uppercase, int digit) {
        if (check(password, threeRepeatingStartIndices, lowercase, uppercase, digit)) return 0;

        int n = password.length();
        int smallest = Math.min(Math.min(lowercase, uppercase), digit);
        if (n < 6) {
            if (!threeRepeatingStartIndices.isEmpty()) {
                int index = threeRepeatingStartIndices.stream().findAny().get();
                threeRepeatingStartIndices.remove(index);
                if (checkThreeRepeating(password, index+2)) {
                    threeRepeatingStartIndices.add(index+3);
                }
                if (smallest == digit) {
                    password = addAnyOther(password, index+1, 0);
                    digit++;
                } else if (smallest == lowercase) {
                    password = addAnyOther(password, index+1, 1);
                    lowercase++;
                } else {
                    password = addAnyOther(password, index+1, 2);
                    uppercase++;
                }
                return 1 + dfs(password, threeRepeatingStartIndices, lowercase, uppercase, digit);
            } else {
                int c = 0;
                c += lowercase > 0 ? 0 : 1;
                c += digit > 0 ? 0 : 1;
                c += uppercase > 0 ? 0 : 1;
                return n + c >= 6 ? c : 6 - n;
            }
        } else if (n > 20) {
            if (!threeRepeatingStartIndices.isEmpty()) {
                int index = threeRepeatingStartIndices.stream().findAny().get();
                threeRepeatingStartIndices.remove(index);
                if (checkThreeRepeating(password, index+1)) {
                    threeRepeatingStartIndices.add(index);
                }
                if (smallest == digit) {
                    password = deleteAnyOther(password, index);
                    digit++;
                } else if (smallest == lowercase) {
                    password = deleteAnyOther(password, index);
                    lowercase++;
                } else {
                    password = deleteAnyOther(password, index);
                    uppercase++;
                }
                return 1 + dfs(password, threeRepeatingStartIndices, lowercase, uppercase, digit);
            } else {
                int c = 0;
                c += lowercase > 0 ? 0 : 1;
                c += digit > 0 ? 0 : 1;
                c += uppercase > 0 ? 0 : 1;
                return n - 20 + c;
            }
        } else {
            if (!threeRepeatingStartIndices.isEmpty()) {
                int index = threeRepeatingStartIndices.stream().findAny().get();
                threeRepeatingStartIndices.remove(index);
                if (checkThreeRepeating(password, index+3)) {
                    threeRepeatingStartIndices.add(index+3);
                }
                if (smallest == digit) {
                    password = replaceToAnyOther(password, index+2, 0);
                    digit++;
                } else if (smallest == lowercase) {
                    password = replaceToAnyOther(password, index+2, 1);
                    lowercase++;
                } else {
                    password = replaceToAnyOther(password, index+2, 2);
                    uppercase++;
                }
                return 1 + dfs(password, threeRepeatingStartIndices, lowercase, uppercase, digit);
            } else {
                int c = 0;
                c += lowercase > 0 ? 0 : 1;
                c += digit > 0 ? 0 : 1;
                c += uppercase > 0 ? 0 : 1;
                return c;
            }
        }
    }

    private static boolean checkThreeRepeating(String password, int i) {
        if (i + 3 >= password.length()) return false;
        for (int j = i+1; j < i + 3; j++) {
            if (password.charAt(j) != password.charAt(j-1)) return false;
        }
        return true;
    }

    private static boolean check(String password, Set<Integer> threeRepeatingStartIndices, int lowercase, int uppercase, int digit) {
        int n = password.length();
        if (n > 20 || n < 6) return false;
        return threeRepeatingStartIndices.isEmpty() && lowercase > 0 && uppercase > 0 && digit > 0;
    }

    private static String addAnyOther(String password, int i, int code) {
        switch (code) {
            case 0:
                return password.substring(0, i+1) + (password.charAt(i) == '1' ? '2' : '1') + password.substring(i+1);
            case 1:
                return password.substring(0, i+1) + (password.charAt(i) == 'a' ? 'b' : 'a') + password.substring(i+1);
            default:
                return password.substring(0, i+1) + (password.charAt(i) == 'A' ? 'B' : 'A') + password.substring(i+1);
        }
    }

    private static String replaceToAnyOther(String password, int i, int code) {
        switch (code) {
            case 0:
                return password.substring(0, i) + (password.charAt(i) == '1' ? '2' : '1') + password.substring(i+1);
            case 1:
                return password.substring(0, i) + (password.charAt(i) == 'a' ? 'b' : 'a') + password.substring(i+1);
            default:
                return password.substring(0, i) + (password.charAt(i) == 'A' ? 'B' : 'A') + password.substring(i+1);
        }
    }

    private static String deleteAnyOther(String password, int i) {
        return password.substring(0, i) + password.substring(i+1);
    }
}
