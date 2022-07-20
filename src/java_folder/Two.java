// Kata link: https://www.codewars.com/kata/5467e4d82edf8bbf40000155
/*
Your task is to make a function that can take any non-negative integer as an argument and return it with its digits in descending order. Essentially, rearrange the digits to create the highest possible number.

Examples:
Input: 42145 Output: 54421

Input: 145263 Output: 654321

Input: 123456789 Output: 987654321

========================================================================================================================

This was the best solution. I pretty much did this the first time but couldn't figure out how to get everything to work.

public static int sortDesc(final int num) {
        return Integer.parseInt(String.valueOf(num)
                                      .chars()
                                      .mapToObj(i -> String.valueOf(Character.getNumericValue(i)))
                                      .sorted(Comparator.reverseOrder())
                                      .collect(Collectors.joining()));
}
========================================================================================================================

*/

package java_folder;

import java.util.ArrayList;
import java.util.Collections;

public class Two {
        
    public static int sortDesc(final int num) {
        // Sanity check first
        if (num < 0 || num == 0) return 0;
        
        // Add the digits to a list
        ArrayList<Integer> digits = new ArrayList();
        int copy = num;
        while (copy != 0) {
            digits.add(Integer.valueOf(copy % 10));
            copy /= 10;
        }
        
        // Sort the list
        digits.sort(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (Integer i: digits) {
            sb.append(i);
        }
        
        // Return an int based on the sorted list of Integers
        return Integer.parseInt(sb.toString());
    }
    
    public static void main(String[] args) {
        System.out.println(sortDesc(42145));
    }
}

