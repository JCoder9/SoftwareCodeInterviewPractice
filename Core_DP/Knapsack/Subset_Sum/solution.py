"""
Subset Sum Problem

Pattern: Can you select items that sum to EXACTLY the target?

Time Complexity: O(n * target)
Space Complexity: O(target)
"""

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
