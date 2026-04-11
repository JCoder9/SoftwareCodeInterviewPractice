"""
1D Dynamic Programming - Maximum Subarray (Kadane's Algorithm)

Problem: Find the contiguous subarray with the largest sum.

Pattern: At each position, decide: continue current subarray or start fresh.
         current_max = max(nums[i], current_max + nums[i])

Related LeetCode Problems:
- LC 53: Maximum Subarray (Medium)
- LC 918: Maximum Sum Circular Subarray (Medium)
- LC 152: Maximum Product Subarray (Medium)

Time Complexity: O(n)
Space Complexity: O(1)
"""

# ───────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
# ───────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks every possible subarray with nested loops:
#                   try all start positions, then all end positions — O(n²) time"
#   2. Problem:    "For n=10,000, makes 50 million comparisons; too slow for large arrays"
#   3. Transition: "Kadane's algorithm decides at each position: extend or restart
#                   — single pass O(n) time"
#
# def maxSubArray_naive(nums):
#     max_sum = float('-inf')
#     
#     for start in range(len(nums)):
#         current_sum = 0
#         for end in range(start, len(nums)):
#             current_sum += nums[end]
#             max_sum = max(max_sum, current_sum)
#     return max_sum
# ───────────────────────────────────────────────────────────────────────────

def maxSubArray(nums):
    # dp[i] = max sum of subarray ending at index i
    dp = [0] * len(nums)
    dp[0] = nums[0]
    max_sum = nums[0]
    
    for i in range(1, len(nums)):
        # Either extend previous subarray or start new one
        dp[i] = max(nums[i], dp[i-1] + nums[i])
        max_sum = max(max_sum, dp[i])
    
    return max_sum

# Space optimized
def maxSubArray_optimized(nums):
    current_max = nums[0]
    global_max = nums[0]
    
    for i in range(1, len(nums)):
        current_max = max(nums[i], current_max + nums[i])
        global_max = max(global_max, current_max)
    
    return global_max

if __name__ == "__main__":
    print(maxSubArray([-2, 1, -3, 4, -1, 2, 1, -5, 4]))  # Output: 6
