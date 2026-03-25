"""
Target Sum (Add +/- to reach target)

Pattern: Add + or - before each number to reach a target sum

Time Complexity: O(n * sum)
Space Complexity: O(sum)
"""

def find_target_sum_ways(nums, target):
    """
    Time: O(n * sum) where sum is the total of all numbers
    Space: O(sum)
    
    Key insight: This transforms into subset sum!
    Let P = sum of positive numbers, N = sum of negative numbers
    P - N = target
    P + N = total
    => P = (target + total) / 2
    
    Find count of subsets that sum to P
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

# Example
if __name__ == "__main__":
    nums = [1, 1, 1, 1, 1]
    target = 3
    print(find_target_sum_ways(nums, target))  # 5 ways
