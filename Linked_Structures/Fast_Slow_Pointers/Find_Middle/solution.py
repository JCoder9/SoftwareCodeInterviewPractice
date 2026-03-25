"""
Find the Middle of Linked List

Pattern: When fast reaches the end, slow is at the middle

Time Complexity: O(n)
Space Complexity: O(1)
"""

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
