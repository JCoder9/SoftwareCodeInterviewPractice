"""
Check if Linked List is a Palindrome

Pattern: Find middle, reverse second half, compare both halves

Time Complexity: O(n)
Space Complexity: O(1)
"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def isPalindrome(head):
    """
    Check if linked list is palindrome
    """
    if not head or not head.next:
        return True
    
    # Step 1: Find middle
    slow = head
    fast = head
    
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    
    # Step 2: Reverse second half
    prev = None
    current = slow
    
    while current:
        next_node = current.next
        current.next = prev
        prev = current
        current = next_node
    
    # Step 3: Compare both halves
    left = head
    right = prev  # Head of reversed second half
    
    while right:  # Only need to check second half
        if left.val != right.val:
            return False
        left = left.next
        right = right.next
    
    return True

# Test
if __name__ == "__main__":
    # Example: 1 -> 2 -> 3 -> 2 -> 1
    node1 = ListNode(1)
    node2 = ListNode(2)
    node3 = ListNode(3)
    node4 = ListNode(2)
    node5 = ListNode(1)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    print(isPalindrome(node1))  # True

    # Example: 1 -> 2 -> 3 -> 4
    node1 = ListNode(1)
    node2 = ListNode(2)
    node3 = ListNode(3)
    node4 = ListNode(4)
    node1.next = node2
    node2.next = node3
    node3.next = node4

    print(isPalindrome(node1))  # False
