"""In-Place Reversal - Reverse K-Group. LC 25. Time: O(n), Space: O(1) or O(n/k) recursive"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def reverseKGroup(head, k):
    """LC 25: Reverse nodes in k-group"""
    if not head or k <= 1:
        return head
    
    # Check if we have k nodes
    count = 0
    current = head
    while current and count < k:
        current = current.next
        count += 1
    
    # If we have k nodes, reverse them
    if count == k:
        prev = None
        current = head
        for _ in range(k):
            next_temp = current.next
            current.next = prev
            prev = current
            current = next_temp
        
        head.next = reverseKGroup(current, k)
        return prev
    
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
    head = createList([1, 2, 3, 4, 5, 6, 7, 8])
    print(f"Original: {printList(head)}")
    result = reverseKGroup(head, 3)
    print(f"After reversing in groups of 3: {printList(result)}")  # 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8
