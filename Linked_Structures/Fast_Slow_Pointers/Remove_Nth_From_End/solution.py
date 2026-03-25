"""
Remove Nth Node From End of List

Pattern: Move fast pointer N steps ahead, then move both until fast reaches end

Time Complexity: O(n)
Space Complexity: O(1)
"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def removeNthFromEnd(head, n):
    """
    Remove nth node from end
    """
    # Dummy node helps handle edge case of removing head
    dummy = ListNode(0)
    dummy.next = head
    
    slow = dummy
    fast = dummy
    
    # Move fast n+1 steps ahead
    for _ in range(n + 1):
        fast = fast.next
    
    # Move both until fast reaches end
    while fast:
        slow = slow.next
        fast = fast.next
    
    # Remove the node
    slow.next = slow.next.next
    
    return dummy.next

# Test
if __name__ == "__main__":
    # Example: 1 -> 2 -> 3 -> 4 -> 5, n = 2
    # Remove 4 (2nd from end)
    node1 = ListNode(1)
    node2 = ListNode(2)
    node3 = ListNode(3)
    node4 = ListNode(4)
    node5 = ListNode(5)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    result = removeNthFromEnd(node1, 2)
    # Result: 1 -> 2 -> 3 -> 5

    current = result
    while current:
        print(current.val, end=" -> ")
        current = current.next
    print("None")
