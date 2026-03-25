"""
Sort + Sliding Window Pattern

Strategy: Sort first, then use sliding window for range-based queries.

Time Complexity: O(n log n) for sort + O(n) for window
Space Complexity: O(1)
"""

from typing import List


def smallest_range_with_k_elements(arr, k):
    """Find smallest subarray that contains k consecutive elements."""
    # Sort the array
    arr.sort()
    
    min_range = float('inf')
    result = [0, 0]
    
    # Sliding window of size k
    for i in range(len(arr) - k + 1):
        current_range = arr[i + k - 1] - arr[i]
        if current_range < min_range:
            min_range = current_range
            result = [arr[i], arr[i + k - 1]]
    
    return result


# Example
if __name__ == "__main__":
    print(smallest_range_with_k_elements([4, 1, 3, 2, 6, 8], 3))
    # Output: [1, 3] (covers 1,2,3)
