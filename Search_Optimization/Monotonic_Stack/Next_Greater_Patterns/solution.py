"""
Monotonic Stack - Next Greater/Smaller Elements

Problem: Find next greater/smaller element for each position.

Pattern: Maintain stack of indices in increasing/decreasing order.

Common Applications:
- Next greater element
- Daily temperatures (days until warmer)
- Largest rectangle in histogram
- Stock span problem

Time Complexity: O(n) - each element pushed/popped once
Space Complexity: O(n)
"""

from typing import List


def sum_of_subarray_minimums(arr: List[int]) -> int:
    """
    Sum of minimum of all subarrays.
    (LeetCode 907: Sum of Subarray Minimums)
    
    Strategy: For each element, find how many subarrays it's minimum of.
    Use monotonic stack to find previous and next smaller elements.
    
    Time: O(n), Space: O(n)
    """
    MOD = 10**9 + 7
    n = len(arr)
    
    # Find previous smaller
    prev_smaller = [-1] * n
    stack = []
    for i in range(n):
        while stack and arr[stack[-1]] > arr[i]:
            stack.pop()
        if stack:
            prev_smaller[i] = stack[-1]
        stack.append(i)
    
    # Find next smaller
    next_smaller = [n] * n
    stack = []
    for i in range(n - 1, -1, -1):
        while stack and arr[stack[-1]] >= arr[i]:  # >= to handle duplicates
            stack.pop()
        if stack:
            next_smaller[i] = stack[-1]
        stack.append(i)
    
    # Calculate contribution of each element
    result = 0
    for i in range(n):
        left_count = i - prev_smaller[i]
        right_count = next_smaller[i] - i
        result = (result + arr[i] * left_count * right_count) % MOD
    
    return result




if __name__ == "__main__":
    print("Next greater [2,1,2,4,3]:", next_greater_elements([2, 1, 2, 4, 3]))  # [4,2,4,-1,-1]
    print("Daily temperatures [73,74,75,71,69,72,76,73]:", 
          daily_temperatures([73, 74, 75, 71, 69, 72, 76, 73]))  # [1,1,4,2,1,1,0,0]
    print("Largest rectangle [2,1,5,6,2,3]:", largest_rectangle_histogram([2, 1, 5, 6, 2, 3]))  # 10
    print("Trap rain water [0,1,0,2,1,0,1,3,2,1,2,1]:", trap_rain_water([0,1,0,2,1,0,1,3,2,1,2,1]))  # 6
