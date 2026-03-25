"""
Knapsack DP - Subset Sum Problem

The Rule: Can you select items that sum to EXACTLY the target?

Related LeetCode Problems:
- LC 416: Partition Equal Subset Sum (Medium)
- LC 698: Partition to K Equal Sum Subsets (Medium)

Key Insight: Boolean DP checking if sum is achievable.
- dp[s] = True if we can make sum s
- For each number, update all possible sums (backwards to avoid reuse)

Time Complexity: O(n * target)
Space Complexity: O(target)
"""

from typing import List


def subset_sum(nums: List[int], target: int) -> bool:
    """
    Returns True if any subset sums to target.
    Time: O(n * target)
    Space: O(target)
    """
    dp = [False] * (target + 1)
    dp[0] = True  # Empty subset sums to 0
    
    for num in nums:
        # Go backwards (0/1 knapsack style)
        for s in range(target, num - 1, -1):
            dp[s] = dp[s] or dp[s - num]
    
    return dp[target]




if __name__ == "__main__":
    nums = [3, 34, 4, 12, 5, 2]
    target = 9
    print(f"Can make {target}: {subset_sum(nums, target)}")  # True (4 + 5)
    
    nums2 = [2, 3, 5, 8]
