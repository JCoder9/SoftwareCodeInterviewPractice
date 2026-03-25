"""
Product Less Than K - Sliding Window Variant

Problem: Count/find subarrays where product < k.

This is actually a SLIDING WINDOW problem, not traditional prefix sum!
But it's related since we track cumulative products.

Time Complexity: O(n)
Space Complexity: O(1)
"""

from typing import List


def subarray_product_less_than_k(nums, k):
    """Count subarrays with product < k using sliding window"""
    if k <= 1:
        return 0
    
    count = 0
    product = 1
    left = 0
    
    for right in range(len(nums)):
        product *= nums[right]
        
        while product >= k:
            product /= nums[left]
            left += 1
        
        # All subarrays ending at right
        count += right - left + 1
    
    return count


# Example usage
if __name__ == "__main__":
    print(subarray_product_less_than_k([10, 5, 2, 6], 100))  # 8
