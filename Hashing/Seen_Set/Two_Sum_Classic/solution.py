"""
Two Sum - Classic Complement Search Pattern

Problem: Given an array of integers and a target, find two numbers that add up to the target.
         Return the indices of the two numbers.

Pattern: Use hash map to store seen values and check for complement (target - current).

Time Complexity: O(n) - single pass
Space Complexity: O(n) - storing values in map
"""

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks every pair of numbers to see if they
#                   sum to target — O(n²) time with nested loops"
#   2. Problem:    "For n=10⁴, we'd do ~10⁸ comparisons; too slow"
#   3. Transition: "With a hash map we track seen values and check for the
#                   complement in O(1) — drops to O(n) time"
#
# def two_sum_naive(nums, target):
#     for i in range(len(nums)):
#         for j in range(i + 1, len(nums)):
#             if nums[i] + nums[j] == target:
#                 return [i, j]
#     return []  # No solution found
# ─────────────────────────────────────────────────────────────────────────

def two_sum(nums, target):
    """
    LeetCode 1: Two Sum
    
    Find two numbers that add up to target.
    Return their indices.
    """
    seen = {}  # value -> index
    
    for i, num in enumerate(nums):
        complement = target - num
        
        if complement in seen:
            return [seen[complement], i]
        
        seen[num] = i
    
    return []


# Test cases
if __name__ == "__main__":
    print(two_sum([2, 7, 11, 15], 9))   # [0, 1]
    print(two_sum([3, 2, 4], 6))        # [1, 2]
