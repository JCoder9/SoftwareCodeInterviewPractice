"""
Product Except Self - Prefix/Suffix Product Pattern

Problem: Given array nums, return array where output[i] = product of all elements except nums[i].
         Must run in O(n) without using division.

Pattern: Use prefix products (left) and suffix products (right).
         result[i] = prefix[i-1] × suffix[i+1]

Related LeetCode Problems:
- LC 238: Product of Array Except Self (Medium) ⭐⭐⭐
- LC 152: Maximum Product Subarray (Medium)
- LC 713: Subarray Product Less Than K (Medium)

Time Complexity: O(n) - two passes
Space Complexity: O(1) - output array doesn't count
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force calculates product for each position by multiplying
#                  all other elements — nested loops give O(n²)"
#   2. Problem:    "For n=10,000: 100 million operations; too slow for large arrays"
#   3. Transition: "With prefix/suffix products, precompute left and right products
#                  — reduces to O(n) with two passes"
#
# def product_except_self_naive(nums):
#     n = len(nums)
#     result = [0] * n
#     
#     for i in range(n):
#         product = 1
#         for j in range(n):
#             if i != j:
#                 product *= nums[j]
#         result[i] = product
#     return result
# ─────────────────────────────────────────────────────────────────────────────

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
