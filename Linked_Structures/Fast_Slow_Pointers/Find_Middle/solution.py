"""
Fast/Slow Pointers - Find Middle Pattern

Problem: Find the middle node of a linked list. For even length, return the second middle node.

Pattern: Fast pointer moves 2 steps, slow moves 1 step. When fast reaches end, slow is at middle.

Time Complexity: O(n) - single pass through list
Space Complexity: O(1) - only two pointers
"""

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force counts the length first, then traverses to length/2
#                   — O(n) time but requires two passes"
#   2. Problem:    "Two passes through the list; can we do it in one pass?"
#   3. Transition: "With fast/slow pointers, fast moves 2x speed. When fast reaches
#                   end, slow is at middle — same O(n) but single pass, more elegant"
#
# def middleNode_naive(head):
#     # First pass: count length
#     length = 0
#     curr = head
#     while curr:
#         length += 1
#         curr = curr.next
#     
#     # Second pass: go to middle
#     mid = length // 2
#     curr = head
#     for _ in range(mid):
#         curr = curr.next
#     return curr
# ─────────────────────────────────────────────────────────────────────────

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def middleNode(head):
    """
    Find middle of linked list
    """
    slow = head
    fast = head
    
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    
    return slow  # Slow is at middle!

# Test
if __name__ == "__main__":
    # Example: 1 -> 2 -> 3 -> 4 -> 5
    node1 = ListNode(1)
    node2 = ListNode(2)
    node3 = ListNode(3)
    node4 = ListNode(4)
    node5 = ListNode(5)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    result = middleNode(node1)
    print(result.val)  # 3

    # Example: 1 -> 2 -> 3 -> 4 -> 5 -> 6
    node6 = ListNode(6)
    node5.next = node6

    result = middleNode(node1)
    print(result.val)  # 4 (second middle)
