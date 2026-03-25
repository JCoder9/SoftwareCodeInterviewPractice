"""
Coin Change (Minimum Coins)

Pattern: Find the MINIMUM number of coins to make a target amount

Time Complexity: O(n * amount)
Space Complexity: O(amount)
"""

def coin_change(coins, amount):
    """
    Time: O(n * amount) where n is number of coin types
    Space: O(amount)
    """
    # dp[i] = minimum coins needed to make amount i
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0  # 0 coins to make amount 0
    
    for coin in coins:
        for a in range(coin, amount + 1):
            dp[a] = min(dp[a], dp[a - coin] + 1)
    
    return dp[amount] if dp[amount] != float('inf') else -1

# Example
if __name__ == "__main__":
    coins = [1, 2, 5]
    amount = 11
    print(coin_change(coins, amount))  # 3 (5 + 5 + 1)
