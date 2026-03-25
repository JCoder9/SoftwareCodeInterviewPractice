"""
1D Dynamic Programming - Core Patterns

Problem: Solve problems with 1D state space using DP.

Common Patterns:
1. Climbing stairs (Fibonacci-like)
2. House robber (can't take adjacent)
3. Jump game (reachability)
4. Decode ways (string partitioning)

General Strategy:
- Define dp[i] = optimal solution for subproblem ending at i
- Find recurrence relation
- Initialize base cases
- Fill array bottom-up

Time Complexity: O(n)
Space Complexity: O(n), often optimizable to O(1)
"""

from typing import List


def longest_increasing_subsequence(nums: List[int]) -> int:
    """
    Find length of longest strictly increasing subsequence.
    
    DP Approach:
    dp[i] = length of LIS ending at i
    dp[i] = max(dp[j] + 1) for all j < i where nums[j] < nums[i]
    
    Time: O(n^2), Space: O(n)
    (Note: O(n log n) solution exists using binary search)
    """
    if not nums:
        return 0
    
    n = len(nums)
    dp = [1] * n
    
    for i in range(1, n):
        for j in range(i):
            if nums[j] < nums[i]:
                dp[i] = max(dp[i], dp[j] + 1)
    
    return max(dp)




if __name__ == "__main__":
    print("Climb stairs (5):", climb_stairs(5))  # 8
    print("Rob houses [1,2,3,1]:", rob_houses([1, 2, 3, 1]))  # 4
    print("Can jump [2,3,1,1,4]:", can_jump([2, 3, 1, 1, 4]))  # True
    print("Min jumps [2,3,1,1,4]:", min_jumps([2, 3, 1, 1, 4]))  # 2
    print("Decode ways '226':", decode_ways("226"))  # 3
    print("Word break 'leetcode':", word_break("leetcode", ["leet", "code"]))  # True
    print("LIS [10,9,2,5,3,7,101,18]:", longest_increasing_subsequence([10, 9, 2, 5, 3, 7, 101, 18]))  # 4
