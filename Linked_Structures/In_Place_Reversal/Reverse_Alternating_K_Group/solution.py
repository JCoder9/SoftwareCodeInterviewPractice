"""In-Place Reversal - Reverse Alternating K-Group. Time: O(n), Space: O(1)"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def reverseAlternatingKGroup(head, k):
    """Reverse first k, skip next k, reverse next k, etc."""
    if not head or k <= 1:
        return head
    
    dummy = ListNode(0)
    dummy.next = head
    prevGroup = dummy
    
    while True:
        # Check if we have k nodes
        kthNode = prevGroup
        for _ in range(k):
            kthNode = kthNode.next
            if not kthNode:
                return dummy.next
        
        # Reverse k nodes
        prev = kthNode.next
        current = prevGroup.next
        
        for _ in range(k):
            nextTemp = current.next
            current.next = prev
            prev = current
            current = nextTemp
        
        temp = prevGroup.next
        prevGroup.next = prev
        prevGroup = temp
        
        # Skip k nodes
        for _ in range(k):
            if not prevGroup:
                return dummy.next
            prevGroup = prevGroup.next
        
        if not prevGroup:
            return dummy.next

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
    head = createList([1, 2, 3, 4, 5, 6, 7, 8])
    print(f"Original: {printList(head)}")
    result = reverseAlternatingKGroup(head, 2)
    print(f"After reversing alternating groups of 2: {printList(result)}")  # 2 -> 1 -> 3 -> 4 -> 6 -> 5 -> 7 -> 8
