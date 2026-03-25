"""
Knapsack DP - Partition Equal Subset Sum

The Rule: Can you split the array into two subsets with equal sum?

Related LeetCode Problems:
- LC 416: Partition Equal Subset Sum (Medium)
- LC 805: Split Array With Same Average (Hard)
- LC 1981: Minimize the Difference Between Target and Chosen Elements (Medium)

Key Insight: If total sum is odd, impossible. Otherwise, check if subset_sum(total/2) exists.
- Reduces to: Can we find a subset that sums to total/2?
- If yes, remaining elements also sum to total/2

Time Complexity: O(n * sum)
Space Complexity: O(sum)
"""

from typing import List

def can_partition(nums: List[int]) -> bool:
    """
    LC 416: Partition Equal Subset Sum.
    Time: O(n * sum)
    Space: O(sum)
    """
    total = sum(nums)
    
    # If total is odd, can't split evenly
    if total % 2 != 0:
        return False
    
    target = total // 2
    dp = [False] * (target + 1)
    dp[0] = True
    
    for num in nums:
        for s in range(target, num - 1, -1):
            dp[s] = dp[s] or dp[s - num]
    
    return dp[target]


def can_partition_k_subsets(nums: List[int], k: int) -> bool:
    """
    Can partition into k equal sum subsets?
    Similar idea but more complex.
    """
    total = sum(nums)
    
    if total % k != 0:
        return False
    
    target = total // k
    nums.sort(reverse=True)
    
    # If any number > target, impossible
    if nums[0] > target:
        return False
    
    used = [False] * len(nums)
    
    def backtrack(k_remaining, current_sum, start):
        if k_remaining == 0:
            return True
        
        if current_sum == target:
            # Found one subset, continue with next
            return backtrack(k_remaining - 1, 0, 0)
        
        for i in range(start, len(nums)):
            if used[i] or current_sum + nums[i] > target:
                continue
            
            used[i] = True
            if backtrack(k_remaining, current_sum + nums[i], i + 1):
                return True
            used[i] = False
        
        return False
    
    return backtrack(k, 0, 0)


def min_subset_sum_difference(nums: List[int]) -> int:
    """
    Minimize difference between two partition sums.
    Find subset closest to total/2.
    """
    total = sum(nums)
    target = total // 2
    
    dp = [False] * (target + 1)
    dp[0] = True
    
    for num in nums:
        for s in range(target, num - 1, -1):
            dp[s] = dp[s] or dp[s - num]
    
    # Find largest sum <= target that's achievable
    for s in range(target, -1, -1):
        if dp[s]:
            # One subset = s, other = total - s
            return abs((total - s) - s)
    
    return total


# Test cases
if __name__ == "__main__":
    # LC 416
    nums1 = [1, 5, 11, 5]
    print(f"Can partition {nums1}: {can_partition(nums1)}")  # True ([1, 5, 5] and [11])
    
    nums2 = [1, 2, 3, 5]
    print(f"Can partition {nums2}: {can_partition(nums2)}")  # False
    
    # Partition into k subsets
    nums3 = [4, 3, 2, 3, 5, 2, 1]
    k = 4
    print(f"\nCan partition into {k} subsets: {can_partition_k_subsets(nums3, k)}")  # True
    
    # Min difference
    nums4 = [1, 6, 11, 5]
    print(f"\nMin difference for {nums4}: {min_subset_sum_difference(nums4)}")  # 1
