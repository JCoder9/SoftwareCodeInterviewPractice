"""In-Place Reversal - Reorder Zigzag. LC 143. Time: O(n), Space: O(1)"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def reorderList(head):
    """LC 143: Reorder L0→Ln→L1→Ln-1→L2→Ln-2→..."""
    if not head or not head.next:
        return head
    
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
    
    # Merge
    firstHalf = head
    while secondHalf:
        temp1 = firstHalf.next
        temp2 = secondHalf.next
        
        firstHalf.next = secondHalf
        secondHalf.next = temp1  
        
        firstHalf = temp1
        secondHalf = temp2
    
    return head

def createList(arr):
    if not arr: return None
    head = ListNode(arr[0])
    current = head
    for val in arr[1:]:
        current.next = ListNode(val)
        current = current.next
    return head

def printList(head):
    values = []
    while head:
        values.append(str(head.val))
        head = head.next
    return " -> ".join(values)

if __name__ == "__main__":
    head = createList([1, 2, 3, 4, 5])
    print(f"Original: {printList(head)}")
    reorderList(head)
    print(f"After reordering: {printList(head)}")  # 1 -> 5 -> 2 -> 4 -> 3
