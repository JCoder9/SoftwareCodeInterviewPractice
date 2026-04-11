"""
Heap/K-Way Merge - Top K Frequent Elements

Problem: Given array, return k most frequent elements.
         Example: [1,1,1,2,2,3] with k=2 → [1,2] (1 appears 3 times, 2 appears 2 times)

Pattern: Count frequencies, use min-heap of size k to track k highest frequencies.
         Heap maintains k largest; smallest in heap is kth largest.

Related LeetCode Problems:
- LC 347: Top K Frequent Elements (Medium) ⭐⭐⭐
- LC 692: Top K Frequent Words (Medium)
- LC 451: Sort Characters By Frequency (Medium)

Time Complexity: O(n log k) - n insertions into heap of size k
Space Complexity: O(n) - frequency map
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n log n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force counts frequencies, sorts all by frequency, returns
#                  top k — O(n log n) sorting"
#   2. Problem:    "Sorts all n unique elements when we only need top k; wasteful
#                  for large n and small k"
#   3. Transition: "Min-heap of size k tracks k most frequent; O(n log k), better
#                  when k ≪ n"
#
# from collections import Counter
# def topKFrequent_naive(nums, k):
#     count = Counter(nums)
#     # Sort all by frequency descending
#     sorted_items = sorted(count.items(), key=lambda x: -x[1])
#     return [num for num, freq in sorted_items[:k]]
# ─────────────────────────────────────────────────────────────────────────────

import heapq
from typing import List
from collections import Counter

def topKFrequent(nums: List[int], k: int) -> List[int]:
    # Count frequencies
    count = Counter(nums)
    
    # Min heap of size k: (frequency, number)
    min_heap = []
    
    for num, freq in count.items():
        heapq.heappush(min_heap, (freq, num))
        if len(min_heap) > k:
            heapq.heappop(min_heap)
    
    # Extract numbers (not frequencies)
    return [num for freq, num in min_heap]

if __name__ == "__main__":
    print(topKFrequent([1, 1, 1, 2, 2, 3], 2))  # [1, 2]
    print(topKFrequent([4, 4, 4, 2, 2, 3], 2))  # [4, 2]
