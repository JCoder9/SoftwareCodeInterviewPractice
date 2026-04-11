"""
In-Place Reversal - Reverse K-Group (LC 25)

Problem: Reverse nodes of linked list k at a time.
         Example: 1→2→3→4→5, k=3 → 3→2→1→4→5

Pattern: Check if k nodes available, reverse them, recurse on rest

Related LeetCode Problems:
- LC 25: Reverse Nodes in k-Group (Hard) ⭐⭐⭐
- LC 206: Reverse Linked List (Easy)
- LC 92: Reverse Linked List II (Medium)

Time Complexity: O(n) - visit each node once
Space Complexity: O(1) iterative or O(n/k) recursive
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n × k) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force converts to array, reverses k-groups in array,
#                  rebuilds list — O(n) but extra O(n) space"
#   2. Problem:    "For n=10,000: allocates 10K-element array; multiple passes over
#                  data; not in-place"
#   3. Transition: "Reverse in-place with pointer manipulation — O(n) time, O(1) space"
#
# def reverse_k_group_naive(head, k):
#     # Convert to array
#     arr = []
#     curr = head
#     while curr:
#         arr.append(curr.val)
#         curr = curr.next
#     
#     # Reverse k-groups in array
#     for i in range(0, len(arr) - k + 1, k):
#         arr[i:i+k] = arr[i:i+k][::-1]
#     
#     # Rebuild linked list
#     dummy = ListNode(0)
#     curr = dummy
#     for val in arr:
#         curr.next = ListNode(val)
#         curr = curr.next
#     return dummy.next
# ─────────────────────────────────────────────────────────────────────────────

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
