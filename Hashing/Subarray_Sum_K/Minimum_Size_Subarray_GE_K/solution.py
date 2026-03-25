"""
Minimum Size Subarray Sum >= K

Pattern: Sliding window for minimum length with sum >= target.

Time Complexity: O(n)
Space Complexity: O(1)
"""

from typing import List


def min_subarray_len(nums, k):
    """Find minimum length of subarray with sum >= k"""
    left = 0
    current_sum = 0
    min_length = float('inf')
    
    for right in range(len(nums)):
        current_sum += nums[right]
        
        # Shrink window while sum >= k
        while current_sum >= k:
            min_length = min(min_length, right - left + 1)
            current_sum -= nums[left]
            left += 1
    
    return min_length if min_length != float('inf') else 0


# Test
if __name__ == "__main__":
    print(min_subarray_len([2, 3, 1, 2, 4, 3], 7))  # 2 ([4,3])
    print(min_subarray_len([1, 4, 4], 4))           # 1
    print(min_subarray_len([1, 1, 1, 1, 1], 11))    # 0 (impossible)
