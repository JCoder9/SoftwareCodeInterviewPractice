"""
Edit Distance (Levenshtein Distance)

Problem: Find minimum number of operations (insert, delete, or replace a character)
         required to convert word1 into word2.
         Example: "horse" → "ros" requires 3 ops (replace h→r, delete o, delete e)

Pattern: 2D DP where dp[i][j] = min edits to convert word1[0..i-1] to word2[0..j-1].
         If chars match: dp[i][j] = dp[i-1][j-1]
         Else: dp[i][j] = 1 + min(replace, delete, insert)

Related LeetCode Problems:
- LC 72: Edit Distance (Medium) ⭐⭐⭐
- LC 583: Delete Operation for Two Strings (Medium)
- LC 1143: Longest Common Subsequence (Medium)
- LC 161: One Edit Distance (Medium)

Time Complexity: O(m × n)
Space Complexity: O(m × n) - can optimize to O(min(m,n))
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(3^(m+n)) time | O(m+n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all 3 operations recursively at each mismatch:
#                  insert, delete, or replace — 3^(m+n) branching factor"
#   2. Problem:    "For length-10 strings: billions of recursive calls; same
#                  subproblems recomputed countless times"
#   3. Transition: "2D DP memoizes all (i,j) subproblems; each solved once
#                  — O(m × n) time"
#
# def min_distance_naive(word1, word2, i=0, j=0):
#     if i >= len(word1):  # Insert remaining chars of word2
#         return len(word2) - j
#     if j >= len(word2):  # Delete remaining chars of word1
#         return len(word1) - i
#     if word1[i] == word2[j]:
#         return min_distance_naive(word1, word2, i + 1, j + 1)
#     # Try all 3 operations
#     replace = 1 + min_distance_naive(word1, word2, i + 1, j + 1)
#     delete  = 1 + min_distance_naive(word1, word2, i + 1, j)
#     insert  = 1 + min_distance_naive(word1, word2, i, j + 1)
#     return min(replace, delete, insert)
# ─────────────────────────────────────────────────────────────────────────────

def min_distance(word1, word2):
    m, n = len(word1), len(word2)
    # dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    # Base cases: converting to/from empty string
    for i in range(m + 1):
        dp[i][0] = i  # Delete all i characters
    for j in range(n + 1):
        dp[0][j] = j  # Insert all j characters
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if word1[i-1] == word2[j-1]:
                # Characters match, no operation needed
                dp[i][j] = dp[i-1][j-1]
            else:
                # Min of: replace, delete, insert
                dp[i][j] = 1 + min(
                    dp[i-1][j-1],  # Replace
                    dp[i-1][j],     # Delete from word1
                    dp[i][j-1]      # Insert into word1
                )
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(min_distance("horse", "ros"))  # 3
