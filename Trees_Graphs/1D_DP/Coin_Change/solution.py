"""
1D Dynamic Programming - Coin Change Pattern

Related LeetCode Problems:
- LC 322: Coin Change (Medium) - minimum coins
- LC 518: Coin Change II (Medium) - count combinations
- LC 377: Combination Sum IV (Medium) - count permutations
- LC 983: Minimum Cost For Tickets (Medium)

Key Insight: Unbounded knapsack - can use each coin unlimited times.
- dp[amount] = min/count ways to make amount
- For each amount, try all coins
- Similar problems: ticket/stamp purchasing

Time Complexity: O(n × amount) where n is number of coins
Space Complexity: O(amount)
"""

from typing import List


def coinChange(coins: List[int], amount: int) -> int:
    """
    Find minimum number of coins to make amount.
    Return -1 if impossible.
    """
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0
    
    for amt in range(1, amount + 1):
        for coin in coins:
            if coin <= amt:
                dp[amt] = min(dp[amt], dp[amt - coin] + 1)
    
    return dp[amount] if dp[amount] != float('inf') else -1




if __name__ == "__main__":
    # Test LC 322
    coins1 = [1, 2, 5]
    print("Min coins for 11:", coinChange(coins1, 11))  # 3: (5+5+1)
    
    coins2 = [2]
