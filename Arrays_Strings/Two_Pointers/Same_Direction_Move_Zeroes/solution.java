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

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n) time | O(n) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force creates a new array, copies non-zeros first,
    //                   then fills rest with zeros — O(n) time, O(n) space"
    //   2. Problem:    "Uses extra space; can't do it in-place"
    //   3. Transition: "With two pointers (slow for write position, fast for scanning)
    //                   we write non-zeros in-place — same O(n) time but O(1) space"
    //
    // public static void moveZeroesNaive(int[] nums) {
    //     int[] temp = new int[nums.length];
    //     int idx = 0;
    //     // Copy non-zeros
    //     for (int num : nums) {
    //         if (num != 0) temp[idx++] = num;
    //     }
    //     // Rest are already zeros (array initialized to 0)
    //     for (int i = 0; i < nums.length; i++) {
    //         nums[i] = temp[i];
    //     }
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
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
