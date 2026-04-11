/**
 * Longest Substring with At Most K Distinct Characters - Frequency Map Sliding Window
 * 
 * Problem: Find the length of the longest substring with at most K distinct characters.
 * 
 * Pattern: Variable window with frequency map - shrink when distinct count exceeds K.
 * 
 * Time Complexity: O(n) - each character visited at most twice
 * Space Complexity: O(min(k, alphabet)) - at most k+1 chars in map before shrinking
 */

import java.util.*;

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n²) time | O(k) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force checks every substring and counts distinct chars — O(n²)"
    //   2. Problem:    "Too slow for large inputs; n=10⁴ means 10⁸ operations"
    //   3. Transition: "With a sliding window we maintain a frequency map and shrink
    //                   from the left when distinct chars exceed k — drops to O(n)"
    //
    // public static int longestAtMostKDistinctNaive(String s, int k) {
    //     int best = 0;
    //     for (int i = 0; i < s.length(); i++) {
    //         Set<Character> seen = new HashSet<>(); //added here so that left char is removed when we move to next starting point
    //         for (int j = i; j < s.length(); j++) {
    //             seen.add(s.charAt(j));
    //             if (seen.size() <= k) best = Math.max(best, j - i + 1);
    //             else break; // adding more chars won't help this window
    //         }
    //     }
    //     return best;
    // }
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Find longest substring with at most K distinct characters.
     *
     * @param s Input string
     * @param k Maximum number of distinct characters allowed
     * @return Length of longest valid substring
     */
    public static int longestAtMostKDistinct(String s, int k) {
        if (s.length() == 0 || k <= 0) return 0;

        Map<Character, Integer> count = new HashMap<>();
        int left = 0;
        int best = 0;  // Start at 0

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            count.put(ch, count.getOrDefault(ch, 0) + 1);

            // Shrink window while we have too many distinct characters
            while (count.size() > k) {
                char lc = s.charAt(left);
                count.put(lc, count.get(lc) - 1);
                if (count.get(lc) == 0) {
                    count.remove(lc);
                }
                left++;
            }

            // Window is valid, update best
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // Test cases
    public static void main(String[] args) {
        Object[][] testCases = {
            {"eceba", 2, 3},
            {"aa", 1, 2},
            {"a", 2, 1},
            {"abcabc", 2, 2},
            {"abcabcabc", 3, 9}
        };
        
        for (Object[] test : testCases) {
            String s = (String) test[0];
            int k = (int) test[1];
            int expected = (int) test[2];
            
            int result = longestAtMostKDistinct(s, k);
            String status = (result == expected) ? "✓" : "✗";
            System.out.println(status + " longestAtMostKDistinct(\"" + s + 
                             "\", k=" + k + ") = " + result + " (expected " + expected + ")");
        }
    }
}
