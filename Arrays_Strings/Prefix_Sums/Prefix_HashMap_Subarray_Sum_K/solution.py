"""
Prefix Sum with HashMap - Subarray Sum Equals K

Problem: Count subarrays with sum equal to target K.

Key Insight: If prefix_sum[j] - prefix_sum[i] = k, then subarray from i+1 to j sums to k.
So we look for (current_prefix_sum - k) in our hashmap!

Time Complexity: O(n) - single pass
Space Complexity: O(n) - worst case all unique prefix sums
"""

from typing import List


def subarray_sum_equals_k(nums, k):
    """Count subarrays with sum equal to k"""
    count = 0
    current_sum = 0
    # HashMap: prefix_sum -> how many times we've seen it
    prefix_count = {0: 1}  # Empty subarray has sum 0
    
    for num in nums:
        current_sum += num
        
        # If (current_sum - k) exists, we found subarrays!
        # Because: current_sum - previous_sum = k
        if current_sum - k in prefix_count:
            count += prefix_count[current_sum - k]
        
        # Add current prefix sum to map
        prefix_count[current_sum] = prefix_count.get(current_sum, 0) + 1
    
    return count


# Example usage
if __name__ == "__main__":
    print(subarray_sum_equals_k([1, 2, 3, 4, 5], 9))  # 2 subarrays: [2,3,4] and [4,5]
    print(subarray_sum_equals_k([1, -1, 1, -1], 0))  # 4 subarrays
