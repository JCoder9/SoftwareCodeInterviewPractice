"""
In-Place Linked List Reversal - Pointer Manipulation Pattern

Problem: Reverse linked list or portions of it by manipulating pointers in place.

Pattern: Track prev, curr, next pointers and rewire connections.

Core Technique:
    prev = None
    curr = head
    while curr:
        next_node = curr.next  # Save next
        curr.next = prev       # Reverse link
        prev = curr            # Move prev forward
        curr = next_node       # Move curr forward
    return prev

Time Complexity: O(n) - single pass
Space Complexity: O(1) - only a few pointers
"""

from typing import Optional


def reorder_list(head: Optional[ListNode]) -> None:
    """
    Reorder list: L0->L1->...->Ln-1->Ln becomes L0->Ln->L1->Ln-1->L2->Ln-2->...
    
    Strategy:
    1. Find middle using fast/slow pointers
    2. Reverse second half
    3. Merge two halves alternately
    
    Time: O(n), Space: O(1)
    """
    if not head or not head.next:
        return
    
    # Find middle
    slow = fast = head
    while fast.next and fast.next.next:
        slow = slow.next
        fast = fast.next.next
    
    # Reverse second half
    second_half = slow.next
    slow.next = None
    second_half = reverse_linked_list(second_half)
    
    # Merge two halves
    first_half = head
    while second_half:
        temp1 = first_half.next
        temp2 = second_half.next
        
        first_half.next = second_half
        second_half.next = temp1
        
        first_half = temp1
        second_half = temp2




if __name__ == "__main__":
    print("Reverse Entire List:")
    head = create_linked_list([1, 2, 3, 4, 5])
    reversed_head = reverse_linked_list(head)
    print(f"  [1,2,3,4,5] => {linked_list_to_list(reversed_head)}")
