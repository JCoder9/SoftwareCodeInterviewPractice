"""Heap/K-Way Merge - Top K Frequent Elements (LC 347)
Return k most frequent elements using min-heap of size k.
Count frequencies, keep k largest frequencies in heap.
Time: O(n log k), Space: O(n)"""

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
