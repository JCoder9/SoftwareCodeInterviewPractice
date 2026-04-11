"""
1D Dynamic Programming - Climbing Stairs Pattern

Problem: You're climbing stairs with n steps. You can climb 1 or 2 steps at a time.
         How many distinct ways can you reach the top?

Pattern: Build solution from smaller subproblems.
         dp[i] = dp[i-1] + dp[i-2] (Fibonacci-like)

Related LeetCode Problems:
- LC 70: Climbing Stairs (Easy)
- LC 746: Min Cost Climbing Stairs (Easy)
- LC 509: Fibonacci Number (Easy)
- LC 1137: N-th Tribonacci Number (Easy)

Time Complexity: O(n)
Space Complexity: O(1) optimized
"""

# ───────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(2^n) time | O(n) space
# ───────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force uses naive recursion: climb(n) = climb(n-1) + climb(n-2)
#                   — exponential O(2^n) due to repeated subproblem calculations"
#   2. Problem:    "For n=40, makes billions of recursive calls; many duplicate
#                   computations"
#   3. Transition: "With DP (memoization or bottom-up), each subproblem solved once
#                   — reduces to O(n) time"
#
# def climbStairs_naive(n):
#     if n <= 2:
#         return n
#     return climbStairs_naive(n - 1) + climbStairs_naive(n - 2)
# ───────────────────────────────────────────────────────────────────────────

from typing import List


def climbStairs(n: int) -> int:
    """
    Can climb 1 or 2 steps. How many ways to reach top?
    dp[i] = dp[i-1] + dp[i-2]
    """
    if n <= 2:
        return n
    
    # dp[i] = number of ways to reach step i
    prev2, prev1 = 1, 2
    
    for i in range(3, n + 1):
        current = prev1 + prev2
        prev2, prev1 = prev1, current
    
    return prev1




if __name__ == "__main__":
    # Test LC 70
    print("Climbing stairs (n=5):", climbStairs(5))  # 8
    
    # Test LC 746
    cost1 = [10, 15, 20]
