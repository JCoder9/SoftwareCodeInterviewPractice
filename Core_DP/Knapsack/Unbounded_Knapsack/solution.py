"""
Unbounded Knapsack

Pattern: Each item can be taken UNLIMITED times

Time Complexity: O(n * capacity)
Space Complexity: O(capacity)
"""

def unbounded_knapsack(weights, values, capacity):
    """
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

# Example: Coin Change Maximum Value
if __name__ == "__main__":
    weights = [1, 3, 4]
    values = [10, 40, 50]
    capacity = 8
    print(unbounded_knapsack(weights, values, capacity))  # Output: 110
