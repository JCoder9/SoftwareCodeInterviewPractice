"""
Knapsack DP - Coin Change II (Count Ways)

The Rule: Count HOW MANY ways to make the target amount.

Related LeetCode Problems:
- LC 518: Coin Change II (Medium)
- LC 377: Combination Sum IV (Medium)

Key Insight: Order matters for combinations vs permutations!
- Combinations: Loop coins FIRST (avoid duplicates like [1,2] and [2,1])
- Permutations: Loop amount FIRST (count [1,2] and [2,1] as different)

Time Complexity: O(n * amount)
Space Complexity: O(amount)
"""

from typing import List

def change(coins: List[int], amount: int) -> int:
    """
    LC 518: Coin Change II - count ways (COMBINATIONS).
    Time: O(n * amount)
    Space: O(amount)
    
    IMPORTANT: Loop coins first to avoid counting duplicates!
    """
    # dp[i] = number of ways to make amount i
    dp = [0] * (amount + 1)
    dp[0] = 1  # One way to make 0: use no coins
    
    # IMPORTANT: Loop coins first for combinations
    for coin in coins:
        for a in range(coin, amount + 1):
            dp[a] += dp[a - coin]
    
    return dp[amount]


def combination_sum_permutations(coins: List[int], amount: int) -> int:
    """
    LC 377: Combination Sum IV - count ways (PERMUTATIONS).
    Different order = different way!
    
    IMPORTANT: Loop amount first to count permutations!
    """
    dp = [0] * (amount + 1)
    dp[0] = 1
    
    # IMPORTANT: Loop amount first for permutations
    for a in range(1, amount + 1):
        for coin in coins:
            if coin <= a:
                dp[a] += dp[a - coin]
    
    return dp[amount]


def change_2d(coins: List[int], amount: int) -> int:
    """
    2D version for clarity.
    """
    n = len(coins)
    dp = [[0] * (amount + 1) for _ in range(n + 1)]
    
    # Base case: one way to make 0
    for i in range(n + 1):
        dp[i][0] = 1
    
    for i in range(1, n + 1):
        for a in range(amount + 1):
            # Don't use coin i-1
            dp[i][a] = dp[i-1][a]
            
            # Use coin i-1 (can use multiple times)
            if coins[i-1] <= a:
                dp[i][a] += dp[i][a - coins[i-1]]
    
    return dp[n][amount]


# Test cases
if __name__ == "__main__":
    # LC 518 - Combinations
    coins1 = [1, 2, 5]
    amount1 = 5
    print(f"Ways for {amount1} (combinations): {change(coins1, amount1)}")  # 4
    # [5], [2,2,1], [2,1,1,1], [1,1,1,1,1]
    
    coins2 = [2]
    amount2 = 3
    print(f"Ways for {amount2}: {change(coins2, amount2)}")  # 0
    
    coins3 = [10]
    amount3 = 10
    print(f"Ways for {amount3}: {change(coins3, amount3)}")  # 1
    
    # LC 377 - Permutations
    print(f"\nPermutations for {amount1}: {combination_sum_permutations(coins1, amount1)}")  # More than 4!
    
    # Compare combinations vs permutations
    coins4 = [1, 2, 3]
    amount4 = 4
    print(f"\nCombinations for {amount4}: {change(coins4, amount4)}")
    print(f"Permutations for {amount4}: {combination_sum_permutations(coins4, amount4)}")
    
    # 2D version
    print(f"\n2D version for {amount1}: {change_2d(coins1, amount1)}")
