/**
 * Monotonic Stack - Next Greater Element
 * 
 * Problem: For each element in array, find the next greater element to its right.
 *          Return -1 if no greater element exists.
 * 
 * Pattern: Maintain a decreasing monotonic stack (stores indices). When we find a larger
 *          element, it's the "next greater" for all smaller elements we pop.
 * 
 * Related LeetCode Problems:
 * - LC 496: Next Greater Element I (Easy) ⭐⭐
 * - LC 503: Next Greater Element II (Medium) - circular array
 * - LC 739: Daily Temperatures (Medium) ⭐⭐⭐
 * - LC 42: Trapping Rain Water (Hard) ⭐⭐⭐
 * 
 * Time Complexity: O(n) - each element pushed/popped once
 * Space Complexity: O(n) - stack storage
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force: for each element, scan right until we find a larger
//                  element — nested loops give O(n²)"
//   2. Problem:    "For n=10,000: 50 million comparisons worst case (decreasing array);
//                  very slow"
//   3. Transition: "Monotonic stack tracks pending elements in one pass; when we find
//                  next greater, pop all smaller — O(n) total"
//
// public static int[] nextGreaterElementNaive(int[] nums) {
//     int n = nums.length;
//     int[] result = new int[n];
//     
//     for (int i = 0; i < n; i++) {
//         result[i] = -1;
//         for (int j = i + 1; j < n; j++) {
//             if (nums[j] > nums[i]) {
//                 result[i] = nums[j];
//                 break;
//             }
//         }
//     }
//     return result;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.Stack;

public class MonotonicStack {
    
    /**
     * Monotonic Stack - Next Greater Element (Optimized)
     * Time: O(n), Space: O(n)
     */
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Initialize result with -1
        Arrays.fill(result, -1);
        
        for (int i = 0; i < n; i++) {
            // Pop smaller elements - nums[i] is their next greater
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = nums[i];
            }
            stack.push(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        int[] result = nextGreaterElement(nums);
        System.out.println(Arrays.toString(result));  // [4, 2, 4, -1, -1]
    }
}
