/*
Kata Link: https://www.codewars.com/kata/569d488d61b812a0f7000015

A stream of data is received and needs to be reversed.

Each segment is 8 bits long, meaning the order of these segments needs to be reversed, for example:

11111111  00000000  00001111  10101010
 (byte1)   (byte2)   (byte3)   (byte4)
should become:

10101010  00001111  00000000  11111111
 (byte4)   (byte3)   (byte2)   (byte1)
The total number of bits will always be a multiple of 8.

The data is given in an array as such:

[1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,0]

===========================================================================================
public static int[] DataReverse(int[] data) {
    int bytes[] = new int[data.length];
    for (int i = data.length-8, j=0; i>=0; i-=8, j+=8) {
      System.arraycopy(data, i, bytes, j, 8);
    }
    return bytes;
}
===========================================================================================
*/

package java_folder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Five {
    public static int[] dataReverse(int[] data) {
        // Add each section to a list to be reversed
        ArrayList<ArrayList<Integer>> bitList = new ArrayList();
        for (int i = 0; i < data.length / 8; i++) {
            ArrayList<Integer> bit = new ArrayList();
            for (int j = i * 8; j < (i * 8) + 8; j++) {
                bit.add(data[j]);
            }
            bitList.add(bit);
        }
        
        // Reverse bitList
        System.out.println(Arrays.toString(bitList.toArray()));
        Collections.reverse(bitList);
        System.out.println(Arrays.toString(bitList.toArray()));

        // Now we have to go from ArrayList<ArrayList> to int[] 
        int[] toReturn = new int[data.length];
        int index = 0;
        for (ArrayList<Integer> chunk: bitList) {
            for (Integer bit: chunk) {
                toReturn[index] = bit;
                index++;
            }
        }

        return toReturn;
    }
    
    public static void main(String[] args) {
        int[] data1= {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,0};
        System.out.println(Arrays.toString(dataReverse(data1)));
    }
}
