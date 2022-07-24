/*
Kata Link: https://www.codewars.com/kata/56b5afb4ed1f6d5fb0000991

The input is a string str of digits. Cut the string into chunks (a chunk here is a substring of the initial string) of size sz (ignore the last chunk if its size is less than sz).

If a chunk represents an integer such as the sum of the cubes of its digits is divisible by 2, reverse that chunk; otherwise rotate it to the left by one position. Put together these modified chunks and return the result as a string.

If:
sz is <= 0 or if str is empty return ""
sz is greater (>) than the length of str it is impossible to take a chunk of size sz hence return "".

Examples:
revrot("123456987654", 6) --> "234561876549"
revrot("123456987653", 6) --> "234561356789"
revrot("66443875", 4) --> "44668753"
revrot("66443875", 8) --> "64438756"
revrot("664438769", 8) --> "67834466"
revrot("123456779", 8) --> "23456771"
revrot("", 8) --> ""
revrot("123456779", 0) --> "" 
revrot("563000655734469485", 4) --> "0365065073456944"
Example of a string rotated to the left by one position:
s = "123456" gives "234561".

=======================================================================\

This is just a bit more clever than what I am doing, but honestly mine isn't that far off

 public static String revRot(String nums, int sz) {
        StringBuffer groups = new StringBuffer();
        for (int i = 0, len = nums.length(); i + sz <= len && sz > 0; i += sz) {
            String group = nums.substring(i, i + sz);
            groups.append(isDivisible(group) ? new StringBuffer(group).reverse() : group.substring(1) + group.charAt(0));
        }
        return groups.toString();  
    }
    
    public static boolean isDivisible(String group) {
        int sum = 0;
        for (char num : group.toCharArray()) {
            sum += Character.getNumericValue(num);
        }
        return sum % 2 == 0;
    } 
=======================================================================
*/

package java_folder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Six {
    public static String revRot(String strng, int sz) {
        // Initial sanity checks
        if (sz <= 0) return "";
        if (sz > strng.length()) return "";
        
        // Create a list to hold all of the chunks we are going to work with
        ArrayList<String> chunks = new ArrayList<>();
        for (int i = 0; i < strng.length(); i += sz) {
            // check if we are out of bounds or not
            if (i >= strng.length() || i + sz >= strng.length()) {
                break;
            }
            chunks.add(strng.substring(i, i + sz));
        }
        
        // Check the sum of the cubes of it's digits
        for (int i = 0; i < chunks.size(); i++) {
            int sum = 0;
            for (char s: chunks.get(i).toCharArray()) {
                sum += Math.pow(Integer.valueOf(s), 3); 
            }
            if (sum % 2 == 0) {
                chunks.set(i, new StringBuilder(chunks.get(i)).reverse().toString()); 
            } else {
                ArrayList<String> temp = new ArrayList(Arrays.asList(chunks.get(i).split("")));
                Collections.rotate(temp, -1);
                chunks.set(i, String.join("", temp));
            }
        }
        
        return String.join("", String.join("", chunks));
    }
    
    public static void main(String[] args) {
        System.out.println(revRot("733049910872815764", 5));
        System.out.println("ANSWER: 330479108928157");
    }
}
