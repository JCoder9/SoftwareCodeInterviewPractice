"""
Wildcard Matching (LC 44)

Problem: Match string with pattern containing ? (any char) and * (any sequence).
         Example: s = "adceb", p = "*a*b" → True

Pattern: 2D DP where dp[i][j] = s[:i] matches p[:j]

Related LeetCode Problems:
- LC 44: Wildcard Matching (Hard) ⭐⭐⭐
- LC 10: Regular Expression Matching (Hard)
- LC 1023: Camelcase Matching (Medium)

Time Complexity: O(m × n) - fill DP table
Space Complexity: O(m × n) - DP table
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(m+n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force recursively tries both branches for '*' (use for zero
#                  chars vs match one+ chars) — O(2^n) branches"
#   2. Problem:    "For pattern with 10 stars: 2^10 = 1024+ recursive calls per position"
#   3. Transition: "Use 2D DP to cache (s_idx, p_idx) results — O(m×n)"
#
# def is_match_naive(s, p):
#     def helper(i, j):
#         if j == len(p):
#             return i == len(s)
#         if i == len(s):
#             return all(c == '*' for c in p[j:])
#         
#         if p[j] == '*':
#             # Use * for zero chars OR match one char and continue with *
#             return helper(i, j + 1) or helper(i + 1, j)
#         elif p[j] == '?' or s[i] == p[j]:
#             return helper(i + 1, j + 1)
#         else:
#             return False
#     return helper(0, 0)
# ─────────────────────────────────────────────────────────────────────────────

def is_match(s, p):
    m, n = len(s), len(p)
    # dp[i][j] = s[0..i-1] matches p[0..j-1]
    dp = [[False] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = True
    
    # Handle patterns like "*", "**", etc.
    for j in range(1, n + 1):
        if p[j-1] == '*':
            dp[0][j] = dp[0][j-1]
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if p[j-1] == '*':
                # * matches empty or * matches one+ characters
                dp[i][j] = dp[i][j-1] or dp[i-1][j]
            elif p[j-1] == '?' or s[i-1] == p[j-1]:
                dp[i][j] = dp[i-1][j-1]
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(is_match("adceb", "*a*b"))  # True
