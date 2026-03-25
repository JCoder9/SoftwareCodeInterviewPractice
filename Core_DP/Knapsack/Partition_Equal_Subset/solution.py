"""
Partition Equal Subset Sum

Pattern: Can you split the array into two subsets with equal sum?

Time Complexity: O(n * sum)
Space Complexity: O(sum)
"""

def can_partition(nums):
    """
    Time: O(n * sum/2) = O(n * sum)
    Space: O(sum/2) = O(sum)
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

# Example
if __name__ == "__main__":
    nums = [1, 5, 11, 5]
    print(can_partition(nums))  # True ([1, 5, 5] and [11])
