"""
Heap/K-Way Merge - Merge K Sorted Lists

Problem: Merge k sorted linked lists into one sorted linked list.
         Example: [[1,4,5], [1,3,4], [2,6]] → [1,1,2,3,4,4,5,6]

Pattern: Use min-heap to always pick the smallest element across all k lists.
         Add first node from each list to heap, then repeatedly extract min and add its next.

Related LeetCode Problems:
- LC 23: Merge k Sorted Lists (Hard) ⭐⭐⭐
- LC 378: Kth Smallest Element in Sorted Matrix (Medium)
- LC 632: Smallest Range Covering Elements from K Lists (Hard)

Time Complexity: O(N log k) where N = total nodes, k = number of lists
Space Complexity: O(k) - heap stores at most k nodes
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(N k) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force merges lists one by one: merge list1 with list2,
#                  then result with list3, etc. — O(N k) time"
#   2. Problem:    "For k=1000 lists with 100 nodes each: 100M operations; early
#                  merges process same nodes multiple times"
#   3. Transition: "Min-heap processes each node exactly once; O(log k) per node
#                  — total O(N log k)"
#
# def merge_k_lists_naive(lists):
#     def merge_two(l1, l2):
#         dummy = ListNode(0)
#         curr = dummy
#         while l1 and l2:
#             if l1.val < l2.val:
#                 curr.next = l1
#                 l1 = l1.next
#             else:
#                 curr.next = l2
#                 l2 = l2.next
#             curr = curr.next
#         curr.next = l1 or l2
#         return dummy.next
#     
#     if not lists:
#         return None
#     result = lists[0]
#     for i in range(1, len(lists)):
#         result = merge_two(result, lists[i])
#     return result
# ─────────────────────────────────────────────────────────────────────────────

import heapq
from typing import List, Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def mergeKLists(lists: List[Optional[ListNode]]) -> Optional[ListNode]:
    # Min heap: (value, list_index, node)
    min_heap = []
    
    # Add first node from each list
    for i, node in enumerate(lists):
        if node:
            heapq.heappush(min_heap, (node.val, i, node))
    
    dummy = ListNode(0)
    current = dummy
    
    while min_heap:
        val, i, node = heapq.heappop(min_heap)
        current.next = node
        current = current.next
        
        # Add next node from same list
        if node.next:
            heapq.heappush(min_heap, (node.next.val, i, node.next))
    
    return dummy.next

if __name__ == "__main__":
    l1 = ListNode(1, ListNode(4, ListNode(5)))
    l2 = ListNode(1, ListNode(3, ListNode(4)))
    l3 = ListNode(2, ListNode(6))
    result = mergeKLists([l1, l2, l3])
    
    while result:
        print(result.val, end=" -> " if result.next else "\n")
        result = result.next
