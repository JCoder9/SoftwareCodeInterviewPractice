"""Heap/K-Way Merge - Merge K Sorted Arrays
Merge k sorted arrays using min-heap.
Track array index and element position within each array.
Time: O(N log k), Space: O(k)"""

import heapq
from typing import List

def mergeKArrays(arrays: List[List[int]]) -> List[int]:
    # Min heap: (value, array_index, element_index)
    min_heap = []
    
    # Add first element from each array
    for i, arr in enumerate(arrays):
        if arr:
            heapq.heappush(min_heap, (arr[0], i, 0))
    
    result = []
    
    while min_heap:
        val, arr_idx, elem_idx = heapq.heappop(min_heap)
        result.append(val)
        
        # Add next element from same array
        if elem_idx + 1 < len(arrays[arr_idx]):
            next_val = arrays[arr_idx][elem_idx + 1]
            heapq.heappush(min_heap, (next_val, arr_idx, elem_idx + 1))
    
    return result

if __name__ == "__main__":
    arrays = [[1, 4, 5], [1, 3, 4], [2, 6]]
    print(mergeKArrays(arrays))  # [1, 1, 2, 3, 4, 4, 5, 6]
