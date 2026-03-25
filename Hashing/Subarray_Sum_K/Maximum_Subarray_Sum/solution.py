"""
Maximum Subarray Sum - Kadane's Algorithm Pattern

Pattern: Dynamic programming / greedy approach for max sum.

Time Complexity: O(n)
Space Complexity: O(1)
"""

from typing import List


def max_subarray_sum(nums):
    """
    Find maximum sum of any subarray (Kadane's Algorithm)
    """
    max_sum = float('-inf')  # Overall maximum
    current_sum = 0           # Current subarray sum
    
    for num in nums:
        # Either extend current subarray or start new one
        current_sum = max(num, current_sum + num)
        
        # Update overall maximum
        max_sum = max(max_sum, current_sum)
    
    return max_sum


# Test
if __name__ == "__main__":
    print(max_subarray_sum([-2, 1, -3, 4, -1, 2, 1, -5, 4]))  # 6 ([4,-1,2,1])
    print(max_subarray_sum([1]))                               # 1
    print(max_subarray_sum([5, 4, -1, 7, 8]))                  # 23 (entire array)
