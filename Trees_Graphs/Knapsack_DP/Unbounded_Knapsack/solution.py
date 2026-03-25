"""
Knapsack DP - Unbounded Knapsack

The Rule: Each item can be taken UNLIMITED times.

Related LeetCode Problems:
- LC 322: Coin Change (Medium)
- LC 518: Coin Change II (Medium)
- LC 377: Combination Sum IV (Medium)
- LC 983: Minimum Cost For Tickets (Medium)

Key Insight: Can use same item multiple times.
- Unlike 0/1 knapsack, iterate capacity forwards AND reuse same dp row

Time Complexity: O(n * capacity)
Space Complexity: O(capacity)
"""

from typing import List

def unbounded_knapsack(weights: List[int], values: List[int], capacity: int) -> int:
    """
    Unbounded knapsack - can use each item unlimited times.
    Time: O(n * capacity)
    Space: O(capacity)
    """
    dp = [0] * (capacity + 1)
    
    for w in range(1, capacity + 1):
        for i in range(len(weights)):
            if weights[i] <= w:
                # Can take item i multiple times
                dp[w] = max(dp[w], dp[w - weights[i]] + values[i])
    
    return dp[capacity]


def unbounded_knapsack_alt(weights: List[int], values: List[int], capacity: int) -> int:
    """
    Alternative formulation - iterate items first.
    Time: O(n * capacity)
    Space: O(capacity)
    """
    dp = [0] * (capacity + 1)
    
    for i in range(len(weights)):
        # Go FORWARD (unlike 0/1 knapsack which goes backward)
        for w in range(weights[i], capacity + 1):
            dp[w] = max(dp[w], dp[w - weights[i]] + values[i])
    
    return dp[capacity]


def unbounded_knapsack_2d(weights: List[int], values: List[int], capacity: int) -> int:
    """
    2D version for clarity.
    """
    n = len(weights)
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    
    for i in range(1, n + 1):
        for w in range(capacity + 1):
            # Don't take item i-1
            dp[i][w] = dp[i-1][w]
            
            # Take item i-1 (can use multiple times, so look at dp[i] not dp[i-1])
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i][w], 
                              dp[i][w - weights[i-1]] + values[i-1])
    
    return dp[n][capacity]


# Test cases
if __name__ == "__main__":
    # Example: Coin Change Maximum Value
    weights = [1, 3, 4]
    values = [10, 40, 50]
    capacity = 8
    
    print(f"Unbounded: {unbounded_knapsack(weights, values, capacity)}")  # 110
    print(f"Alternative: {unbounded_knapsack_alt(weights, values, capacity)}")  # 110
    print(f"2D version: {unbounded_knapsack_2d(weights, values, capacity)}")  # 110
    
    # Rod cutting problem
    lengths = [1, 2, 3, 4, 5]
    prices = [2, 5, 7, 8, 10]
    rod_length = 5
    print(f"\nRod cutting (length {rod_length}): {unbounded_knapsack(lengths, prices, rod_length)}")  # 12
