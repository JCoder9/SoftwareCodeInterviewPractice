"""
In-Place Reversal - Reverse Entire List Pattern

Problem: Reverse a singly linked list in-place.

Pattern: Use 3 pointers: prev (starts None), current (starts at head), next (temporary).
         Reverse links one by one.

Time Complexity: O(n) - single pass
Space Complexity: O(1) - only three pointers
"""

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force stores all nodes in a list, then rebuilds in
#                   reverse order — O(n) time but O(n) space"
#   2. Problem:    "Uses extra space; not truly in-place"
#   3. Transition: "With 3 pointers we reverse links in-place as we traverse
#                   — same O(n) time but O(1) space"
#
# def reverse_list_naive(head):
#     nodes = []
#     curr = head
#     
#     # Store all nodes
#     while curr:
#         nodes.append(curr)
#         curr = curr.next
#     
#     # Rebuild in reverse
#     for i in range(len(nodes) - 1, 0, -1):
#         nodes[i].next = nodes[i - 1]
#     if nodes:
#         nodes[0].next = None
#         return nodes[-1]
#     return None
# ─────────────────────────────────────────────────────────────────────────

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
