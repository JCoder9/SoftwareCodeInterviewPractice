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
