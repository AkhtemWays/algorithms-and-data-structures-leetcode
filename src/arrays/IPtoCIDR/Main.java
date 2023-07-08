package arrays.IPtoCIDR;

import java.util.List;

// Input: ip = "255.0.0.7", n = 10
//Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
//Explanation:
//The IP addresses that need to be covered are:
//- 255.0.0.7  -> 11111111 00000000 00000000 00000111
//- 255.0.0.8  -> 11111111 00000000 00000000 00001000
//- 255.0.0.9  -> 11111111 00000000 00000000 00001001
//- 255.0.0.10 -> 11111111 00000000 00000000 00001010
//- 255.0.0.11 -> 11111111 00000000 00000000 00001011
//- 255.0.0.12 -> 11111111 00000000 00000000 00001100
//- 255.0.0.13 -> 11111111 00000000 00000000 00001101
//- 255.0.0.14 -> 11111111 00000000 00000000 00001110
//- 255.0.0.15 -> 11111111 00000000 00000000 00001111
//- 255.0.0.16 -> 11111111 00000000 00000000 00010000
//The CIDR block "255.0.0.7/32" covers the first address.
//The CIDR block "255.0.0.8/29" covers the middle 8 addresses (binary format of 11111111 00000000 00000000 00001xxx).
//The CIDR block "255.0.0.16/32" covers the last address.
//Note that while the CIDR block "255.0.0.0/28" does cover all the addresses, it also includes addresses outside of the range, so we cannot use it.

public class Main {
    public static void main(String[] args) {

    }

    public static List<String> ipToCIDR(String ip, int n) {
        String[] bytes = ip.split("\\.");
        int firstByte = Integer.parseInt(bytes[0]);
        int secondByte = Integer.parseInt(bytes[1]);
        int thirdByte = Integer.parseInt(bytes[2]);
        int fourthByte = Integer.parseInt(bytes[3]);

        return List.of();
    }
}
