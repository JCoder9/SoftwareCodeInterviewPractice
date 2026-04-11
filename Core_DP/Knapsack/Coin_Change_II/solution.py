"""
Coin Change II (Count Ways)

Problem: Given coin denominations and target amount, count how many different ways
         you can make that amount using the coins (unlimited use of each coin).

Pattern: Unbounded knapsack counting problem.
         dp[amount] += dp[amount - coin]
         Important: loop coins first to avoid counting duplicates!

Related LeetCode Problems:
- LC 518: Coin Change II (Medium) ⭐⭐⭐
- LC 322: Coin Change (Medium) - min coins version
- LC 377: Combination Sum IV (Medium) - order matters

Time Complexity: O(coins × amount)
Space Complexity: O(amount)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(amount^coins) time | O(amount) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force generates all combinations recursively: try each coin,
#                  subtract from amount, recurse — exponential branching"
#   2. Problem:    "Same subproblems computed many times; for amount=100: millions
#                  of recursive calls"
#   3. Transition: "DP counts ways bottom-up; loop coins first to avoid duplicates
#                  — O(coins × amount)"
#
# def coin_change_2_naive(coins, amount, i=0):
#     if amount == 0:
#         return 1
#     if amount < 0 or i >= len(coins):
#         return 0
#     # Use coin[i] or skip it
#     return (coin_change_2_naive(coins, amount - coins[i], i) +
#             coin_change_2_naive(coins, amount, i + 1))
# ─────────────────────────────────────────────────────────────────────────────

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
