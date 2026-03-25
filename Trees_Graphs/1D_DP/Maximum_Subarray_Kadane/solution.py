"""
Maximum Subarray (Kadane's Algorithm)

Pattern: Find contiguous subarray with largest sum - decide to continue or start fresh

Time Complexity: O(n)
Space Complexity: O(n) for array version, O(1) for optimized version
"""

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
