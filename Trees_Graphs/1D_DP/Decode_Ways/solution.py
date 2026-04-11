"""
Decode Ways (Count Decoding Combinations)

Problem: Given string of digits, count ways to decode it where A=1, B=2, ..., Z=26.
         For example, "226" can be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Pattern: DP where dp[i] = ways to decode string up to index i.
         Can decode 1 digit (1-9) or 2 digits (10-26).

Related LeetCode Problems:
- LC 91: Decode Ways (Medium) ⭐⭐⭐
- LC 639: Decode Ways II (Hard) - with wildcards
- LC 842: Split Array into Fibonacci Sequence (Medium)

Time Complexity: O(n)
Space Complexity: O(n) - can be optimized to O(1)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force recursively tries decoding 1 digit or 2 digits at
#                  each position — exponential branching"
#   2. Problem:    "For n=30: over 1 billion recursive calls; many overlapping
#                  subproblems computed repeatedly"
#   3. Transition: "DP memoizes count for each position; each subproblem solved once
#                  — O(n) time"
#
# def numDecodings_naive(s, i=0):
#     if i == len(s):
#         return 1
#     if s[i] == '0':
#         return 0
#     
#     # Decode 1 digit
#     count = numDecodings_naive(s, i + 1)
#     
#     # Decode 2 digits (if valid)
#     if i + 1 < len(s):
#         two_digit = int(s[i:i+2])
#         if 10 <= two_digit <= 26:
#             count += numDecodings_naive(s, i + 2)
#     
#     return count
# ─────────────────────────────────────────────────────────────────────────────

def numDecodings(s):
    if not s or s[0] == '0':
        return 0
    
    n = len(s)
    dp = [0] * (n + 1)
    dp[0] = 1  # Empty string
    dp[1] = 1  # First character (already checked it's not '0')
    
    for i in range(2, n + 1):
        # Check one digit
        if s[i-1] != '0':
            dp[i] += dp[i-1]
        
        # Check two digits
        two_digit = int(s[i-2:i])
        if 10 <= two_digit <= 26:
            dp[i] += dp[i-2]
    
    return dp[n]

if __name__ == "__main__":
    print(numDecodings("226"))  # Output: 3
