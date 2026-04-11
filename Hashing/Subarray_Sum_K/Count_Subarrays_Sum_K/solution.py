"""
Subarray Sum Equals K - Prefix Sum + Hash Map Pattern

Problem: Given an array of integers and an integer k, count how many continuous subarrays sum to k.

Pattern: Use prefix sum + hash map. If prefixSum[j] - prefixSum[i] = k, then subarray[i..j] sums to k.
         Track all prefix sums seen so far in a map.

Time Complexity: O(n) - single pass through array
Space Complexity: O(n) - hash map stores up to n prefix sums
"""

from typing import List

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks every subarray by calculating its sum
#                   — O(n²) time with nested loops"
#   2. Problem:    "For n=10⁴, we'd do ~10⁸ operations; too slow"
#   3. Transition: "Key insight: if currSum - k exists in our map, we found a
#                   subarray. Track prefix sums in hash map — drops to O(n)"
#
# def subarray_sum_equals_k_naive(nums, k):
#     count = 0
#     for i in range(len(nums)):
#         current_sum = 0
#         for j in range(i, len(nums)):
#             current_sum += nums[j]
#             if current_sum == k:
#                 count += 1
#     return count
# ─────────────────────────────────────────────────────────────────────────


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
