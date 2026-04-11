"""
Coin Change (Minimum Coins)

Problem: Given coin denominations and target amount, find minimum coins needed to make that amount.
         Return -1 if impossible.

Pattern: Unbounded knapsack (use each coin unlimited times).
         dp[amount] = min(dp[amount], dp[amount - coin] + 1)

Related LeetCode Problems:
- LC 322: Coin Change (Medium) ⭐⭐⭐
- LC 518: Coin Change II (Medium) - count ways
- LC 983: Minimum Cost For Tickets (Medium)

Time Complexity: O(coins × amount)
Space Complexity: O(amount)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(amount^coins) time | O(amount) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all coin combinations recursively: for each coin,
#                  subtract and recurse — exponential branching"
#   2. Problem:    "For amount=100 with coins [1,2,5]: massive recursion tree;
#                  many overlapping subproblems"
#   3. Transition: "DP builds from 0 to amount; each subproblem solved once
#                  — O(coins × amount)"
#
# def coin_change_naive(coins, amount):
#     if amount == 0:
#         return 0
#     if amount < 0:
#         return float('inf')
#     min_coins = float('inf')
#     for coin in coins:
#         result = coin_change_naive(coins, amount - coin)
#         if result != float('inf'):
#             min_coins = min(min_coins, result + 1)
#     return min_coins
# ─────────────────────────────────────────────────────────────────────────────

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
