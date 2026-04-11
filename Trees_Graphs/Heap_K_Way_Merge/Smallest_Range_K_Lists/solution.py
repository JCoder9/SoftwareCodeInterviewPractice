"""
Heap/K-Way Merge - Smallest Range Covering K Lists (LC 632)

Problem: Find smallest range including at least one number from each of k lists.
         Example: [[4,10,15],[0,9,12],[5,18,22]] → [20,24]

Pattern: K-way merge with min-heap, track current max while processing min

Related LeetCode Problems:
- LC 632: Smallest Range Covering Elements from K Lists (Hard) ⭐⭐⭐
- LC 23: Merge k Sorted Lists (Hard)
- LC 373: Find K Pairs with Smallest Sums (Medium)

Time Complexity: O(N log k) - N total elements, k-size heap
Space Complexity: O(k) - heap size
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n1 × n2 × ... × nk) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all combinations of picking one element from
#                  each list — O(n1 × n2 × ... × nk)"
#   2. Problem:    "For k=3 lists with n=100 each: 100^3 = 1M combinations to check"
#   3. Transition: "Use min-heap for k-way merge, track current max — O(N log k)"
#
# def smallest_range_naive(nums):
#     from itertools import product
#     min_range = [float('-inf'), float('inf')]
#     
#     # Generate all combinations: one element from each list
#     for combo in product(*nums):
#         range_min = min(combo)
#         range_max = max(combo)
#         if range_max - range_min < min_range[1] - min_range[0]:
#             min_range = [range_min, range_max]
#     return min_range
# ─────────────────────────────────────────────────────────────────────────────

import heapq
from typing import List

def smallestRange(nums: List[List[int]]) -> List[int]:
    # Min heap: (value, list_index, element_index)
    min_heap = []
    current_max = float('-inf')
    
    # Add first element from each list
    for i, lst in enumerate(nums):
        heapq.heappush(min_heap, (lst[0], i, 0))
        current_max = max(current_max, lst[0])
    
    result_range = [float('-inf'), float('inf')]
    
    while min_heap:
        current_min, list_idx, elem_idx = heapq.heappop(min_heap)
        
        # Update result if smaller range found
        if current_max - current_min < result_range[1] - result_range[0]:
            result_range = [current_min, current_max]
        
        # Move to next element in same list
        if elem_idx + 1 < len(nums[list_idx]):
            next_val = nums[list_idx][elem_idx + 1]
            heapq.heappush(min_heap, (next_val, list_idx, elem_idx + 1))
            current_max = max(current_max, next_val)
        else:
            # Can't include all lists anymore
            break
    
    return result_range

if __name__ == "__main__":
    nums = [[4, 10, 15, 24, 26], [0, 9, 12, 20], [5, 18, 22, 30]]
    print(smallestRange(nums))  # [20, 24]
