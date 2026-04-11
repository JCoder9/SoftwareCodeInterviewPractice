"""
Count Subarrays with Exactly K Distinct - Trick: exactlyK = atMostK - atMost(K-1)

Problem: Count the number of subarrays with exactly K distinct integers.

Pattern: Use the "at most K" sliding window twice and subtract.
         Key insight: exactlyK(arr, K) = atMostK(arr, K) - atMostK(arr, K-1)

Time Complexity: O(n) - two linear passes
Space Complexity: O(k) - frequency map with at most k distinct elements
"""

from collections import defaultdict
from typing import List

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n²) time | O(k) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks every subarray and counts distinct elements
#                   — O(n²) time with O(k) space for the set"
#   2. Problem:    "For n=10⁴, we'd do ~10⁸ operations; needs optimization"
#   3. Transition: "Key insight: exactlyK = atMostK - atMost(K-1). We use two
#                   sliding windows to count in O(n) time instead"
#
# def exactly_k_distinct_naive(nums: List[int], k: int) -> int:
#     count = 0
#     for i in range(len(nums)):
#         distinct = set()
#         for j in range(i, len(nums)):
#             distinct.add(nums[j])
#             if len(distinct) == k:
#                 count += 1
#             elif len(distinct) > k:
#                 break
#     return count
# ─────────────────────────────────────────────────────────────────────────

def subarrays_with_at_most_k_distinct(nums: List[int], k: int) -> int:
    """
    Count subarrays with at most K distinct integers.
    
    Args:
        nums: Input array
        k: Maximum number of distinct integers
        
    Returns:
        Count of valid subarrays
    """
    if k < 0:
        return 0  # No valid subarrays
    
    count = defaultdict(int)
    left = 0
    res = 0

    for right, x in enumerate(nums):
        count[x] += 1
        
        # Shrink window while we have too many distinct integers
        while len(count) > k:
            count[nums[left]] -= 1
            if count[nums[left]] == 0:
                del count[nums[left]]
            left += 1
        
        # All subarrays ending at right with start in [left..right] are valid
        res += right - left + 1
    
    return res


def subarrays_with_exactly_k_distinct(nums: List[int], k: int) -> int:
    """
    Count subarrays with exactly K distinct integers.
    
    Args:
        nums: Input array
        k: Exact number of distinct integers required
        
    Returns:
        Count of valid subarrays
    """
    if not nums or k <= 0:
        return 0
    
    # exactlyK = atMostK - atMost(K-1)
    return (subarrays_with_at_most_k_distinct(nums, k) - 
            subarrays_with_at_most_k_distinct(nums, k - 1))


# Test cases
if __name__ == "__main__":
    test_cases = [
        ([1, 2, 1, 2, 3], 2, 7),   # Subarrays: [1,2], [2,1], [1,2], [2,1], [1,2,1], [2,1,2], [1,2,1,2]
        ([1, 2, 1, 3, 4], 3, 3),   # [1,2,1,3], [2,1,3], [1,3,4]
        ([1], 1, 1),               # [1]
        ([1, 1, 1], 1, 6),         # All subarrays: [1], [1], [1], [1,1], [1,1], [1,1,1] = 6
    ]
    
    for nums, k, expected in test_cases:
        result = subarrays_with_exactly_k_distinct(nums, k)
        status = "✓" if result == expected else "✗"
        print(f"{status} subarrays_with_exactly_k_distinct({nums}, k={k}) = {result} (expected {expected})")
