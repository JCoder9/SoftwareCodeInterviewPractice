"""
Longest Subarray Sum K - With Negatives

Pattern: HashMap approach for arrays with negative numbers.

Time Complexity: O(n)
Space Complexity: O(n)
"""

from typing import List


def longest_subarray_sum_k_with_negatives(nums, k):
    """
    Longest subarray with sum = k (works with negatives/zeros)
    Uses prefix sum + hashmap
    """
    # Map: prefix_sum -> earliest_index where this sum occurred
    prefix_map = {0: -1}  # Base case: sum 0 at index -1
    
    current_sum = 0
    max_length = 0
    
    for i in range(len(nums)):
        current_sum += nums[i]
        
        # Check if (current_sum - k) exists
        diff = current_sum - k
        if diff in prefix_map:
            length = i - prefix_map[diff]
            max_length = max(max_length, length)
        
        # Only store FIRST occurrence of this sum (for longest subarray)
        if current_sum not in prefix_map:
            prefix_map[current_sum] = i
    
    return max_length


# Test
if __name__ == "__main__":
    print(longest_subarray_sum_k_with_negatives([1, -1, 5, -2, 3], 3))  # 4
    print(longest_subarray_sum_k_with_negatives([1, 1, 1], 2))          # 2
    print(longest_subarray_sum_k_with_negatives([-2, -1, 2, 1], 1))     # 4
