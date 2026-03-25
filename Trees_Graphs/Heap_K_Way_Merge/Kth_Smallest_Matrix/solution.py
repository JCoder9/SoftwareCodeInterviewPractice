"""Heap/K-Way Merge - Kth Smallest in Sorted Matrix (LC 378)
Find kth smallest element in matrix where rows/columns sorted.
Use k-way merge! Each row is sorted array.
Time: O(k log n), Space: O(n)"""

import heapq
from typing import List

def kthSmallest(matrix: List[List[int]], k: int) -> int:
    n = len(matrix)
    # Min heap: (value, row, col)
    min_heap = []
    
    # Add first element from each row
    for r in range(min(k, n)):  # No need to add more than k rows
        heapq.heappush(min_heap, (matrix[r][0], r, 0))
    
    result = 0
    for _ in range(k):
        result, r, c = heapq.heappop(min_heap)
        
        # Add next element from same row
        if c + 1 < n:
            heapq.heappush(min_heap, (matrix[r][c + 1], r, c + 1))
    
    return result

if __name__ == "__main__":
    matrix = [[1, 5, 9], [10, 11, 13], [12, 13, 15]]
    print(kthSmallest(matrix, 8))  # 13
