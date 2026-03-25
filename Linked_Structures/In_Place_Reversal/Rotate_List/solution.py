"""In-Place Reversal - Rotate List. LC 61. Time: O(n), Space: O(1)"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def rotateRight(head, k):
    """LC 61: Rotate list to right by k places"""
    if not head or not head.next or k == 0:
        return head
    
    # Find length and tail
    length = 1
    tail = head
    while tail.next:
        tail = tail.next
        length += 1
    
    # Normalize k
    k = k % length
    if k == 0:
        return head
    
    # Make circular
    tail.next = head
    
    # Find new tail (at position length - k)
    steps = length - k
    new_tail = head
    for _ in range(steps - 1):
        new_tail = new_tail.next
    
    new_head = new_tail.next
    new_tail.next = None
    
    return new_head

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
    result = rotateRight(head, 2)
    print(f"After rotating right by 2: {printList(result)}")  # 4 -> 5 -> 1 -> 2 -> 3
