"""
Subarray Sum Equals K - Count Pattern

Pattern: Prefix sum + hash map to count subarrays with exact sum.

Time Complexity: O(n)
Space Complexity: O(n)
"""

from typing import List


def subarray_sum_equals_k(nums, k):
    """Count subarrays that sum to k"""
    # Dictionary to store: {prefix_sum: count_of_occurrences}
    prefix_sums = {0: 1}  # Base case: empty prefix has sum 0
    
    current_sum = 0
    count = 0
    
    for num in nums:
        current_sum += num
        
        # Check if (current_sum - k) exists
        # If yes, we found subarray(s) that sum to k
        diff = current_sum - k
        if diff in prefix_sums:
            count += prefix_sums[diff]
        
        # Add current sum to our map
        prefix_sums[current_sum] = prefix_sums.get(current_sum, 0) + 1
    
    return count


# Test
if __name__ == "__main__":
    print(subarray_sum_equals_k([1, 2, 3, 4, 5], 5))  # Output: 2
    print(subarray_sum_equals_k([1, 1, 1], 2))         # Output: 2
    print(subarray_sum_equals_k([1, -1, 0], 0))        # Output: 3
