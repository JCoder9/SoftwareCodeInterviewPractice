"""
Minimum Length Subarray with Sum >= S - Variable-Size Sliding Window

Problem: Find the minimum length of a contiguous subarray whose sum is >= S.

Pattern: Variable window - expand to include elements, shrink when valid to minimize.

Time Complexity: O(n) - each element visited at most twice (once by right, once by left)
Space Complexity: O(1) - only tracking window sum and pointers
"""

from typing import List

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks every subarray and calculates its sum
#                   — O(n²) time with O(1) space"
#   2. Problem:    "For n=10⁴, we'd do ~10⁸ operations; too slow for large inputs"
#   3. Transition: "With a sliding window we maintain the sum and shrink when valid
#                   (sum >= S) to minimize. Each element visited twice — drops to O(n)"
#
# def min_len_sum_at_least_s_naive(nums: List[int], S: int) -> int:
#     best = float('inf')
#     for i in range(len(nums)):
#         window_sum = 0
#         for j in range(i, len(nums)):
#             window_sum += nums[j]
#             if window_sum >= S:
#                 best = min(best, j - i + 1)
#                 break  # no need to extend further from this starting point
#     return 0 if best == float('inf') else best
# ─────────────────────────────────────────────────────────────────────────

def min_len_sum_at_least_s(nums: List[int], S: int) -> int:
    """
    Find minimum length of subarray with sum >= S.
    
    Args:
        nums: Input array (positive integers)
        S: Target sum threshold
        
    Returns:
        Minimum length, or 0 if no such subarray exists
    """
    if not nums:
        return 0
    
    left = 0
    window_sum = 0
    best = float("inf")  # Minimizing length

    for right, x in enumerate(nums):
        window_sum += x

        # Shrink window while it's valid (sum >= S)
        while window_sum >= S:
            best = min(best, right - left + 1)
            window_sum -= nums[left]
            left += 1

    return 0 if best == float("inf") else best  # Check sentinel


# Test cases
if __name__ == "__main__":
    test_cases = [
        ([2, 3, 1, 2, 4, 3], 7, 2),    # [4, 3] -> length 2
        ([1, 4, 4], 4, 1),              # [4] -> length 1
        ([1, 1, 1, 1, 1], 11, 0),      # No subarray
        ([1, 2, 3, 4, 5], 11, 3),      # [3, 4, 5] -> length 3
        ([5, 1, 3, 5, 10, 7], 15, 2),  # [5, 10] or [10, 7] -> length 2
    ]
    
    for nums, S, expected in test_cases:
        result = min_len_sum_at_least_s(nums, S)
        status = "✓" if result == expected else "✗"
        print(f"{status} min_len_sum_at_least_s({nums}, S={S}) = {result} (expected {expected})")
