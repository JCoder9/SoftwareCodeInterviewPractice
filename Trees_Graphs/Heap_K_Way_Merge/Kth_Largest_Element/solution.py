"""
Heap/K-Way Merge - Kth Largest Element

Problem: Find the kth largest element in an unsorted array.
         Example: [3, 2, 1, 5, 6, 4] with k=2 → 5 (second largest)

Pattern: Use min-heap of size k. Keep only k largest elements.
         The smallest element in this heap is the kth largest overall.

Related LeetCode Problems:
- LC 215: Kth Largest Element in an Array (Medium) ⭐⭐⭐
- LC 703: Kth Largest Element in a Stream (Easy)
- LC 973: K Closest Points to Origin (Medium)

Time Complexity: O(n log k) - n insertions into heap of size k
Space Complexity: O(k) - heap stores k elements
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n log n) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force sorts entire array, then returns element at index
#                  n-k — O(n log n) time"
#   2. Problem:    "Sorting entire array when we only need kth element; for large n
#                  and small k this is wasteful"
#   3. Transition: "Min-heap of size k tracks k largest; only O(n log k) time,
#                  better when k ≪ n"
#
# def findKthLargest_naive(nums, k):
#     nums_sorted = sorted(nums, reverse=True)
#     return nums_sorted[k - 1]
# ─────────────────────────────────────────────────────────────────────────────

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
