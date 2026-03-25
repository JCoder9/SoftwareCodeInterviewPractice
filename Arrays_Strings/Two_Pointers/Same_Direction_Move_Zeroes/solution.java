/**
 * Move Zeroes to End - Same Direction Two Pointer Pattern
 * 
 * Problem: Move all zeros to the end of array while maintaining relative order
 *          of non-zero elements.
 * 
 * Pattern: Slow pointer tracks write position for non-zeros, fast pointer scans array.
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - in-place modification
 */

import java.util.Arrays;

public class Solution {
    
    /**
     * Move all zeros to end of array, maintaining order of non-zeros.
     * 
     * @param nums Array to modify (modified in-place)
     */
    public static void moveZeroes(int[] nums) {
        int slow = 0;  // Next position to write non-zero element
        
        // First pass: move all non-zeros to front
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        // Second pass: fill rest with zeros
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // Test cases
    public static void main(String[] args) {
        int[][] testCases = {
            {0, 1, 0, 3, 12},
            {0},
            {1, 2, 3},
            {0, 0, 1},
            {1, 0, 0, 2, 0, 3}
        };
        
        for (int[] nums : testCases) {
            int[] original = nums.clone();
            moveZeroes(nums);
            System.out.println(Arrays.toString(original) + " -> " + Arrays.toString(nums));
        }
    }
}
