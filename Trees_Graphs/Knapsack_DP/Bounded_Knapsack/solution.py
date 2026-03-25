"""
Knapsack DP - Bounded Knapsack

The Rule: Each item can be taken up to a LIMITED number of times.

Related LeetCode Problems:
- Similar to unbounded but with count limits
- Can be optimized with binary representation

Key Insight: Hybrid of 0/1 and unbounded.
- For each item type, try 0, 1, 2, ..., up to count[i] times
- Can optimize by treating as multiple 0/1 items
- OR use binary representation (e.g., count=7 -> items of weight 1,2,4)

Time Complexity: O(n * capacity * max_count) naive
Space Complexity: O(capacity)
"""

from typing import List

def bounded_knapsack(weights: List[int], values: List[int], 
                     counts: List[int], capacity: int) -> int:
    """
    Bounded knapsack - each item has limited count.
    Time: O(n * capacity * max_count)
    Space: O(capacity)
    """
    dp = [0] * (capacity + 1)
    
    for i in range(len(weights)):
        # Must go backwards for each item type
        for w in range(capacity, weights[i] - 1, -1):
            # Try taking 1, 2, ..., up to counts[i] of item i
            for k in range(1, counts[i] + 1):
                if weights[i] * k <= w:
                    dp[w] = max(dp[w], dp[w - weights[i] * k] + values[i] * k)
                else:
                    break
    
    return dp[capacity]


def bounded_knapsack_optimized(weights: List[int], values: List[int], 
                               counts: List[int], capacity: int) -> int:
    """
    Optimized: Flatten into multiple 0/1 items.
    Time: O(sum(counts) * capacity) - better when counts are small
    Space: O(capacity)
    """
    # Flatten items
    flattened_weights = []
    flattened_values = []
    
    for i in range(len(weights)):
        for _ in range(counts[i]):
            flattened_weights.append(weights[i])
            flattened_values.append(values[i])
    
    # Standard 0/1 knapsack
    dp = [0] * (capacity + 1)
    
    for i in range(len(flattened_weights)):
        for w in range(capacity, flattened_weights[i] - 1, -1):
            dp[w] = max(dp[w], dp[w - flattened_weights[i]] + flattened_values[i])
    
    return dp[capacity]


def bounded_knapsack_binary(weights: List[int], values: List[int], 
                            counts: List[int], capacity: int) -> int:
    """
    Binary representation optimization.
    If count = 7, use items of multiplier 1, 2, 4 instead of 7 separate items.
    Time: O(n * log(max_count) * capacity)
    Space: O(capacity)
    """
    # Convert to binary representation
    binary_weights = []
    binary_values = []
    
    for i in range(len(weights)):
        count = counts[i]
        k = 1
        
        while k <= count:
            binary_weights.append(weights[i] * k)
            binary_values.append(values[i] * k)
            count -= k
            k *= 2
        
        # Add remaining
        if count > 0:
            binary_weights.append(weights[i] * count)
            binary_values.append(values[i] * count)
    
    # Standard 0/1 knapsack on binary items
    dp = [0] * (capacity + 1)
    
    for i in range(len(binary_weights)):
        for w in range(capacity, binary_weights[i] - 1, -1):
            dp[w] = max(dp[w], dp[w - binary_weights[i]] + binary_values[i])
    
    return dp[capacity]


# Test cases
if __name__ == "__main__":
    weights = [1, 3, 4]
    values = [10, 40, 50]
    counts = [2, 1, 3]  # Can take up to 2 of item 0, 1 of item 1, 3 of item 2
    capacity = 8
    
    print(f"Bounded knapsack: {bounded_knapsack(weights, values, counts, capacity)}")
    print(f"Flattened: {bounded_knapsack_optimized(weights, values, counts, capacity)}")
    print(f"Binary: {bounded_knapsack_binary(weights, values, counts, capacity)}")
    
    # Another test
    weights2 = [2, 3, 5]
    values2 = [3, 4, 7]
    counts2 = [2, 3, 1]
    capacity2 = 10
    
    print(f"\nTest 2: {bounded_knapsack(weights2, values2, counts2, capacity2)}")
