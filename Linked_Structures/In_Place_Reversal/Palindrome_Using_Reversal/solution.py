"""In-Place Reversal - Palindrome Using Reversal. LC 234. Time: O(n), Space: O(1)"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def isPalindrome(head):
    """LC 234: Check if linked list is palindrome"""
    if not head or not head.next:
        return True
    
    # Find middle
    slow = fast = head
    while fast.next and fast.next.next:
        slow = slow.next
        fast = fast.next.next
    
    # Reverse second half
    secondHalf = slow.next
    slow.next = None
    
    prev = None
    current = secondHalf
    while current:
        nextTemp = current.next
        current.next = prev
        prev = current
        current = nextTemp
    secondHalf = prev
    
    # Compare
    firstHalf = head
    result = True
    while secondHalf:
        if firstHalf.val != secondHalf.val:
            result = False
            break
        firstHalf = firstHalf.next
        secondHalf = secondHalf.next
    
    return result

def createList(arr):
    if not arr: return None
    head = ListNode(arr[0])
    current = head
    for val in arr[1:]:
        current.next = ListNode(val)
        current = current.next
    return head

if __name__ == "__main__":
    head = createList([1, 2, 3, 2, 1])
    print(f"[1,2,3,2,1] is palindrome: {isPalindrome(head)}")  # True
    
    head = createList([1, 2, 3, 4])
    print(f"[1,2,3,4] is palindrome: {isPalindrome(head)}")  # False
