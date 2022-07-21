/*
Kata Link: https://www.codewars.com/kata/52e88b39ffb6ac53a400022e

Take the following IPv4 address: 128.32.10.1

This address has 4 octets where each octet is a single byte (or 8 bits).

1st octet 128 has the binary representation: 10000000
2nd octet 32 has the binary representation: 00100000
3rd octet 10 has the binary representation: 00001010
4th octet 1 has the binary representation: 00000001
So 128.32.10.1 == 10000000.00100000.00001010.00000001

Because the above IP address has 32 bits, we can represent it as the unsigned 32 bit number: 2149583361

Complete the function that takes an unsigned 32 bit number and returns a string representation of its IPv4 address.

Examples:
2149583361 ==> "128.32.10.1"
32         ==> "0.0.0.32"
0          ==> "0.0.0.0"

========================================================================================================================

This was the best solution. This one makes me cringe honestly, it's so easy and simple and I didn't think of it.

public static String longToIP(long ip) {
        return String.format("%d.%d.%d.%d", ip>>>24, (ip>>16)&0xff, (ip>>8)&0xff, ip&0xff);
}
========================================================================================================================
*/
package java_folder;

import java.util.ArrayList;
import java.util.Collections;

public class Three {
    
    public static String longToIP(long ip) {        
        // Declare some masks and stuff that we can use to copy the values
        int mask = 0x0ff;
        long workingValue = ip;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for (int i = 0; i < 4; i++) {
            int chunk = (int) (workingValue & mask);
            if (chunk < 0) chunk = 0 - chunk;
            list.add(chunk);
            System.out.printf("CHUNK: %s\nWORKING VALUE: %s\n", Integer.toBinaryString(chunk), Integer.toBinaryString((int)workingValue));
            workingValue = workingValue >> 8;
        }
        Collections.reverse(list);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(i == (list.size() - 1) ? String.valueOf(list.get(i)) : String.valueOf(list.get(i)) + ".");
        }
        return builder.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(longToIP(2149583361L));    
    }
}
