/*
Kata Link: https://www.codewars.com/kata/51e056fe544cf36c410000fb

Write a function that, given a string of text (possibly with punctuation and line-breaks), returns an array of the top-3 most occurring words, in descending order of the number of occurrences.

Assumptions:
A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
Matches should be case-insensitive, and the words in the result should be lowercased.
Ties may be broken arbitrarily.
If a text contains fewer than three unique words, then either the top-2 or top-1 words should be returned, or an empty array if a text contains no words.
Examples:
top_3_words("In a village of La Mancha, the name of which I have no desire to call to
mind, there lived not long since one of those gentlemen that keep a lance
in the lance-rack, an old buckler, a lean hack, and a greyhound for
coursing. An olla of rather more beef than mutton, a salad on most
nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
on Sundays, made away with three-quarters of his income.")
# => ["a", "of", "on"]

top_3_words("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e")
# => ["e", "ddd", "aa"]

top_3_words("  //wont won't won't")
# => ["won't", "wont"]
For java users, the calls will actually be in the form: TopWords.top3(String s), expecting you to return a List<String>.

Bonus points (not really, but just for fun):
Avoid creating an array whose memory footprint is roughly as big as the input text.
Avoid sorting the entire array of unique words.

=======================================================================
<Best solution goes here> 
=======================================================================
*/

package java_folder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// LEMME JUST SAY THAT REGEX CAN EAT ASS

public class Eight {
    public static List<String> top3(String s) {
        // First handle all of the illegal characters
        s = s.replaceAll("([^A-Za-z'\\s])([a-zA-Z]*'[a-zA-Z]*)('[a-zA-z]*')('[a-zA-Z]*')", "/");

        Map<String, Integer> wordToCountMap = new HashMap();
        for (String word: s.split("[\\s/\n\r\t]")) {
            if (word.equals("")) continue; 
            wordToCountMap.compute(word.toLowerCase(), (k, v) -> {
                return v == null ? 1 : v+1;
            });
        }
        List<String> wordList = new ArrayList<>();
        wordList.addAll(wordToCountMap.keySet());
        Collections.sort(wordList, (a, b) -> {
            return wordToCountMap.get(a) < wordToCountMap.get(b) ? 1 : -1;
        });
        System.out.println(Arrays.toString(wordList.toArray()));
        
        
        return wordList.size() > 2 ? wordList.subList(0, 3) : wordList.subList(0, wordList.size());
    }
    
    public static void main(String[] args) {
        System.out.println(top3(Stream.of("In a village of La Mancha, the name of which I have no desire to call to",
        "mind, there lived not long since one of those gentlemen that keep a lance",
        "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
        "coursing. An olla of rather more beef than mutton, a salad on most",
        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
        "on Sundays, made away with three-quarters of his income.").collect(Collectors.joining("\n"))));
    }
}
// The kata I want to do : https://www.codewars.com/kata/51e056fe544cf36c410000fb