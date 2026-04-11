"""
0/1 Knapsack (Classic)

Problem: Given n items with weights and values, and a knapsack with capacity W,
         maximize value without exceeding capacity. Each item can be taken 0 or 1 times.

Pattern: For each item, choose to include it (if it fits) or exclude it.
         dp[i][w] = max(dp[i-1][w], value[i] + dp[i-1][w-weight[i]])

Related LeetCode Problems:
- 0/1 Knapsack (Classic CS problem)
- LC 416: Partition Equal Subset Sum (Medium)
- LC 494: Target Sum (Medium)
- LC 1049: Last Stone Weight II (Medium)

Time Complexity: O(n × capacity)
Space Complexity: O(capacity) - optimized
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries every subset: for each item, recursively include
#                  or exclude it — exponential O(2^n)"
#   2. Problem:    "For 30 items: over 1 billion subsets; overlapping subproblems
#                  computed many times"
#   3. Transition: "With DP, memoize subproblems (i, remaining_capacity) — reduces
#                  to O(n × capacity)"
#
# def knapsack_naive(weights, values, capacity, i=0):
#     if i >= len(weights) or capacity == 0:
#         return 0
#     # Don't take item i
#     exclude = knapsack_naive(weights, values, capacity, i + 1)
#     # Take item i (if it fits)
#     include = 0
#     if weights[i] <= capacity:
#         include = values[i] + knapsack_naive(weights, values, 
#                                              capacity - weights[i], i + 1)
#     return max(include, exclude)
# ─────────────────────────────────────────────────────────────────────────────

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
