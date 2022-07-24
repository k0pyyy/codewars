/*
Kata Link: https://www.codewars.com/kata/51ba717bb08c1cd60f00002f

A format for expressing an ordered list of integers is to use a comma separated list of either:

- individual integers
- or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. 
The range includes all integers in the interval including both endpoints. 
It is not considered a range unless it spans at least 3 numbers. For example "12,13,15-17"

Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

Example:

Solution.rangeExtraction(new int[] {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
# returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20"

=======================================================================

Alright I worked through this one on paper and this is a great solution. The index skipping ahead to see if we have a range 
at the start of every new number is really good. This makes me solution look terrible, but that's alright because I might
be able to use something like this.

public static String rangeExtraction(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            int j = i;
            while (j < arr.length - 1 && arr[j] + 1 == arr[j + 1]) j++;
            if (i + 1 < j) {
                i = j;
                sb.append("-");
                sb.append(arr[i]);
            }
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
}
=======================================================================
*/

package java_folder;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Seven {
  
    public static String rangeExtraction(int[] arr) {
        // we can assume the array is sorted, so just go through it 
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> currentSegment = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            // Alright, we iterate through the array now
            if (currentSegment.size() == 0) {
                if (i == arr.length - 1) {
                    // we are done, and need to print this value at the end
                    sb.append(String.valueOf(arr[i]));
                    break;
                }
                
                // add the current value to the working space
                currentSegment.add(arr[i]);
                
            } else {
                // Check to see if this value is the number that comes after the previous
                if (arr[i] == currentSegment.get(currentSegment.size() - 1) + 1) {
                    currentSegment.add(arr[i]);
                    
                    if (i == arr.length - 1) {
                        // we are done with this segment, and we need to add the currentSegment to the output, and start a new segment
                        if (currentSegment.size() >= 3) {
                            // add the first and last values, separated by a -
                            sb.append(currentSegment.get(0) + "-" + currentSegment.get(currentSegment.size() - 1));
                        } else {
                            sb.append(currentSegment.stream().map(Object::toString).collect(Collectors.joining(",")));
                        }
                    }
                } else if (arr[i] != currentSegment.get(currentSegment.size() - 1) + 1) {
                    String addOn = i == arr.length - 1 ? "" : ",";
                    
                    // we are done with this segment, and we need to add the currentSegment to the output, and start a new segment
                    if (currentSegment.size() >= 3) {
                        // add the first and last values, separated by a -
                        sb.append(currentSegment.get(0) + "-" + currentSegment.get(currentSegment.size() - 1) + addOn);
                    } else {
                        sb.append(currentSegment.stream().map(Object::toString).collect(Collectors.joining(",")) + addOn);
                    }
                    
                    if (i == arr.length - 1) {
                        // also append this last value to the end of the string
                        sb.append("," + arr[i]);
                    } else {
                        // create a new currentSegment and add this new value at i
                        currentSegment.clear();
                        currentSegment.add(arr[i]);
                    }
                }
            }
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("ANSWER: " + rangeExtraction(new int[] {-6,-3,-2,-1,0,1,3,4,5,7,8,9,10,11,14,15,17,18,19,20}));
        System.out.println("SOLUTION: -6,-3-1,3-5,7-11,14,15,17-20");
    }
}
