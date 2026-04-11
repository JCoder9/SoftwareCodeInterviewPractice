/**
 * Monotonic Stack - Largest Rectangle in Histogram (LC 84)
 * 
 * Problem: Find largest rectangular area in histogram.
 *          Example: heights = [2,1,5,6,2,3] → 10 (width=2, height=5)
 * 
 * Pattern: For each bar, find previous/next smaller elements to determine width
 * 
 * Related LeetCode Problems:
 * - LC 84: Largest Rectangle in Histogram (Hard) ⭐⭐⭐
 * - LC 85: Maximal Rectangle (Hard)
 * - LC 1504: Count Submatrices With All Ones (Medium)
 * 
 * Time Complexity: O(n) - each bar pushed/popped once
 * Space Complexity: O(n) - stack storage
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force tries each bar as height, extends left/right —
//                  O(n²) worst case"
//   2. Problem:    "For n=10,000 bars: 100M operations to check all rectangles"
//   3. Transition: "Use monotonic stack to find prev/next smaller — O(n)"
//
// public int largestRectangleAreaNaive(int[] heights) {
//     int maxArea = 0;
//     int n = heights.length;
//     for (int i = 0; i < n; i++) {
//         int minHeight = heights[i];
//         for (int j = i; j < n; j++) {
//             minHeight = Math.min(minHeight, heights[j]);
//             int width = j - i + 1;
//             maxArea = Math.max(maxArea, minHeight * width);
//         }
//     }
//     return maxArea;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Stack;

public class LargestRectangle {
    /**
     * Monotonic Stack - Largest Rectangle in Histogram
     * Time: O(n), Space: O(n)
     */
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i < n; i++) {
            // Pop taller bars and calculate area
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int heightIdx = stack.pop();
                int height = heights[heightIdx];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        
        // Process remaining bars
        while (!stack.isEmpty()) {
            int heightIdx = stack.pop();
            int height = heights[heightIdx];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights)); // 10
    }
}
