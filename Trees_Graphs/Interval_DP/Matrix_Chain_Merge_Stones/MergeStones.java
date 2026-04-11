/**
 * Interval DP - Matrix Chain Multiplication / Merge Stones
 * 
 * Problem: Merge adjacent stone piles with cost = sum of merged stones.
 *          Example: stones = [3,4,3] → 17 (merge 3+4=7 cost 7, then 7+3=10 cost 10)
 * 
 * Pattern: Interval DP - try all split points k in [i, j-1]
 *          dp[i][j] = min cost to merge stones[i:j+1]
 * 
 * Related LeetCode Problems:
 * - LC 1000: Minimum Cost to Merge Stones (Hard) ⭐⭐⭐
 * - LC 312: Burst Balloons (Hard)
 * - LC 1039: Minimum Score Triangulation (Medium)
 * 
 * Formula: dp[i][j] = min(dp[i][k] + dp[k+1][j] + sum[i:j])
 * 
 * Time Complexity: O(n³) - 3 nested loops
 * Space Complexity: O(n²) - DP table
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force tries all ways to parenthesize n-1 merges — Catalan
//                  number = O(4^n / n^1.5) approaches"
//   2. Problem:    "For n=20: ~10^9 ways to merge; each requires O(n) to compute"
//   3. Transition: "Use interval DP to cache subproblems [i,j] — O(n³)"
//
// public int mergeStonesNaive(int[] stones) {
//     return helper(stones, 0, stones.length - 1);
// }
// 
// private int helper(int[] stones, int left, int right) {
//     if (left == right) return 0;
//     int minCost = Integer.MAX_VALUE;
//     // Try merging at each position
//     for (int k = left; k < right; k++) {
//         int cost = helper(stones, left, k) + helper(stones, k+1, right);
//         int sum = 0;
//         for (int i = left; i <= right; i++) sum += stones[i];
//         minCost = Math.min(minCost, cost + sum);
//     }
//     return minCost;
// }
// ─────────────────────────────────────────────────────────────────────────────

public class MergeStones {
    public int mergeStones(int[] stones) {
        int n = stones.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }
        int[][] dp = new int[n][n];
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + prefix[j + 1] - prefix[i];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][n - 1];
    }
}
