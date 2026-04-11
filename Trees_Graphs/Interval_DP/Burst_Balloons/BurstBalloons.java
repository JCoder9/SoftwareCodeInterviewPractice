/**
 * Interval DP - Burst Balloons (LC 312)
 * 
 * Problem: Burst balloons to maximize coins. coins = nums[left]*nums[i]*nums[right]
 *          Example: nums = [3,1,5,8] → 167
 * 
 * Key Insight: Think reverse - which balloon to burst LAST (not first)
 *              Add boundary balloons with value 1
 * 
 * Related LeetCode Problems:
 * - LC 312: Burst Balloons (Hard) ⭐⭐⭐
 * - LC 1039: Minimum Score Triangulation of Polygon (Medium)
 * - LC 664: Strange Printer (Hard)
 * 
 * Formula: dp[left][right] = max coins from bursting in (left, right) exclusive
 * 
 * Time Complexity: O(n³) - 3 nested loops
 * Space Complexity: O(n²) - DP table
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n!) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force tries all n! orderings of bursting balloons,
//                  simulates each order — O(n!)"
//   2. Problem:    "For n=10: 10! = 3.6M orderings; for n=15: 1.3 trillion"
//   3. Transition: "Think backwards: which balloon bursts LAST in interval? Use
//                  interval DP — O(n³)"
//
// // Conceptual - would need to generate all permutations
// public int maxCoinsNaive(int[] nums) {
//     // Try all n! orderings of bursting balloons
//     // For each ordering, simulate the bursting process
//     // Track maximum coins obtained
//     return maxCoins;
// }
// ─────────────────────────────────────────────────────────────────────────────

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int length = 2; length < n + 2; length++) {
            for (int left = 0; left < n + 2 - length; left++) {
                int right = left + length;
                for (int k = left + 1; k < right; k++) {
                    int coins = balloons[left] * balloons[k] * balloons[right];
                    int total = coins + dp[left][k] + dp[k][right];
                    dp[left][right] = Math.max(dp[left][right], total);
                }
            }
        }
        return dp[0][n + 1];
    }
}
