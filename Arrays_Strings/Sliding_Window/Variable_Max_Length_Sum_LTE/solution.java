/**
 * Maximum Length Subarray with Sum <= S - Variable-Size Sliding Window
 * 
 * Problem: Find the maximum length of a contiguous subarray whose sum is <= S.
 * 
 * Pattern: Variable window - expand to include elements, shrink when invalid.
 * 
 * Time Complexity: O(n) - each element visited at most twice
 * Space Complexity: O(1) - only tracking window sum and pointers
 */

import java.util.Arrays;

public class Solution {
    
    /**
     * Find maximum length of subarray with sum <= S.
     * 
     * @param nums Input array (positive integers)
     * @param S Maximum sum threshold
     * @return Maximum length of valid subarray
     */
    public static int maxLenSumAtMostS(int[] nums, int S) {
        if (nums.length == 0) return 0;
        
        int left = 0;
        int windowSum = 0;
        int best = 0;  // Start at 0

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            // Shrink window while it's invalid (sum > S)
            while (windowSum > S) {
                windowSum -= nums[left];
                left++;
            }

            // Window is valid, update best
            best = Math.max(best, right - left + 1);
        }
        return best;  // No sentinel check needed
    }

    // Test cases
    public static void main(String[] args) {
        Object[][] testCases = {
            {new int[]{1, 2, 3, 4, 5}, 8, 3},
            {new int[]{5, 1, 1, 1, 1}, 6, 5},
            {new int[]{3, 1, 2, 1}, 4, 3},
            {new int[]{1, 1, 1}, 2, 2},
            {new int[]{10}, 5, 0}
        };
        
        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int S = (int) test[1];
            int expected = (int) test[2];
            
            int result = maxLenSumAtMostS(nums, S);
            String status = (result == expected) ? "✓" : "✗";
            System.out.println(status + " maxLenSumAtMostS(" + Arrays.toString(nums) + 
                             ", S=" + S + ") = " + result + " (expected " + expected + ")");
        }
    }
}
