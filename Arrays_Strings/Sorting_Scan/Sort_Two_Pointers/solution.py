"""
Sort + Two Pointers Pattern

Problem: Find pairs/triplets with specific sum after sorting.
         Example: Two Sum in sorted array, 3Sum, 4Sum

Pattern: Sort array first, then use two pointers from opposite ends

Related LeetCode Problems:
- LC 15: 3Sum (Medium) ⭐⭐⭐
- LC 167: Two Sum II - Input Array Is Sorted (Medium)
- LC 16: 3Sum Closest (Medium)

Time Complexity: O(n log n) for sort + O(n²) for 3Sum
Space Complexity: O(1) or O(n) for result storage
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n³) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks all triplet combinations with 3 nested loops —
#                  O(n³) for 3Sum"
#   2. Problem:    "For n=100: 100³ = 1 million comparisons; for n=1000: 1 billion"
#   3. Transition: "Sort first, fix one element, use two pointers for remaining pair —
#                  O(n²)"
#
# def three_sum_naive(nums):
#     result = []
#     n = len(nums)
#     for i in range(n):
#         for j in range(i + 1, n):
#             for k in range(j + 1, n):
#                 if nums[i] + nums[j] + nums[k] == 0:
#                     triplet = sorted([nums[i], nums[j], nums[k]])
#                     if triplet not in result:
#                         result.append(triplet)
#     return result
# ─────────────────────────────────────────────────────────────────────────────

from typing import List


def two_sum_sorted(arr, target):
    """Find two numbers that sum to a target. Array is already sorted."""
    # Array is already sorted, but if not:
    # arr.sort()
    
    left = 0
    right = len(arr) - 1
    
    while left < right:
        current_sum = arr[left] + arr[right]
        
        if current_sum == target:
            return [left, right]  # or the values
        elif current_sum < target:
            left += 1  # need bigger sum
        else:
            right -= 1  # need smaller sum
    
    return [-1, -1]  # not found


# Example
if __name__ == "__main__":
    print(two_sum_sorted([2, 7, 11, 15], 9))  # Output: [0, 1]
