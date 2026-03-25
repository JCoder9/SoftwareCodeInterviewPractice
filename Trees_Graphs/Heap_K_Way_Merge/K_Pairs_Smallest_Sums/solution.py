"""Heap/K-Way Merge - K Pairs with Smallest Sums (LC 373)
Find k pairs (u, v) with smallest sums from two sorted arrays.
Start with (nums1[0], nums2[0]). Use heap to explore smallest sums.
Time: O(k log k), Space: O(k)"""

import heapq
from typing import List

def kSmallestPairs(nums1: List[int], nums2: List[int], k: int) -> List[List[int]]:
    if not nums1 or not nums2:
        return []
    
    # Min heap: (sum, i, j)
    min_heap = [(nums1[0] + nums2[0], 0, 0)]
    visited = {(0, 0)}
    result = []
    
    while min_heap and len(result) < k:
        _, i, j = heapq.heappop(min_heap)
        result.append([nums1[i], nums2[j]])
        
        # Add next pairs
        if i + 1 < len(nums1) and (i + 1, j) not in visited:
            heapq.heappush(min_heap, (nums1[i + 1] + nums2[j], i + 1, j))
            visited.add((i + 1, j))
        
        if j + 1 < len(nums2) and (i, j + 1) not in visited:
            heapq.heappush(min_heap, (nums1[i] + nums2[j + 1], i, j + 1))
            visited.add((i, j + 1))
    
    return result

if __name__ == "__main__":
    print(kSmallestPairs([1, 7, 11], [2, 4, 6], 3))  # [[1,2], [1,4], [1,6]]
    print(kSmallestPairs([1, 1, 2], [1, 2, 3], 2))   # [[1,1], [1,1]]
