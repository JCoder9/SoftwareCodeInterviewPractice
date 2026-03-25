"""
Heap / Priority Queue - Top K and K-Way Merge

Problem: Efficiently find top K elements or merge sorted streams.

Common Patterns:
1. Top K elements (min/max heap)
2. K-way merge (merge k sorted arrays/lists)
3. Sliding window with heap
4. Meeting rooms / intervals

Time Complexity: O(n log k) where k is heap size
Space Complexity: O(k)
"""

from typing import List, Optional
import heapq


def reorganize_string(s: str) -> str:
    """
    Rearrange string so no two adjacent characters are same.
    (LeetCode 767: Reorganize String)
    
    Strategy: Greedily place most frequent character first (using max heap).
    
    Time: O(n log 26) = O(n), Space: O(26) = O(1)
    """
    from collections import Counter
    
    freq = Counter(s)
    max_heap = [(-count, char) for char, count in freq.items()]
    heapq.heapify(max_heap)
    
    result = []
    prev_count, prev_char = 0, ''
    
    while max_heap:
        count, char = heapq.heappop(max_heap)
        result.append(char)
        
        # Add previous character back if still has remaining
        if prev_count < 0:
            heapq.heappush(max_heap, (prev_count, prev_char))
        
        # Update previous character
        prev_count = count + 1  # Increment since count is negative
        prev_char = char
    
    result_str = ''.join(result)
    return result_str if len(result_str) == len(s) else ""




if __name__ == "__main__":
    print("Kth largest [3,2,1,5,6,4], k=2:", kth_largest_element([3, 2, 1, 5, 6, 4], 2))  # 5
    print("Top k frequent [1,1,1,2,2,3], k=2:", top_k_frequent([1, 1, 1, 2, 2, 3], 2))  # [1,2]
    print("Kth smallest matrix [[1,5,9],[10,11,13],[12,13,15]], k=8:", 
          kth_smallest_in_sorted_matrix([[1, 5, 9], [10, 11, 13], [12, 13, 15]], 8))  # 13
    print("Min meeting rooms [[0,30],[5,10],[15,20]]:", min_meeting_rooms([[0, 30], [5, 10], [15, 20]]))  # 2
    print("Reorganize string 'aab':", reorganize_string("aab"))  # "aba"
