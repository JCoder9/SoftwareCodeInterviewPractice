"""
Target Sum (Add +/- to reach target)

Problem: Assign + or - before each number to make their sum equal target.
         Count how many ways this can be done.

Pattern: Transforms into subset sum! If P = positive subset, N = negative subset:
         P - N = target and P + N = total ⇒ P = (target + total) / 2
         Count subsets that sum to P.

Related LeetCode Problems:
- LC 494: Target Sum (Medium) ⭐⭐⭐
- LC 416: Partition Equal Subset Sum (Medium)

Time Complexity: O(n × sum)
Space Complexity: O(sum)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all 2^n ways to assign +/- to each number
#                  — exponential time"
#   2. Problem:    "For n=20: 1 million combinations; many redundant calculations"
#   3. Transition: "Math reduces it to subset sum problem; DP counts subsets
#                  — O(n × target)"
#
# def find_target_sum_ways_naive(nums, target, i=0, current_sum=0):
#     if i == len(nums):
#         return 1 if current_sum == target else 0
#     # Add or subtract nums[i]
#     return (find_target_sum_ways_naive(nums, target, i + 1, current_sum + nums[i]) +
#             find_target_sum_ways_naive(nums, target, i + 1, current_sum - nums[i]))
# ─────────────────────────────────────────────────────────────────────────────

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
