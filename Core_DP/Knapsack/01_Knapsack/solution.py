"""
0/1 Knapsack (Classic)

Pattern: Each item can be taken ONCE or NOT AT ALL

Time Complexity: O(n * capacity)
Space Complexity: O(n * capacity) - can be optimized to O(capacity)
"""

def knapsack_01(weights, values, capacity):
    """
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

# Space optimized version
def knapsack_01_optimized(weights, values, capacity):
    """
    Time: O(n * capacity)
    Space: O(capacity) - only one row!
    """
    dp = [0] * (capacity + 1)
    
    for i in range(len(weights)):
        # MUST go backwards to avoid using updated values
        for w in range(capacity, weights[i] - 1, -1):
            dp[w] = max(dp[w], dp[w - weights[i]] + values[i])
    
    return dp[capacity]

# Example
if __name__ == "__main__":
    weights = [1, 3, 4, 5]
    values = [1, 4, 5, 7]
    capacity = 7
    print(knapsack_01(weights, values, capacity))  # Output: 9 (take items 1 and 3)
