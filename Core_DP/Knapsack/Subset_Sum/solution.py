"""
Subset Sum Problem

Problem: Given array of integers and target sum, can you select a subset that sums exactly to target?

Pattern: Boolean DP where dp[s] = True if sum s is achievable.
         For each number, update all sums we can now reach.

Related LeetCode Problems:
- LC 416: Partition Equal Subset Sum (Medium) ⭐⭐⭐
- LC 494: Target Sum (Medium)
- Practice problem for 0/1 Knapsack

Time Complexity: O(n × target)
Space Complexity: O(target)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force generates all 2^n subsets and checks each sum
#                  — exponential time"
#   2. Problem:    "For n=25: 33 million subsets; many overlapping subproblems"
#   3. Transition: "DP tracks achievable sums iteratively; each number considered once
#                  — O(n × target)"
#
# def subset_sum_naive(nums, target, i=0, current_sum=0):
#     if current_sum == target:
#         return True
#     if i >= len(nums) or current_sum > target:
#         return False
#     # Include nums[i] or exclude it
#     return (subset_sum_naive(nums, target, i + 1, current_sum + nums[i]) or
#             subset_sum_naive(nums, target, i + 1, current_sum))
# ─────────────────────────────────────────────────────────────────────────────

def subset_sum(nums, target):
    """
    Time: O(n * target)
    Space: O(target)
    Returns True if any subset sums to target
    """
    dp = [False] * (target + 1)
    dp[0] = True  # Empty subset sums to 0
    
    for num in nums:
        # Go backwards (0/1 knapsack style)
        for s in range(target, num - 1, -1):
            dp[s] = dp[s] or dp[s - num]
    
    return dp[target]

# Example
if __name__ == "__main__":
    nums = [3, 34, 4, 12, 5, 2]
    target = 9
    print(subset_sum(nums, target))  # True (4 + 5 = 9)
