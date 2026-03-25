"""
1D Dynamic Programming - Longest Increasing Subsequence (LIS)

Related LeetCode Problems:
- LC 300: Longest Increasing Subsequence (Medium)
- LC 673: Number of Longest Increasing Subsequence (Medium)
- LC 354: Russian Doll Envelopes (Hard) - 2D LIS
- LC 1671: Minimum Number of Removals to Make Mountain Array (Hard)

Key Insight: For each position, find longest increasing subsequence ending there.
- dp[i] = length of LIS ending at i
- For each i, check all j < i where nums[j] < nums[i]
- O(n²) DP solution, O(n log n) with binary search

Time Complexity: O(n²) or O(n log n) with binary search
Space Complexity: O(n)
"""

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
