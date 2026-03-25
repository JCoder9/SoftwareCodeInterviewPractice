"""Heap/K-Way Merge - Merge K Sorted Lists (LC 23)
Merge k sorted linked lists using min-heap.
Always pick smallest element across all k lists.
Time: O(N log k), Space: O(k)"""

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
