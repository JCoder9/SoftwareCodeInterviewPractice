"""
Reverse First K Nodes

Pattern: Same as basic reversal but stop after k nodes

Time Complexity: O(n)
Space Complexity: O(1)
"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def reverse_first_k(head, k):
    """
    Reverse first k nodes of linked list
    """
    if not head or k <= 1:
        return head
    
    prev = None
    current = head
    count = 0
    
    # Reverse first k nodes
    while current and count < k:
        next_temp = current.next
        current.next = prev
        prev = current
        current = next_temp
        count += 1
    
    # Connect the reversed part to remaining list
    # 'head' is now the tail of reversed part
    if head:
        head.next = current
    
    return prev

# Test
if __name__ == "__main__":
    head = ListNode(1)
    head.next = ListNode(2)
    head.next.next = ListNode(3)
    head.next.next.next = ListNode(4)
    head.next.next.next.next = ListNode(5)

    result = reverse_first_k(head, 3)
    # Result: 3→2→1→4→5
