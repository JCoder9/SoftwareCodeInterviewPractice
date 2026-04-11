"""
1D Dynamic Programming - House Robber Pattern

Problem: Rob houses in a row, each with money. Can't rob adjacent houses (alarm triggered).
         Maximize total money robbed.

Pattern: At each house, choose: rob it (+ skip previous) or skip it (keep previous max).
         dp[i] = max(nums[i] + dp[i-2], dp[i-1])

Related LeetCode Problems:
- LC 198: House Robber (Medium)
- LC 213: House Robber II (Medium) - circular
- LC 740: Delete and Earn (Medium)
- LC 2320: Count Number of Ways to Place Houses (Medium)

Time Complexity: O(n)
Space Complexity: O(1) optimized
"""

# ───────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(2^n) time | O(n) space
# ───────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries every valid subset: for each house, recursively
#                   rob it (skip next) or skip it — O(2^n) exponential"
#   2. Problem:    "For 20 houses, explores over 1 million combinations; overlapping
#                   subproblems computed repeatedly"
#   3. Transition: "With DP, track max at each position based on previous two decisions
#                   — O(n) time, O(1) space"
#
# def rob_naive(nums, i=0):
#     if i >= len(nums):
#         return 0
#     # Two choices: rob this house or skip it
#     rob_current = nums[i] + rob_naive(nums, i + 2)
#     skip_current = rob_naive(nums, i + 1)
#     return max(rob_current, skip_current)
# ───────────────────────────────────────────────────────────────────────────

from typing import List


def rob(nums: List[int]) -> int:
    """
    Can't rob adjacent houses. Maximize money robbed.
    dp[i] = max(nums[i] + dp[i-2], dp[i-1])
    """
    if not nums:
        return 0
    if len(nums) <= 2:
        return max(nums)
    
    # prev2 = dp[i-2], prev1 = dp[i-1]
    prev2, prev1 = nums[0], max(nums[0], nums[1])
    
    for i in range(2, len(nums)):
        current = max(nums[i] + prev2, prev1)
        prev2, prev1 = prev1, current
    
    return prev1




if __name__ == "__main__":
    # Test LC 198
    nums1 = [1, 2, 3, 1]
    print("House robber:", rob(nums1))  # 4 (rob house 0 and 2)
    
    nums2 = [2, 7, 9, 3, 1]
