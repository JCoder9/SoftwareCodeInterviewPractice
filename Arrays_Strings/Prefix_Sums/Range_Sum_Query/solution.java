/**
 * Range Sum Query - Prefix Sum Pattern
 * 
 * Problem: Given an array, answer multiple queries for sum of elements in range [left, right].
 * 
 * Pattern: Build prefix sum array where prefix[i] = sum of nums[0...i-1].
 *          Then range_sum(left, right) = prefix[right+1] - prefix[left]
 * 
 * Time Complexity: O(n) preprocessing, O(1) per query
 * Space Complexity: O(n) for prefix array
 */

import java.util.*;

// ─────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) - O(q × n) time | O(1) space
//   where q = number of queries, n = array length
// ─────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force loops through the range for each query to calculate
//                   the sum — O(n) per query, O(q × n) for q queries"
//   2. Problem:    "For 10⁴ queries on array of 10⁴ elements, we'd do ~10⁸ operations"
//   3. Transition: "With prefix sums we precompute cumulative sums once in O(n),
//                   then answer each query in O(1) — total O(n + q)"
//
// class NumArrayNaive {
//     private int[] nums;
//     
//     public NumArrayNaive(int[] nums) {
//         this.nums = nums;
//     }
//     
//     public int sumRange(int left, int right) {
//         int sum = 0;
//         for (int i = left; i <= right; i++) {
//             sum += nums[i];
//         }
//         return sum;
//     }
// }
// ─────────────────────────────────────────────────────────────────────────

class NumArray {
    private int[] prefix;
    
    /**
     * Build prefix sum array.
     * prefix[i] = sum of nums[0] to nums[i-1]
     */
    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];  // +1 to handle prefix[0] = 0
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }
    
    /**
     * Return sum of elements from index left to right (inclusive).
     * 
     * Visual Example:
     * nums = [3, 1, 4, 2, 5]
     * prefix = [0, 3, 4, 8, 10, 15]
     * 
     * sumRange(1, 3) = sum of [1, 4, 2]
     *   = prefix[4] - prefix[1]
     *   = 10 - 3
     *   = 7
     */
    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}

public class Solution {
    public static void main(String[] args) {
        System.out.println("=== Range Sum Query (LC 303) ===");
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        
        int[][] queries = {{0, 2}, {2, 5}, {0, 5}};
        for (int[] q : queries) {
            int result = obj.sumRange(q[0], q[1]);
            System.out.println("sumRange(" + q[0] + ", " + q[1] + ") = " + result);
        }
        
        // Visual breakdown for query [1, 3]:
        // nums    = [-2, 0, 3, -5, 2, -1]
        // prefix  = [0, -2, -2, 1, -4, -2, -3]
        // sumRange(2, 5) = prefix[6] - prefix[2] = -3 - (-2) = -1
    }
}
