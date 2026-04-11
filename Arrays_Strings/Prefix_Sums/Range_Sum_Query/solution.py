"""
Range Sum Query - Prefix Sum Pattern

Problem: Given an array, answer multiple queries for sum of elements in range [left, right].

Pattern: Build prefix sum array where prefix[i] = sum of nums[0...i-1].
         Then range_sum(left, right) = prefix[right+1] - prefix[left]

Time Complexity: O(n) preprocessing, O(1) per query
Space Complexity: O(n) for prefix array
"""

from typing import List

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(q × n) time | O(1) space
#   where q = number of queries, n = array length
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force loops through the range for each query to calculate
#                   the sum — O(n) per query, O(q × n) for q queries"
#   2. Problem:    "For 10⁴ queries on array of 10⁴ elements, we'd do ~10⁸ operations"
#   3. Transition: "With prefix sums we precompute cumulative sums once in O(n),
#                   then answer each query in O(1) — total O(n + q)"
#
# class PrefixSumNaive:
#     def __init__(self, nums):
#         self.nums = nums
#     
#     def range_sum(self, left, right):
#         return sum(self.nums[left:right + 1])
# ─────────────────────────────────────────────────────────────────────────

class PrefixSum:
    """Build prefix sum array for efficient range queries."""
    
    def __init__(self, nums):
        """Build prefix sum array"""
        self.prefix = [0]  # Start with 0 for easier calculation
        for num in nums:
            self.prefix.append(self.prefix[-1] + num)
    
    def range_sum(self, left, right):
        """Get sum from index left to right (inclusive)"""
        return self.prefix[right + 1] - self.prefix[left]


# Example usage
if __name__ == "__main__":
    nums = [3, 1, 4, 2, 5]
    ps = PrefixSum(nums)
    print(ps.range_sum(1, 3))  # Sum of [1,4,2] = 7
    print(ps.range_sum(0, 4))  # Sum of entire array = 15
