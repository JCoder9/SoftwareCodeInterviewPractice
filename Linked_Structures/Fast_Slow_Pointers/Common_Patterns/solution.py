"""
Fast/Slow Pointers - Linked List Pattern

Problem: Use two pointers moving at different speeds to solve linked list problems.

Patterns:
1. Cycle Detection (Floyd's Algorithm): slow moves 1, fast moves 2
2. Find Middle: When fast reaches end, slow is at middle
3. Find Kth from End: fast moves k steps ahead, then both move together

Time Complexity: O(n) - single pass
Space Complexity: O(1) - only two pointers
"""

from typing import Optional


def reverse_linked_list(head: Optional[ListNode]) -> Optional[ListNode]:
    """Helper: reverse a linked list."""
    prev = None
    curr = head
    
    while curr:
        next_node = curr.next
        curr.next = prev
        prev = curr
        curr = next_node
    
    return prev




if __name__ == "__main__":
    print("Cycle Detection:")
    head = create_linked_list([1, 2, 3, 4, 5])
    print(f"  has_cycle([1,2,3,4,5]) = {has_cycle(head)}")
    
    print("\nFind Middle:")
