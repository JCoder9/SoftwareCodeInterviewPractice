/**
 * Frequency Counting and Anagrams - Hash Map Pattern
 * 
 * Problem: Use hash maps to count character/element frequencies for comparison.
 * 
 * Pattern: Build frequency maps and compare them (for anagrams, permutations, etc.)
 * 
 * Time Complexity: O(n) for building frequency map
 * Space Complexity: O(k) where k is unique elements
 */

import java.util.*;

public class Solution {
    
    /**
     * Check if two strings are anagrams.
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        Map<Character, Integer> count = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        for (char c : t.toCharArray()) {
            if (!count.containsKey(c)) return false;
            count.put(c, count.get(c) - 1);
            if (count.get(c) < 0) return false;
        }
        
        return true;
    }
    
    /**
     * Group strings that are anagrams of each other.
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        
        for (String s : strs) {
            // Sort string to create key
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            groups.putIfAbsent(key, new ArrayList<>());
            groups.get(key).add(s);
        }
        
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        System.out.println("Is Anagram:");
        System.out.println("  " + isAnagram("anagram", "nagaram"));
        System.out.println("  " + isAnagram("rat", "car"));
        
        System.out.println("\nGroup Anagrams:");
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groups = groupAnagrams(strs);
        System.out.println("  " + groups);
    }
}
