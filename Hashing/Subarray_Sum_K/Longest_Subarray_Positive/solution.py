"""
Longest Subarray Sum K - Positive Numbers Only

Pattern: Sliding window for arrays with only positive numbers.

Time Complexity: O(n)
Space Complexity: O(1)
"""

from typing import List


def longest_subarray_sum_k_positive(nums, k):
    """
    Longest subarray with sum = k (positive numbers only)
    Uses sliding window technique
    """
    left = 0
    current_sum = 0
    max_length = 0
    
    for right in range(len(nums)):
        current_sum += nums[right]
        
        # Shrink window if sum exceeds k
        while current_sum > k and left <= right:
            current_sum -= nums[left]
            left += 1
        
        # Check if we found a valid subarray
        if current_sum == k:
            max_length = max(max_length, right - left + 1)
    
    return max_length


# Test
if __name__ == "__main__":
    print(longest_subarray_sum_k_positive([1, 2, 3, 4, 5], 5))  # 2 ([2,3])
    print(longest_subarray_sum_k_positive([1, 1, 1], 2))         # 2
    print(longest_subarray_sum_k_positive([1, 4, 3, 3, 5], 6))   # 2 ([3,3])
