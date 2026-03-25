"""
Knapsack DP - Target Sum

The Rule: Add + or - before each number to reach a target sum.

Related LeetCode Problems:
- LC 494: Target Sum (Medium)
- LC 1049: Last Stone Weight II (Medium)

Key Insight: This transforms into subset sum!
Let P = sum of positive numbers, N = sum of negative numbers
P - N = target
P + N = total
=> P = (target + total) / 2

Find count of subsets that sum to P.

Time Complexity: O(n * sum)
Space Complexity: O(sum)
"""

from typing import List

def find_target_sum_ways(nums: List[int], target: int) -> int:
    """
    LC 494: Target Sum.
    Count ways to add +/- to reach target.
    
    Time: O(n * sum)
    Space: O(sum)
    """
    total = sum(nums)
    
    # Check if solution is possible
    if abs(target) > total or (target + total) % 2 != 0:
        return 0
    
    subset_sum = (target + total) // 2
    
    # dp[s] = number of ways to achieve sum s
    dp = [0] * (subset_sum + 1)
    dp[0] = 1  # One way to make 0: select nothing
    
    for num in nums:
        for s in range(subset_sum, num - 1, -1):
            dp[s] += dp[s - num]
    
    return dp[subset_sum]


def find_target_sum_ways_2d(nums: List[int], target: int) -> int:
    """
    2D version for clarity.
    """
    total = sum(nums)
    
    if abs(target) > total or (target + total) % 2 != 0:
        return 0
    
    subset_sum = (target + total) // 2
    n = len(nums)
    
    dp = [[0] * (subset_sum + 1) for _ in range(n + 1)]
    dp[0][0] = 1
    
    for i in range(1, n + 1):
        for s in range(subset_sum + 1):
            # Don't take nums[i-1]
            dp[i][s] = dp[i-1][s]
            
            # Take nums[i-1]
            if nums[i-1] <= s:
                dp[i][s] += dp[i-1][s - nums[i-1]]
    
    return dp[n][subset_sum]


def find_target_sum_brute_force(nums: List[int], target: int) -> int:
    """
    Brute force backtracking (for comparison).
    Time: O(2^n)
    """
    count = 0
    
    def backtrack(index, current_sum):
        nonlocal count
        
        if index == len(nums):
            if current_sum == target:
                count += 1
            return
        
        # Try adding +nums[index]
        backtrack(index + 1, current_sum + nums[index])
        # Try adding -nums[index]
        backtrack(index + 1, current_sum - nums[index])
    
    backtrack(0, 0)
    return count


# Test cases
if __name__ == "__main__":
    nums1 = [1, 1, 1, 1, 1]
    target1 = 3
    print(f"Ways to reach {target1}: {find_target_sum_ways(nums1, target1)}")  # 5
    # +1+1+1+1-1 = 3
    # +1+1+1-1+1 = 3
    # +1+1-1+1+1 = 3
    # +1-1+1+1+1 = 3
    # -1+1+1+1+1 = 3
    
    nums2 = [1]
    target2 = 1
    print(f"Ways to reach {target2}: {find_target_sum_ways(nums2, target2)}")  # 1
    
    nums3 = [2, 3, 1]
    target3 = 2
    print(f"Ways to reach {target3}: {find_target_sum_ways(nums3, target3)}")  # 2
    
    # Compare with brute force
    print(f"\nBrute force: {find_target_sum_brute_force(nums1, target1)}")  # 5
