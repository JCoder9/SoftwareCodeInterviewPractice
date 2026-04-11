/**
 * Find All Anagrams in String - Fixed Window + Frequency Matching
 * 
 * Problem: Find all starting indices of p's anagrams in string s.
 * 
 * Pattern: Fixed window size (len(p)) with frequency array comparison.
 *          Window slides through s, checking if frequencies match.
 * 
 * Time Complexity: O(n × 26) = O(n) for lowercase letters
 * Space Complexity: O(1) - two fixed-size arrays of 26
 */

import java.util.*;

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n × m × 26) time | O(1) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force checks every substring of length m and compares
    //                   frequency arrays — O(n × m × 26) ≈ O(n × m)"
    //   2. Problem:    "For large inputs (n=10⁴, m=1000), this means ~10⁷ operations"
    //   3. Transition: "With a sliding window we maintain one frequency array and
    //                   update it incrementally as we slide — drops to O(n)"
    //
    // public static List<Integer> findAnagramsNaive(String s, String p) {
    //     List<Integer> res = new ArrayList<>();
    //     if (s.length() < p.length()) return res;
    //     
    //     int[] need = new int[26];
    //     for (char c : p.toCharArray()) need[c - 'a']++;
    //     
    //     for (int i = 0; i <= s.length() - p.length(); i++) {
    //         int[] win = new int[26];
    //         for (int j = i; j < i + p.length(); j++) {
    //             win[s.charAt(j) - 'a']++;
    //         }
    //         if (Arrays.equals(win, need)) res.add(i);
    //     }
    //     return res;
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    /**
     * Find all starting indices of p's anagrams in s.
     * 
     * @param s String to search in
     * @param p Pattern to find anagrams of
     * @return List of starting indices where anagrams are found
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0 || p.length() == 0 || p.length() > s.length()) {
            return res;  // No anagrams possible
        }

        // Frequency arrays for lowercase a-z
        int[] need = new int[26];
        int[] win = new int[26];

        // Build frequency array for pattern p
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }

        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            // Add new character to window
            win[s.charAt(right) - 'a']++;

            // Shrink window if it exceeds pattern length
            if (right - left + 1 > p.length()) {
                win[s.charAt(left) - 'a']--;
                left++;
            }

            // Check if we have an anagram (frequency arrays match)
            if (right - left + 1 == p.length() && Arrays.equals(win, need)) {
                res.add(left);
            }
        }
        return res;
    }

    // Test cases
    public static void main(String[] args) {
        Object[][] testCases = {
            {"cbaebabacd", "abc", new int[]{0, 6}},
            {"abab", "ab", new int[]{0, 1, 2}},
            {"baa", "aa", new int[]{1}},
            {"a", "a", new int[]{0}},
            {"abc", "xyz", new int[]{}}
        };
        
        for (Object[] test : testCases) {
            String s = (String) test[0];
            String p = (String) test[1];
            int[] expected = (int[]) test[2];
            
            List<Integer> result = findAnagrams(s, p);
            String status = Arrays.equals(result.stream().mapToInt(i->i).toArray(), expected) ? "✓" : "✗";
            System.out.println(status + " findAnagrams(\"" + s + "\", \"" + p + 
                             "\") = " + result + " (expected " + Arrays.toString(expected) + ")");
        }
    }
}
