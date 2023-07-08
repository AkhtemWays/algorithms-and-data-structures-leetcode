package strings.FirstUniqueCharacterInaString;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(validateIP("192.168.0.1"));
        System.out.println(validateIP("0.0.0.0"));
    }

    static boolean validateIP(String ip) { // 192.168.0.1

        List<String> oktets = new ArrayList<>();
        String str = "";

        for(int i = 0; i < ip.length(); i++) {
            if(ip.charAt(i) == '.') {
                oktets.add(str);
                str = "";
            }
            else str += ip.charAt(i);
        }

        oktets.add(str);
        System.out.println(oktets); // [192, 168, 123, 456][192, 168, 0]
        return oktets.size() == 4 && validateList(oktets);
    }


    public static boolean validateList(List<String> oktets) {

        for(int i = 0; i < oktets.size(); i++){

            try{
                Integer num = Integer.parseInt(oktets.get(i));

                if(num < 0 && num > 255) {
                    return false;
                }
            } catch(Exception e) {
                return false;
            }

        }

        return true;
    }
}
