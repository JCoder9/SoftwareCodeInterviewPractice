"""
Knapsack DP - Coin Change (Minimum Coins)

The Rule: Find the MINIMUM number of coins to make a target amount.

Related LeetCode Problems:
- LC 322: Coin Change (Medium)
- LC 983: Minimum Cost For Tickets (Medium)
- LC 2547: Minimum Cost to Split an Array (Hard)

Key Insight: Unbounded knapsack minimization.
- dp[amount] = minimum coins needed to make amount
- For each coin, try adding it to all achievable amounts

Time Complexity: O(n * amount)
Space Complexity: O(amount)
"""

from typing import List
import sys

def coin_change(coins, amount):
    """
    LC 322: Coin Change - minimum coins.
    Time: O(n * amount), Space: O(amount)
    """
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0
    
    for coin in coins:
        for a in range(coin, amount + 1):
            dp[a] = min(dp[a], dp[a - coin] + 1)
    
    return dp[amount] if dp[amount] != float('inf') else -1


# Test cases
if __name__ == "__main__":
    """
    Returns minimum count AND which coins were used.
    """
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0
    parent = [-1] * (amount + 1)
    
    for coin in coins:
        for a in range(coin, amount + 1):
            if dp[a - coin] + 1 < dp[a]:
                dp[a] = dp[a - coin] + 1
                parent[a] = coin
    
    if dp[amount] == float('inf'):
        return -1, []
    
    # Backtrack to find coins
    result = []
    curr = amount
    while curr > 0:
        coin_used = parent[curr]
        result.append(coin_used)
        curr -= coin_used
    
    return dp[amount], result


def coin_change_2d(coins: List[int], amount: int) -> int:
    """
    2D version (less space efficient but clearer).
    """
    n = len(coins)
    dp = [[float('inf')] * (amount + 1) for _ in range(n + 1)]
    
    # Base case: 0 coins needed for amount 0
    for i in range(n + 1):
        dp[i][0] = 0
    
    for i in range(1, n + 1):
        for a in range(1, amount + 1):
            # Don't use coin i-1
            dp[i][a] = dp[i-1][a]
            
            # Use coin i-1 (can use multiple times)
            if coins[i-1] <= a:
                dp[i][a] = min(dp[i][a], dp[i][a - coins[i-1]] + 1)
    
    return dp[n][amount] if dp[n][amount] != float('inf') else -1


# Test cases
if __name__ == "__main__":
    # LC 322
    coins1 = [1, 2, 5]
    amount1 = 11
    print(f"Min coins for {amount1}: {coin_change(coins1, amount1)}")  # 3 (5+5+1)
    
    coins2 = [2]
    amount2 = 3
    print(f"Min coins for {amount2}: {coin_change(coins2, amount2)}")  # -1 (impossible)
    
    coins3 = [1]
    amount3 = 0
    print(f"Min coins for {amount3}: {coin_change(coins3, amount3)}")  # 0
    
    # With actual coins
    count, used_coins = coin_change_with_coins(coins1, amount1)
    print(f"\nCoins used: {used_coins}, count: {count}")
    
    # 2D version
    print(f"2D version: {coin_change_2d(coins1, amount1)}")
