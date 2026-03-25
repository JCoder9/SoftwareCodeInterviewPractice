"""
1D Dynamic Programming - Climbing Stairs Pattern

Related LeetCode Problems:
- LC 70: Climbing Stairs (Easy)
- LC 746: Min Cost Climbing Stairs (Easy)
- LC 509: Fibonacci Number (Easy)
- LC 1137: N-th Tribonacci Number (Easy)

Key Insight: Build solution from smaller subproblems.
- dp[i] depends on dp[i-1] and/or dp[i-2]
- Fibonacci-like recurrence relation
- Can optimize space to O(1) by keeping only last 2 values

Time Complexity: O(n)
Space Complexity: O(n) or O(1) optimized
"""

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
