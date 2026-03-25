"""
Sort + Two Pointers Pattern

Strategy: Sort first, then use two pointers to find pairs/triplets.

Time Complexity: O(n log n) for sort + O(n) or O(n²) for pointers
Space Complexity: O(1)
"""

from typing import List


def two_sum_sorted(arr, target):
    """Find two numbers that sum to a target. Array is already sorted."""
    # Array is already sorted, but if not:
    # arr.sort()
    
    left = 0
    right = len(arr) - 1
    
    while left < right:
        current_sum = arr[left] + arr[right]
        
        if current_sum == target:
            return [left, right]  # or the values
        elif current_sum < target:
            left += 1  # need bigger sum
        else:
            right -= 1  # need smaller sum
    
    return [-1, -1]  # not found


# Example
if __name__ == "__main__":
    print(two_sum_sorted([2, 7, 11, 15], 9))  # Output: [0, 1]
