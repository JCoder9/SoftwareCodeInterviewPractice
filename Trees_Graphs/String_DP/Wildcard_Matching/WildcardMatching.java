/**
 * Wildcard Matching (LC 44)
 * 
 * Problem: Match string with pattern containing ? (any char) and * (any sequence).
 *          Example: s = "adceb", p = "*a*b" → True
 * 
 * Pattern: 2D DP where dp[i][j] = s[:i] matches p[:j]
 * 
 * Related LeetCode Problems:
 * - LC 44: Wildcard Matching (Hard) ⭐⭐⭐
 * - LC 10: Regular Expression Matching (Hard)
 * - LC 1023: Camelcase Matching (Medium)
 * 
 * Time Complexity: O(m × n) - fill DP table
 * Space Complexity: O(m × n) - DP table
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(2^n) time | O(m+n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force recursively tries both branches for '*' — O(2^n)"
//   2. Problem:    "For pattern with 10 stars: 2^10 = 1024+ recursive calls"
//   3. Transition: "Use 2D DP to cache (s_idx, p_idx) results — O(m×n)"
//
// public boolean isMatchNaive(String s, String p) {
//     return helper(s, p, 0, 0);
// }
// private boolean helper(String s, String p, int i, int j) {
//     if (j == p.length()) return i == s.length();
//     if (i == s.length()) {
//         for (int k = j; k < p.length(); k++)
//             if (p.charAt(k) != '*') return false;
//         return true;
//     }
//     if (p.charAt(j) == '*') {
//         return helper(s, p, i, j+1) || helper(s, p, i+1, j);
//     } else if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) {
//         return helper(s, p, i+1, j+1);
//     }
//     return false;
// }
// ─────────────────────────────────────────────────────────────────────────────

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-1];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                } else if (p.charAt(j-1) == '?' || s.charAt(i-1) == p.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
