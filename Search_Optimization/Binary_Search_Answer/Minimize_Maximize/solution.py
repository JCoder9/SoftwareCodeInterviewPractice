"""
Binary Search - On Answer Space

Problem: Find optimal value by binary searching the answer space.

Pattern: When asked "find minimum/maximum X such that condition holds"
1. Identify search space [left, right]
2. Define check function: can we achieve X?
3. Binary search to find boundary

Common Applications:
- Minimum capacity to ship packages in D days
- Koko eating bananas (minimum speed)
- Split array into m subarrays (minimize max sum)

Time Complexity: O(n log(max - min))
Space Complexity: O(1)
"""

from typing import List
import math


def find_peak_element(nums: List[int]) -> int:
    """
    Find any peak element (nums[i] > nums[i-1] and nums[i] > nums[i+1]).
    
    Strategy: Binary search, go towards higher neighbor.
    
    Time: O(log n), Space: O(1)
    """
    left, right = 0, len(nums) - 1
    
    while left < right:
        mid = (left + right) // 2
        
        if nums[mid] < nums[mid + 1]:
            left = mid + 1  # Peak is on the right
        else:
            right = mid  # Peak is on the left or at mid
    
    return left




if __name__ == "__main__":
    print("Min eating speed [3,6,7,11] h=8:", min_eating_speed([3, 6, 7, 11], 8))  # 4
    print("Min ship capacity [1,2,3,4,5,6,7,8,9,10] days=5:", 
          min_capacity_ship_packages([1,2,3,4,5,6,7,8,9,10], 5))  # 15
    print("Split array [7,2,5,10,8] k=2:", split_array_min_max_sum([7, 2, 5, 10, 8], 2))  # 18
    print("Find peak [1,2,3,1]:", find_peak_element([1, 2, 3, 1]))  # 2
