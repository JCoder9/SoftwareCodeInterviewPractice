"""
1D Dynamic Programming - House Robber Pattern

Related LeetCode Problems:
- LC 198: House Robber (Medium)
- LC 213: House Robber II (Medium) - circular
- LC 740: Delete and Earn (Medium)
- LC 2320: Count Number of Ways to Place Houses (Medium)

Key Insight: Can't take adjacent elements.
- dp[i] = max(rob i + dp[i-2], skip i + dp[i-1])
- Choice: rob current house or skip it
- For circular: solve twice excluding first or last house

Time Complexity: O(n)
Space Complexity: O(1) optimized
"""

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
