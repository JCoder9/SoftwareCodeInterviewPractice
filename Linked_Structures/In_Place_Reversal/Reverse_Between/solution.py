"""In-Place Reversal - Reverse Between Positions. LC 92. Time: O(n), Space: O(1)"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def reverseBetween(head, m, n):
    """LC 92: Reverse from position m to n (1-indexed)"""
    if not head or m == n:
        return head
    
    dummy = ListNode(0)
    dummy.next = head
    
    # Find node before position m
    prev_m = dummy
    for _ in range(m - 1):
        prev_m = prev_m.next
    
    # Reverse from m to n
    prev = None
    current = prev_m.next
    
    for _ in range(n - m + 1):
        next_temp = current.next
        current.next = prev
        prev = current
        current = next_temp
    
    # Connect parts
    prev_m.next.next = current
    prev_m.next = prev
    
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
    head = createList([1, 2, 3, 4, 5])
    print(f"Original: {printList(head)}")
    result = reverseBetween(head, 2, 4)
    print(f"After reversing 2-4: {printList(result)}")  # 1 -> 4 -> 3 -> 2 -> 5
