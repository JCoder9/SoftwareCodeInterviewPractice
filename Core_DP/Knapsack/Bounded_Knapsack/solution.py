"""
Bounded Knapsack

Pattern: Each item can be taken up to a LIMITED number of times

Time Complexity: O(n * capacity * max_count)
Space Complexity: O(capacity)
"""

def bounded_knapsack(weights, values, counts, capacity):
    """
    Time: O(n * capacity * max_count)
    Space: O(capacity)
    """
    dp = [0] * (capacity + 1)
    
    for i in range(len(weights)):
        # Must go backwards for each item type
        for w in range(capacity, weights[i] - 1, -1):
            # Try taking 1, 2, ... up to counts[i] of item i
            for k in range(1, counts[i] + 1):
                if weights[i] * k <= w:
                    dp[w] = max(dp[w], dp[w - weights[i] * k] + values[i] * k)
                else:
                    break
    
    return dp[capacity]

# Example
if __name__ == "__main__":
    weights = [1, 3, 4]
    values = [10, 40, 50]
    counts = [2, 1, 3]  # Can take up to 2 of item 0, 1 of item 1, etc.
    capacity = 8
    print(bounded_knapsack(weights, values, counts, capacity))
