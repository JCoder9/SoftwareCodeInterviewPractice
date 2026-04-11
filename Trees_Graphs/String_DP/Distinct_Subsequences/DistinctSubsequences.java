/**
 * Distinct Subsequences (LC 115)
 * 
 * Problem: Count distinct subsequences of s that equal t.
 *          Example: s = "rabbbit", t = "rabbit" → 3 (rab_bit, ra_bbit, rabb_it)
 * 
 * Pattern: 2D DP where dp[i][j] = # ways t[:j] appears in s[:i]
 * 
 * Related LeetCode Problems:
 * - LC 115: Distinct Subsequences (Hard) ⭐⭐⭐
 * - LC 940: Distinct Subsequences II (Hard)
 * - LC 792: Number of Matching Subsequences (Medium)
 * 
 * Time Complexity: O(m × n) - fill DP table
 * Space Complexity: O(m × n) - DP table
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(2^m) time | O(m) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force recursively tries including/excluding chars — O(2^m)"
//   2. Problem:    "For m=30: 2^30 = 1 billion recursive calls"
//   3. Transition: "Use 2D DP to cache (s_idx, t_idx) results — O(m×n)"
//
// public int numDistinctNaive(String s, String t) {
//     return helper(s, t, 0, 0);
// }
// private int helper(String s, String t, int i, int j) {
//     if (j == t.length()) return 1;
//     if (i == s.length()) return 0;
//     int count = helper(s, t, i+1, j);
//     if (s.charAt(i) == t.charAt(j)) {
//         count += helper(s, t, i+1, j+1);
//     }
//     return count;
// }
// ─────────────────────────────────────────────────────────────────────────────

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i-1][j];
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] += dp[i-1][j-1];
                }
            }
        }
        return (int) dp[m][n];
    }
}
