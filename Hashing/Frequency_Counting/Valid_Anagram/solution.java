/**
 * Valid Anagram - Basic Frequency Check
 * 
 * Problem: Given two strings s and t, return true if t is an anagram of s, false otherwise.
 *          An anagram uses the same characters in a different order.
 * 
 * Pattern: Build frequency count for both strings and compare.
 * 
 * Time Complexity: O(n) - single pass through both strings
 * Space Complexity: O(1) - fixed array of 26 for lowercase letters
 */

import java.util.*;

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n log n) time | O(n) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force sorts both strings and compares if they're equal
    //                   — O(n log n) time for sorting"
    //   2. Problem:    "Sorting is slower than needed; uses O(n) space for sorted copies"
    //   3. Transition: "With frequency counting we track character counts and compare
    //                   in one pass — drops to O(n) time with O(1) space"
    //
    // public static boolean isAnagramNaive(String s, String t) {
    //     if (s.length() != t.length()) return false;
    //     
    //     char[] sArr = s.toCharArray();
    //     char[] tArr = t.toCharArray();
    //     Arrays.sort(sArr);
    //     Arrays.sort(tArr);
    //     
    //     return Arrays.equals(sArr, tArr);
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] count = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        
        for (int c : count) {
            if (c != 0) return false;
        }
        
        return true;
    }
    
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        
        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            
            if (sToT.containsKey(c1)) {
                if (sToT.get(c1) != c2) return false;
            } else {
                sToT.put(c1, c2);
            }
            
            if (tToS.containsKey(c2)) {
                if (tToS.get(c2) != c1) return false;
            } else {
                tToS.put(c2, c1);
            }
        }
        
        return true;
    }
    
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        
        if (pattern.length() != words.length) return false;
        
        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            
            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word)) return false;
            } else {
                charToWord.put(c, word);
            }
            
            if (wordToChar.containsKey(word)) {
                if (wordToChar.get(word) != c) return false;
            } else {
                wordToChar.put(word, c);
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("Valid Anagram: " + isAnagram("anagram", "nagaram"));
        System.out.println("Isomorphic: " + isIsomorphic("egg", "add"));
        System.out.println("Word Pattern: " + wordPattern("abba", "dog cat cat dog"));
    }
}
