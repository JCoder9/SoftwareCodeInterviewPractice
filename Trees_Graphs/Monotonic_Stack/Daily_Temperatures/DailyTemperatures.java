/**
 * Monotonic Stack - Daily Temperatures (LC 739)
 * 
 * Problem: Find days until next warmer temperature for each day.
 *          Example: temps = [73,74,75,71,69,72,76,73] → [1,1,4,2,1,1,0,0]
 * 
 * Pattern: Monotonic decreasing stack tracks indices of previous cooler days
 * 
 * Related LeetCode Problems:
 * - LC 739: Daily Temperatures (Medium) ⭐⭐⭐
 * - LC 901: Online Stock Span (Medium)
 * - LC 1019: Next Greater Node In Linked List (Medium)
 * 
 * Time Complexity: O(n) - each element pushed/popped once
 * Space Complexity: O(n) - stack storage
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force scans ahead from each day to find next warmer —
//                  O(n²) worst case"
//   2. Problem:    "For n=1000 with decreasing temps: 500K comparisons"
//   3. Transition: "Use monotonic stack to track pending days in one pass — O(n)"
//
// public int[] dailyTemperaturesNaive(int[] temperatures) {
//     int n = temperatures.length;
//     int[] result = new int[n];
//     for (int i = 0; i < n; i++) {
//         for (int j = i + 1; j < n; j++) {
//             if (temperatures[j] > temperatures[i]) {
//                 result[i] = j - i;
//                 break;
//             }
//         }
//     }
//     return result;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {
    /**
     * Monotonic Stack - Daily Temperatures
     * Time: O(n), Space: O(n)
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // Found warmer day
            while (!stack.isEmpty() && 
                   temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                result[prevDay] = i - prevDay;
            }
            stack.push(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temps)));
        // [1, 1, 4, 2, 1, 1, 0, 0]
    }
}
