"""
1D Dynamic Programming - Longest Increasing Subsequence (LIS)

Problem: Find the length of the longest strictly increasing subsequence in an array.
         Subsequence doesn't need to be contiguous.
         Example: [10, 9, 2, 5, 3, 7, 101, 18] → LIS is [2, 3, 7, 101], length = 4

Pattern: dp[i] = length of LIS ending at index i.
         For each i, check all j < i where nums[j] < nums[i].

Related LeetCode Problems:
- LC 300: Longest Increasing Subsequence (Medium) ⭐⭐⭐
- LC 673: Number of Longest Increasing Subsequence (Medium)
- LC 354: Russian Doll Envelopes (Hard) - 2D LIS
- LC 1671: Minimum Number of Removals to Make Mountain Array (Hard)

Time Complexity: O(n²) or O(n log n) with binary search
Space Complexity: O(n)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force generates all 2^n subsequences, checks each if
#                  strictly increasing — exponential time"
#   2. Problem:    "For n=20: over 1 million subsequences; for n=30: over 1 billion;
#                  overlapping subproblems"
#   3. Transition: "DP tracks LIS ending at each position; O(n²) checks or O(n log n)
#                  with binary search optimization"
#
# def length_of_lis_naive(nums):
#     max_len = 0
#     
#     def check_subsequence(indices):
#         if not indices:
#             return 0
#         for i in range(1, len(indices)):
#             if nums[indices[i]] <= nums[indices[i-1]]:
#                 return 0
#         return len(indices)
#     
#     # Try all 2^n subsequences
#     for mask in range(1 << len(nums)):
#         indices = [i for i in range(len(nums)) if mask & (1 << i)]
#         max_len = max(max_len, check_subsequence(indices))
#     return max_len
# ─────────────────────────────────────────────────────────────────────────────

from typing import List
import bisect


def lengthOfLIS(nums: List[int]) -> int:
    """
    Find length of longest strictly increasing subsequence.
    dp[i] = LIS length ending at index i
    """
    if not nums:
        return 0
    
    n = len(nums)
    dp = [1] * n  # Each element is LIS of length 1
    
    for i in range(1, n):
        for j in range(i):
            if nums[j] < nums[i]:
                dp[i] = max(dp[i], dp[j] + 1)
    
    return max(dp)




if __name__ == "__main__":
    # Test LC 300
    nums1 = [10, 9, 2, 5, 3, 7, 101, 18]
    print("LIS length:", lengthOfLIS(nums1))  # 4: [2, 3, 7, 101]
    print("LIS length (optimized):", lengthOfLIS_optimized(nums1))
