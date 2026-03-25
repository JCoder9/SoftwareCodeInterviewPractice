"""
Knapsack DP - 0/1 Knapsack (Classic)

The Rule: Each item can be taken ONCE or NOT AT ALL (0 or 1 times).

Related LeetCode Problems:
- LC 416: Partition Equal Subset Sum (Medium)
- LC 494: Target Sum (Medium)
- LC 1049: Last Stone Weight II (Medium)

Key Insight: For each item, choose to take it or leave it.
- dp[i][w] = max value using first i items with weight limit w
- If take item i: dp[i][w] = dp[i-1][w - weight[i]] + value[i]
- If skip item i: dp[i][w] = dp[i-1][w]

Time Complexity: O(n * capacity)
Space Complexity: O(n * capacity) or O(capacity) optimized
"""

from typing import List

def knapsack_01(weights: List[int], values: List[int], capacity: int) -> int:
    """
    Classic 0/1 Knapsack - 2D DP approach.
    Time: O(n * capacity)
    Space: O(n * capacity)
    """
    n = len(weights)
    # dp[i][w] = max value using first i items with weight limit w
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    
    for i in range(1, n + 1):
        for w in range(capacity + 1):
            # Option 1: Don't take item i-1
            dp[i][w] = dp[i-1][w]
            
            # Option 2: Take item i-1 (if it fits)
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i][w], 
                              dp[i-1][w - weights[i-1]] + values[i-1])
    
    return dp[n][capacity]


def knapsack_01_optimized(weights: List[int], values: List[int], capacity: int) -> int:
    """
    Space-optimized version using 1D array.
    MUST iterate backwards to avoid using updated values.
    Time: O(n * capacity)
    Space: O(capacity)
    """
    dp = [0] * (capacity + 1)
    
    for i in range(len(weights)):
        # MUST go backwards to avoid using updated values
        for w in range(capacity, weights[i] - 1, -1):
            dp[w] = max(dp[w], dp[w - weights[i]] + values[i])
    
    return dp[capacity]


def knapsack_01_with_items(weights: List[int], values: List[int], capacity: int) -> tuple:
    """
    Returns max value AND which items were selected.
    """
    n = len(weights)
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    
    for i in range(1, n + 1):
        for w in range(capacity + 1):
            dp[i][w] = dp[i-1][w]
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i][w], 
                              dp[i-1][w - weights[i-1]] + values[i-1])
    
    # Backtrack to find items
    selected = []
    w = capacity
    for i in range(n, 0, -1):
        if dp[i][w] != dp[i-1][w]:
            selected.append(i-1)
            w -= weights[i-1]
    
    return dp[n][capacity], selected[::-1]


# Test cases
if __name__ == "__main__":
    weights = [1, 3, 4, 5]
    values = [1, 4, 5, 7]
    capacity = 7
    
    print(f"2D DP: {knapsack_01(weights, values, capacity)}")  # 9
    print(f"1D DP: {knapsack_01_optimized(weights, values, capacity)}")  # 9
    
    max_val, items = knapsack_01_with_items(weights, values, capacity)
    print(f"Max value: {max_val}, Items: {items}")  # 9, [1, 2]
    
    # More test cases
    weights2 = [2, 1, 3, 2]
    values2 = [12, 10, 20, 15]
    capacity2 = 5
    print(f"\nTest 2: {knapsack_01(weights2, values2, capacity2)}")  # 37
