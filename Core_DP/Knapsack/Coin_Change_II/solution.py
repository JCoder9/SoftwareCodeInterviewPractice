"""
Coin Change II (Count Ways)

Pattern: Count HOW MANY ways to make the target amount

Time Complexity: O(n * amount)
Space Complexity: O(amount)
"""

def coin_change_2(coins, amount):
    """
    Time: O(n * amount)
    Space: O(amount)
    """
    # dp[i] = number of ways to make amount i
    dp = [0] * (amount + 1)
    dp[0] = 1  # One way to make 0: use no coins
    
    # IMPORTANT: Loop coins first to avoid counting duplicates
    for coin in coins:
        for a in range(coin, amount + 1):
            dp[a] += dp[a - coin]
    
    return dp[amount]

# Example
if __name__ == "__main__":
    coins = [1, 2, 5]
    amount = 5
    print(coin_change_2(coins, amount))  # 4 ways: [5], [2,2,1], [2,1,1,1], [1,1,1,1,1]
