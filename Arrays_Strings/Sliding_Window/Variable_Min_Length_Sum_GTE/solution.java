/**
 * Minimum Length Subarray with Sum >= S - Variable-Size Sliding Window
 * 
 * Problem: Find the minimum length of a contiguous subarray whose sum is >= S.
 * 
 * Pattern: Variable window - expand to include elements, shrink when valid to minimize.
 * 
 * Time Complexity: O(n) - each element visited at most twice
 * Space Complexity: O(1) - only tracking window sum and pointers
 */

import java.util.Arrays;

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force checks every subarray and calculates its sum
    //                   — O(n²) time with O(1) space"
    //   2. Problem:    "For n=10⁴, we'd do ~10⁸ operations; too slow for large inputs"
    //   3. Transition: "With a sliding window we maintain the sum and shrink when valid
    //                   (sum >= S) to minimize. Each element visited twice — drops to O(n)"
    //
    // public static int minLenSumAtLeastSNaive(int[] nums, int S) {
    //     int best = Integer.MAX_VALUE;
    //     for (int i = 0; i < nums.length; i++) {
    //         int sum = 0;
    //         for (int j = i; j < nums.length; j++) {
    //             sum += nums[j];
    //             if (sum >= S) {
    //                 best = Math.min(best, j - i + 1);
    //                 break; // no need to extend further from this starting point
    //             }
    //         }
    //     }
    //     return best == Integer.MAX_VALUE ? 0 : best;
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    /**
     * Find minimum length of subarray with sum >= S.
     * 
     * @param nums Input array (positive integers)
     * @param S Target sum threshold
     * @return Minimum length, or 0 if no such subarray exists
     */
    public static int minLenSumAtLeastS(int[] nums, int S) {
        if (nums.length == 0) return 0;
        
        int left = 0;
        int windowSum = 0;
        int best = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            // Shrink window while it's valid (sum >= S)
            while (windowSum >= S) {
                best = Math.min(best, right - left + 1);
                windowSum -= nums[left];
                left++;
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;  // Check sentinel
    }

    // Test cases
    public static void main(String[] args) {
        Object[][] testCases = {
            {new int[]{2, 3, 1, 2, 4, 3}, 7, 2},
            {new int[]{1, 4, 4}, 4, 1},
            {new int[]{1, 1, 1, 1, 1}, 11, 0},
            {new int[]{1, 2, 3, 4, 5}, 11, 3},
            {new int[]{5, 1, 3, 5, 10, 7}, 15, 2}
        };
        
        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int S = (int) test[1];
            int expected = (int) test[2];
            
            int result = minLenSumAtLeastS(nums, S);
            String status = (result == expected) ? "✓" : "✗";
            System.out.println(status + " minLenSumAtLeastS(" + Arrays.toString(nums) + 
                             ", S=" + S + ") = " + result + " (expected " + expected + ")");
        }
    }
}
