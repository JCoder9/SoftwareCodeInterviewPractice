"""Heap/K-Way Merge - Smallest Range Covering K Lists (LC 632)
Find smallest range including at least one number from each list.
Use k-way merge with tracking. Track current max while using min-heap.
Time: O(N log k), Space: O(k)"""

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
