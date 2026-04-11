/**
 * Merge Two Sorted Arrays - Two Pointer Pattern Across Arrays
 * 
 * Problem: Merge two sorted arrays into a single sorted array.
 * 
 * Pattern: One pointer for each array, compare and merge.
 * 
 * Time Complexity: O(m + n) - single pass through both arrays
 * Space Complexity: O(m + n) - for output array
 */

import java.util.Arrays;

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O((m+n) log(m+n)) time | O(m+n) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force combines both arrays and sorts the result
    //                   — O((m+n) log(m+n)) time"
    //   2. Problem:    "Doesn't use the fact that inputs are already sorted;
    //                   wastes time re-sorting"
    //   3. Transition: "With two pointers (one per array) we merge in one pass,
    //                   picking the smaller element each time — drops to O(m+n)"
    //
    // public static int[] mergeSortedNaive(int[] a, int[] b) {
    //     int[] result = new int[a.length + b.length];
    //     int idx = 0;
    //     for (int x : a) result[idx++] = x;
    //     for (int x : b) result[idx++] = x;
    //     Arrays.sort(result);
    //     return result;
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    /**
     * Merge two sorted arrays into a single sorted array.
     * 
     * @param a First sorted array
     * @param b Second sorted array
     * @return Merged sorted array containing all elements from both arrays
     */
    public static int[] mergeSorted(int[] a, int[] b) {
        int i = 0, j = 0;
        int[] out = new int[a.length + b.length];
        int k = 0;

        // Merge elements while both arrays have remaining elements
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                out[k++] = a[i++];
            } else {
                out[k++] = b[j++];
            }
        }
        
        // Append remaining elements from a (if any)
        while (i < a.length) {
            out[k++] = a[i++];
        }
        
        // Append remaining elements from b (if any)
        while (j < b.length) {
            out[k++] = b[j++];
        }

        return out;
    }

    // Test cases
    public static void main(String[] args) {
        int[][][] testCases = {
            {{1, 3, 5}, {2, 4, 6}},
            {{1, 2, 3}, {4, 5, 6}},
            {{}, {1, 2, 3}},
            {{1, 2, 3}, {}},
            {{1}, {2}}
        };
        
        for (int[][] test : testCases) {
            int[] a = test[0];
            int[] b = test[1];
            int[] result = mergeSorted(a, b);
            System.out.println("merge_sorted(" + Arrays.toString(a) + ", " + 
                             Arrays.toString(b) + ") = " + Arrays.toString(result));
        }
    }
}
