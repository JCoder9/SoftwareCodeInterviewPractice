"""
Longest Common Subsequence (LCS)

Problem: Find length of longest subsequence that appears in both strings (doesn't need to be contiguous).
         Example: "abcde" and "ace" → LCS is "ace" (length 3)

Pattern: 2D DP where dp[i][j] = LCS of first i chars of text1 and first j chars of text2.
         If chars match: dp[i][j] = dp[i-1][j-1] + 1
         Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])

Related LeetCode Problems:
- LC 1143: Longest Common Subsequence (Medium) ⭐⭐⭐
- LC 583: Delete Operation for Two Strings (Medium)
- LC 712: Minimum ASCII Delete Sum (Medium)
- LC 1092: Shortest Common Supersequence (Hard)

Time Complexity: O(m × n)
Space Complexity: O(m × n) - can optimize to O(min(m,n))
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^(m+n)) time | O(m+n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force recursively tries both options at each position:
#                  match chars or skip from either string — exponential branching"
#   2. Problem:    "For strings of length 20 each: over 1 trillion recursive calls;
#                  massive overlapping subproblems"
#   3. Transition: "2D DP table memoizes all (i,j) pairs; each computed once
#                  — reduces to O(m × n)"
#
# def lcs_naive(text1, text2, i=0, j=0):
#     if i >= len(text1) or j >= len(text2):
#         return 0
#     if text1[i] == text2[j]:
#         return 1 + lcs_naive(text1, text2, i + 1, j + 1)
#     return max(lcs_naive(text1, text2, i + 1, j),
#                lcs_naive(text1, text2, i, j + 1))
# ─────────────────────────────────────────────────────────────────────────────

def longest_common_subsequence(text1, text2):
    m, n = len(text1), len(text2)
    # dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1]
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if text1[i-1] == text2[j-1]:
                # Characters match! Extend previous LCS
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                # Take best of: skip char from text1 OR skip char from text2
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(longest_common_subsequence("abcde", "ace"))  # 3
