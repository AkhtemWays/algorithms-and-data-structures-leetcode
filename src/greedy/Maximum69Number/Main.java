package greedy.Maximum69Number;

public class Main {
    public static void main(String[] args) {
        int num = 9669;
        System.out.println(maximum69Number(num));
    }

    public static int maximum69Number(int num) {
        String str = String.valueOf(num);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            res.append(9);
            if (str.charAt(i) == '6') {
                res.append(str.substring(i+1));
                return Integer.parseInt(res.toString());
            }
        }
        return Integer.parseInt(res.toString());
    }
}
