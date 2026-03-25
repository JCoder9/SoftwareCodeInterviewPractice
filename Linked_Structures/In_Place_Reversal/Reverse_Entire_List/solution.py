"""
Basic Reverse Entire Linked List

Pattern: Use 3 pointers: prev (starts null), current (starts at head), next (temporary storage)

Time Complexity: O(n)
Space Complexity: O(1)
"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def reverse_list(head):
    """
    Reverse entire linked list
    """
    prev = None
    current = head
    
    while current:
        # Save next node before we break the link
        next_temp = current.next
        
        # Reverse the pointer (THIS IS THE SURGERY!)
        current.next = prev
        
        # Move prev and current one step forward
        prev = current
        current = next_temp
    
    # prev is now the new head
    return prev

# Test
if __name__ == "__main__":
    # Create: 1→2→3→4→5
    head = ListNode(1)
    head.next = ListNode(2)
    head.next.next = ListNode(3)
    head.next.next.next = ListNode(4)
    head.next.next.next.next = ListNode(5)

    reversed_head = reverse_list(head)
    # Result: 5→4→3→2→1
