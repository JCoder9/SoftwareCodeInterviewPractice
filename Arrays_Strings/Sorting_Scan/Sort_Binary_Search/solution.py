"""
Sort + Binary Search Pattern

Strategy: Sort to enable binary search for efficient lookups.

Time Complexity: O(n log n) for sort + O(log n) per query
Space Complexity: O(1) to O(n)
"""

from typing import List


def find_pairs_with_difference(arr, k):
    """Find if any two numbers have exactly difference k."""
    arr.sort()
    
    def binary_search(nums, target, start):
        left, right = start, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return True
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1
        return False
    
    pairs = []
    for i in range(len(arr) - 1):
        # Skip duplicates
        if i > 0 and arr[i] == arr[i - 1]:
            continue
        
        target = arr[i] + k
        if binary_search(arr, target, i + 1):
            pairs.append([arr[i], target])
    
    return pairs


# Example
if __name__ == "__main__":
    print(find_pairs_with_difference([1, 5, 3, 4, 2], 3))
    # Output: [[1, 4], [2, 5]]
