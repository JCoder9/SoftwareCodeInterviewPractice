"""
3Sum - Sort + Two Pointers Pattern

Problem: Find all unique triplets that sum to zero.
         Example: nums = [-1,0,1,2,-1,-4] → [[-1,-1,2],[-1,0,1]]

Pattern: 1. Sort array
         2. Fix first element, use two pointers for remaining pair
         3. Skip duplicates to avoid duplicate triplets

Related LeetCode Problems:
- LC 15: 3Sum (Medium) ⭐⭐⭐
- LC 16: 3Sum Closest (Medium)
- LC 18: 4Sum (Medium)
- LC 259: 3Sum Smaller (Medium)

Time Complexity: O(n²) - O(n log n) sort + O(n²) two pointers
Space Complexity: O(1) or O(n) for result (depending on requirements)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n³) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks all triplet combinations with 3 nested loops,
#                  uses set to avoid duplicates — O(n³)"
#   2. Problem:    "For n=100: 100³ = 1 million comparisons; for n=1000: 1 billion
#                  comparisons; duplicate detection is expensive"
#   3. Transition: "Sort first (O(n log n)), fix one element, use two pointers
#                  for pair (O(n²)), skip duplicates during iteration"
#
# def three_sum_naive(nums):
#     result = []
#     seen = set()
#     n = len(nums)
#     
#     for i in range(n):
#         for j in range(i + 1, n):
#             for k in range(j + 1, n):
#                 if nums[i] + nums[j] + nums[k] == 0:
#                     triplet = tuple(sorted([nums[i], nums[j], nums[k]]))
#                     if triplet not in seen:
#                         seen.add(triplet)
#                         result.append(list(triplet))
#     return result
# ─────────────────────────────────────────────────────────────────────────────

from typing import List


def threeSum(nums: List[int]) -> List[List[int]]:
    """
    Find all unique triplets that sum to zero.
    
    Algorithm:
    1. Sort the array
    2. Fix first element (i)
    3. Use two pointers (left, right) to find pair that sums to -nums[i]
    4. Skip duplicates at each level
    """
    nums.sort()
    result = []
    n = len(nums)
    
    for i in range(n - 2):
        # Skip duplicate first elements
        if i > 0 and nums[i] == nums[i - 1]:
            continue
        
        # Two pointers for the remaining pair
        left = i + 1
        right = n - 1
        target = -nums[i]
        
        while left < right:
            current_sum = nums[left] + nums[right]
            
            if current_sum == target:
                result.append([nums[i], nums[left], nums[right]])
                
                # Skip duplicates for left pointer
                while left < right and nums[left] == nums[left + 1]:
                    left += 1
                # Skip duplicates for right pointer
                while left < right and nums[right] == nums[right - 1]:
                    right -= 1
                
                left += 1
                right -= 1
            elif current_sum < target:
                left += 1
            else:
                right -= 1
    
    return result


def threeSumClosest(nums: List[int], target: int) -> int:
    """
    LC 16: Find triplet sum closest to target.
    """
    nums.sort()
    n = len(nums)
    closest_sum = float('inf')
    
    for i in range(n - 2):
        left = i + 1
        right = n - 1
        
        while left < right:
            current_sum = nums[i] + nums[left] + nums[right]
            
            # Update closest if current is closer
            if abs(current_sum - target) < abs(closest_sum - target):
                closest_sum = current_sum
            
            if current_sum < target:
                left += 1
            elif current_sum > target:
                right -= 1
            else:
                return current_sum  # Exact match
    
    return closest_sum


# Example usage
if __name__ == "__main__":
    # LC 15: 3Sum
    print(threeSum([-1, 0, 1, 2, -1, -4]))
    # Output: [[-1, -1, 2], [-1, 0, 1]]
    
    print(threeSum([0, 1, 1]))
    # Output: []
    
    print(threeSum([0, 0, 0]))
    # Output: [[0, 0, 0]]
    
    # LC 16: 3Sum Closest
    print(threeSumClosest([-1, 2, 1, -4], 1))
    # Output: 2 (sum of -1 + 2 + 1 = 2)
