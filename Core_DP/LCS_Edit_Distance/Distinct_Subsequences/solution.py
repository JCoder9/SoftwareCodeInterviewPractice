"""
Distinct Subsequences (LC 115)

Problem: Count distinct subsequences of s that equal t.
         Example: s = "rabbbit", t = "rabbit" → 3 (rab_bit, ra_bbit, rabb_it)

Pattern: 2D DP where dp[i][j] = # ways t[:j] appears in s[:i]

Related LeetCode Problems:
- LC 115: Distinct Subsequences (Hard) ⭐⭐⭐
- LC 940: Distinct Subsequences II (Hard)
- LC 792: Number of Matching Subsequences (Medium)

Time Complexity: O(m × n) - fill DP table
Space Complexity: O(m × n) - DP table
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^m) time | O(m) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force recursively tries including/excluding each char
#                  from s — O(2^m) branches"
#   2. Problem:    "For m=30: 2^30 = 1 billion recursive calls; massive overlap"
#   3. Transition: "Use 2D DP to cache (s_idx, t_idx) results — O(m×n)"
#
# def num_distinct_naive(s, t):
#     def helper(i, j):
#         # Found complete match
#         if j == len(t):
#             return 1
#         # No more chars in s
#         if i == len(s):
#             return 0
#         
#         count = 0
#         # Don't use s[i]
#         count += helper(i + 1, j)
#         # Use s[i] if it matches t[j]
#         if s[i] == t[j]:
#             count += helper(i + 1, j + 1)
#         return count
#     return helper(0, 0)
# ─────────────────────────────────────────────────────────────────────────────

def num_distinct(s, t):
    m, n = len(s), len(t)
    # dp[i][j] = number of ways t[0..j-1] appears in s[0..i-1]
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    # Empty string is subsequence of any string (1 way)
    for i in range(m + 1):
        dp[i][0] = 1
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            # Don't use s[i-1]
            dp[i][j] = dp[i-1][j]
            
            # Use s[i-1] if it matches t[j-1]
            if s[i-1] == t[j-1]:
                dp[i][j] += dp[i-1][j-1]
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(num_distinct("rabbbit", "rabbit"))  # 3
