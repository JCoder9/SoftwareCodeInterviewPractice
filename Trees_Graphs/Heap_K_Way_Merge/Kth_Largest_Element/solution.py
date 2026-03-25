"""Heap/K-Way Merge - Kth Largest Element (LC 215)
Find kth largest element using min-heap of size k.
Keep only k largest elements. Smallest of k largest = kth largest.
Time: O(n log k), Space: O(k)"""

import heapq

def findKthLargest(nums, k):
    # Min heap of size k
    min_heap = []
    
    for num in nums:
        heapq.heappush(min_heap, num)
        # Keep only k largest elements
        if len(min_heap) > k:
            heapq.heappop(min_heap)  # Remove smallest
    
    return min_heap[0]  # Smallest of k largest = kth largest

if __name__ == "__main__":
    print(findKthLargest([3, 2, 1, 5, 6, 4], 2))  # 5
