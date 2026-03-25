/**
 * Maximum Sum of Subarray Size K - Fixed-Size Sliding Window
 * 
 * Problem: Find the maximum sum of any subarray of length k.
 * 
 * Pattern: Fixed window size - add new element, remove old element when window exceeds k.
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - only tracking window sum
 */

import java.util.Arrays;

public class Solution {
    
    /**
     * Find maximum sum of any subarray of length k.
     * 
     * @param nums Input array
     * @param k Window size
     * @return Maximum sum, or null if no valid window possible
     */
    public static Integer maxSumK(int[] nums, int k) {
        if (nums.length < k || k <= 0) {
            return null;  // No valid window possible
        }
        
        int left = 0;
        int windowSum = 0;
        int best = Integer.MIN_VALUE;  // Safe now

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            // Shrink window if it exceeds size k
            if (right - left + 1 > k) {
                windowSum -= nums[left];
                left++;
            }

            // Update best when window is exactly size k
            if (right - left + 1 == k) {
                best = Math.max(best, windowSum);
            }
        }
        return best;
    }

    // Test cases
    public static void main(String[] args) {
        Object[][] testCases = {
            {new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 4, 39},
            {new int[]{2, 3}, 3, null},
            {new int[]{1, -1, 5, -2, 3}, 2, 4},
            {new int[]{1, -1, 5, -2, 3}, 3, 6},
            {new int[]{5}, 1, 5}
        };
        
        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int k = (int) test[1];
            Integer expected = (Integer) test[2];
            
            Integer result = maxSumK(nums, k);
            String status = (result == null && expected == null) || 
                          (result != null && result.equals(expected)) ? "✓" : "✗";
            System.out.println(status + " maxSumK(" + Arrays.toString(nums) + 
                             ", k=" + k + ") = " + result + " (expected " + expected + ")");
        }
    }
}
