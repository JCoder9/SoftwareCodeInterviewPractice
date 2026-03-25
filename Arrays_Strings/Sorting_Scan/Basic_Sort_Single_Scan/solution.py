"""
Basic Sort + Single Scan Pattern

Strategy: Sort array, then scan to find answer.
Often used when sorted order reveals patterns.

Time Complexity: O(n log n) for sort + O(n) for scan = O(n log n)
Space Complexity: O(1) to O(n) depending on problem
"""

from typing import List


def min_absolute_difference(arr):
    """Find the minimum absolute difference between any two elements."""
    # Sort the array
    arr.sort()
    
    min_diff = float('inf')
    
    # Scan through consecutive pairs
    for i in range(len(arr) - 1):
        diff = arr[i + 1] - arr[i]
        min_diff = min(min_diff, diff)
    
    return min_diff


# Example
if __name__ == "__main__":
    print(min_absolute_difference([4, 2, 1, 3]))  # Output: 1
