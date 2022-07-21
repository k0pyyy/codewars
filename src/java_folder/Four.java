/*
Kata Link: https://www.codewars.com/kata/55b3425df71c1201a800009c

You are the "computer expert" of a local Athletic Association (C.A.A.). Many teams of runners come to compete. Each time you get a string of all race results of every team who has run. For example here is a string showing the individual results of a team of 5 runners:

"01|15|59, 1|47|6, 01|17|20, 1|32|34, 2|3|17"

Each part of the string is of the form: h|m|s where h, m, s (h for hour, m for minutes, s for seconds) are positive or null integer (represented as strings) with one or two digits. Substrings in the input string are separated by ,  or ,.

To compare the results of the teams you are asked for giving three statistics; range, average and median.

Range : difference between the lowest and highest values. In {4, 6, 9, 3, 7} the lowest value is 3, and the highest is 9, so the range is 9 − 3 = 6.

Mean or Average : To calculate mean, add together all of the numbers in a set and then divide the sum by the total count of numbers.

Median : In statistics, the median is the number separating the higher half of a data sample from the lower half. The median of a finite list of numbers can be found by arranging all the observations from lowest value to highest value and picking the middle one (e.g., the median of {3, 3, 5, 9, 11} is 5) when there is an odd number of observations. If there is an even number of observations, then there is no single middle value; the median is then defined to be the mean of the two middle values (the median of {3, 5, 6, 9} is (5 + 6) / 2 = 5.5).

Your task is to return a string giving these 3 values. For the example given above, the string result will be

"Range: 00|47|18 Average: 01|35|15 Median: 01|32|34"

of the form: "Range: hh|mm|ss Average: hh|mm|ss Median: hh|mm|ss"`

where hh, mm, ss are integers (represented by strings) with each 2 digits.

Remarks:
if a result in seconds is ab.xy... it will be given truncated as ab.
if the given string is "" you will return ""


========================================================================================================================
I actually like my solution better than the ones that were submitted for this one, which is great.
========================================================================================================================

*/

package java_folder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class Four {
    
    public static String stat(String strg) {
        // Sanity check
        if (strg == "") return "";
        
        // Store the values converted into seconds into a list
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (String s: strg.split(",")) {
            String[] runnerStat = s.strip().split(Pattern.quote("|")); // this should give 3 values which are the H, M, S 
            list.add((Integer.valueOf(runnerStat[0]) * 3600) + (Integer.valueOf(runnerStat[1]) * 60) + (Integer.valueOf(runnerStat[2])));
        }
        // quickly sort the list for median value later
        Collections.sort(list);
        
        // now run the stats on the values that we have calculated
        long range = Collections.max(list) - Collections.min(list);
        long mean = list.stream().mapToInt(Integer::intValue).sum() / list.size();

        // This line will either take the middle value (if the size of the list is odd), or take the average of the two middle values (if the list size is even)
        long median = list.size() % 2 == 0 ? ((list.get(list.size() / 2) + list.get((list.size() / 2) - 1)) / 2)  : list.get(list.size() / 2);
        
        return String.format("Range: %s Average: %s Median: %s", longToStatline(range), longToStatline(mean), longToStatline(median));
    }
    
    public static String longToStatline(long l) {       
        return String.format("%02d|%02d|%02d", l / 3600, l % 3600 / 60, l % 3600 % 60);
    }
    
    public static void main(String[] args) {
        System.out.println(stat("01|15|59, 1|47|16, 01|17|20, 1|32|34, 2|17|17"));
        System.out.println(stat("02|15|59, 2|47|16, 02|17|20, 2|32|34, 2|32|34, 2|17|17"));
    }
}
