"""
Regular Expression Matching (LC 10)

Problem: Match pattern with '.' (any char) and '*' (zero+ of previous char).
         Example: s = "aa", p = "a*" → True

Pattern: 2D DP where dp[i][j] = s[:i] matches p[:j]

Related LeetCode Problems:
- LC 10: Regular Expression Matching (Hard) ⭐⭐⭐
- LC 44: Wildcard Matching (Hard)
- LC 115: Distinct Subsequences (Hard)

Time Complexity: O(m × n) - fill DP table
Space Complexity: O(m × n) - DP table
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^(m+n)) time | O(m+n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force recursively tries both branches for '*' (use zero
#                  times vs one+ times) — O(2^n) branches"
#   2. Problem:    "For pattern 'a*b*c*...' with 10 stars: 2^10 = 1024+ recursive
#                  calls per position"
#   3. Transition: "Use 2D DP to cache (s_idx, p_idx) results — O(m×n)"
#
# def is_match_naive(s, p):
#     def helper(i, j):
#         # Base cases
#         if j == len(p):
#             return i == len(s)
#         
#         first_match = i < len(s) and (p[j] == s[i] or p[j] == '.')
#         
#         # Handle '*'
#         if j + 1 < len(p) and p[j + 1] == '*':
#             # Use * zero times OR use * one+ times
#             return (helper(i, j + 2) or
#                     (first_match and helper(i + 1, j)))
#         else:
#             return first_match and helper(i + 1, j + 1)
#     
#     return helper(0, 0)
# ─────────────────────────────────────────────────────────────────────────────

def is_match_regex(s, p):
    m, n = len(s), len(p)
    dp = [[False] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = True
    
    # Handle patterns like "a*", "a*b*", etc. matching empty string
    for j in range(2, n + 1):
        if p[j-1] == '*':
            dp[0][j] = dp[0][j-2]
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if p[j-1] == '*':
                # * means zero occurrences
                dp[i][j] = dp[i][j-2]
                
                # * means one+ occurrences (if previous char matches)
                if p[j-2] == '.' or p[j-2] == s[i-1]:
                    dp[i][j] = dp[i][j] or dp[i-1][j]
            elif p[j-1] == '.' or p[j-1] == s[i-1]:
                dp[i][j] = dp[i-1][j-1]
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(is_match_regex("aa", "a*"))  # True
    print(is_match_regex("ab", ".*"))  # True
