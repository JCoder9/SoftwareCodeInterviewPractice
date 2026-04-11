/**
 * Minimum Window Substring - Hard Classic Sliding Window
 * 
 * Problem: Find the minimum substring of s that contains all characters of t
 *          (including their frequencies).
 * 
 * Pattern: Expand window until it "covers" t, then shrink to minimize while maintaining coverage.
 *          Track "formed" count to efficiently check if window is valid.
 * 
 * Time Complexity: O(|s| + |t|) - each character in s visited at most twice
 * Space Complexity: O(|s| + |t|) - space for both frequency maps
 */

import java.util.*;

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n² × m) time | O(m) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force checks every substring and validates if it contains
    //                   all chars from t — O(n²) substrings × O(m) to validate each"
    //   2. Problem:    "For large inputs (|s|=10⁴, |t|=100), this means ~10¹⁰ operations"
    //   3. Transition: "With a sliding window we expand until valid, then shrink to
    //                   minimize. Each char visited at most twice — drops to O(n + m)"
    //
    // public static String minWindowNaive(String s, String t) {
    //     if (t.length() > s.length()) return "";
    //     
    //     Map<Character, Integer> need = new HashMap<>();
    //     for (char c : t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
    //     
    //     String best = "";
    //     int bestLen = Integer.MAX_VALUE;
    //     
    //     for (int i = 0; i < s.length(); i++) {
    //         for (int j = i; j < s.length(); j++) {
    //             Map<Character, Integer> win = new HashMap<>();
    //             for (int k = i; k <= j; k++) {
    //                 char c = s.charAt(k);
    //                 win.put(c, win.getOrDefault(c, 0) + 1);
    //             }
    //             boolean valid = true;
    //             for (char c : need.keySet()) {
    //                 if (win.getOrDefault(c, 0) < need.get(c)) {
    //                     valid = false;
    //                     break;
    //                 }
    //             }
    //             if (valid && j - i + 1 < bestLen) {
    //                 bestLen = j - i + 1;
    //                 best = s.substring(i, j + 1);
    //             }
    //         }
    //     }
    //     return best;
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    /**
     * Find minimum window substring of s containing all characters of t.
     * 
     * @param s String to search in
     * @param t Pattern string - must find all these characters
     * @return Minimum window substring, or empty string if not found
     */
    public static String minWindow(String s, String t) {
        if (t.length() == 0 || s.length() == 0 || t.length() > s.length()) {
            return "";  // No window possible
        }

        // Build frequency map of what we need
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int required = need.size();
        Map<Character, Integer> window = new HashMap<>();
        int formed = 0;

        int left = 0;
        int bestLen = Integer.MAX_VALUE;
        int bestL = 0, bestR = 0;

        for (int right = 0; right < s.length(); right++) {
            // Expand window: add character from right
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // Check if this character now has required frequency
            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }

            // Try to shrink window while it's valid
            while (formed == required) {
                // Update best if current window is smaller
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestL = left;
                    bestR = right;
                }

                // Try to shrink from left
                char lc = s.charAt(left);
                window.put(lc, window.get(lc) - 1);
                if (need.containsKey(lc) && window.get(lc) < need.get(lc)) {
                    formed--;
                }
                left++;
            }
        }

        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestL, bestR + 1);
    }

    // Test cases
    public static void main(String[] args) {
        String[][] testCases = {
            {"ADOBECODEBANC", "ABC", "BANC"},
            {"a", "a", "a"},
            {"a", "aa", ""},
            {"ab", "b", "b"},
            {"abc", "cba", "abc"}
        };
        
        for (String[] test : testCases) {
            String s = test[0];
            String t = test[1];
            String expected = test[2];
            
            String result = minWindow(s, t);
            String status = result.equals(expected) ? "✓" : "✗";
            System.out.println(status + " minWindow(\"" + s + "\", \"" + t + 
                             "\") = \"" + result + "\" (expected \"" + expected + "\")");
        }
    }
}
