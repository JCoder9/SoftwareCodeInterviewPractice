/**
 * Remove Duplicates from Sorted Array - Same Direction Two Pointer Pattern
 * 
 * Problem: Remove duplicates from a sorted array in-place, keeping each unique value once.
 *          Return the new length.
 * 
 * Pattern: Slow pointer tracks write position, fast pointer scans array.
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
    //   1. Describe:   "Brute force creates a new array and only copies elements
    //                   that differ from previous — O(n) time, O(n) space"
    //   2. Problem:    "Uses extra space; can't do it in-place"
    //   3. Transition: "With two pointers (slow for write, fast for scanning) we
    //                   overwrite duplicates in-place — same O(n) time but O(1) space"
    //
    // public static int removeDuplicatesNaive(int[] nums) {
    //     if (nums.length == 0) return 0;
    //     
    //     int[] temp = new int[nums.length];
    //     temp[0] = nums[0];
    //     int idx = 1;
    //     for (int i = 1; i < nums.length; i++) {
    //         if (nums[i] != nums[i - 1]) {
    //             temp[idx++] = nums[i];
    //         }
    //     }
    //     // Copy back
    //     for (int i = 0; i < idx; i++) {
    //         nums[i] = temp[i];
    //     }
    //     return idx;
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    /**
     * Remove duplicates from sorted array in-place.
     * 
     * @param nums Sorted array (modified in-place)
     * @return New length of array with unique elements
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int slow = 1;  // Next write position (first element always stays)
        
        for (int fast = 1; fast < nums.length; fast++) {
            // If current element is different from previous, keep it
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        return slow;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // VARIANT 1: Return a new array  (O(n) space)
    // Asked as: "return the deduplicated array" / "don't modify the input"
    // ─────────────────────────────────────────────────────────────────────────
    public static int[] removeDuplicatesReturnNew(int[] nums) {
        if (nums.length == 0) return new int[0];

        // Count unique elements first to size the result array
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) count++;
        }

        int[] result = new int[count];
        result[0] = nums[0];
        int idx = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) result[idx++] = nums[i];
        }

        return result;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // VARIANT 2: In-place write, return trimmed array  (O(n) space for result)
    // Asked as: "return the modified array" — Java arrays are fixed-size so
    // Arrays.copyOf is required; the write logic itself stays O(1) extra space.
    // ─────────────────────────────────────────────────────────────────────────
    public static int[] removeDuplicatesInplaceReturnArray(int[] nums) {
        if (nums.length == 0) return nums;

        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        return Arrays.copyOf(nums, slow); // Trim to unique length
    }

    // Test cases
    public static void main(String[] args) {
        int[][][] testCases = {
            {{1, 1, 2}, {2}},
            {{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, {5}},
            {{1}, {1}},
            {{1, 2, 3}, {3}}
        };
        
        for (int[][] test : testCases) {
            int[] nums = test[0].clone();
            int expectedLen = test[1][0];
            int[] original = test[0].clone();
            
            int length = removeDuplicates(nums);
            int[] uniqueVals = Arrays.copyOf(nums, length);
            
            String status = (length == expectedLen) ? "✓" : "✗";
            System.out.println(status + " " + Arrays.toString(original) + 
                             " -> length=" + length + ", values=" + Arrays.toString(uniqueVals));
        }

        System.out.println("\n--- Variant 1: return new array ---");
        int[][] v1Cases = {{1, 1, 2}, {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, {1}, {1, 2, 3}};
        for (int[] nums : v1Cases) {
            int[] result = removeDuplicatesReturnNew(nums);
            System.out.println(Arrays.toString(nums) + " -> " + Arrays.toString(result));
        }

        System.out.println("\n--- Variant 2: in-place write, return trimmed array ---");
        for (int[] nums : v1Cases) {
            int[] result = removeDuplicatesInplaceReturnArray(nums.clone());
            System.out.println(Arrays.toString(nums) + " -> " + Arrays.toString(result));
        }
    }
}
