"""
Fast/Slow Pointers - Reorder List Pattern

Related LeetCode Problems:
- LC 143: Reorder List (Medium)
- LC 2074: Reverse Nodes in Even Length Groups (Medium)

Pattern:
1. Find middle using fast/slow pointers
2. Reverse second half
3. Merge two halves alternately (zigzag pattern)

Time Complexity: O(n)
Space Complexity: O(1)
"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def reorderList(head):
    """
    LC 143: Reorder List
    L0→L1→L2→...→Ln-1→Ln becomes L0→Ln→L1→Ln-1→L2→Ln-2→...
    
    Modifies the list in-place
    """
    if not head or not head.next:
        return
    
    # Step 1: Find middle using slow/fast pointers
    slow = fast = head
    while fast.next and fast.next.next:
        slow = slow.next
        fast = fast.next.next
    
    # Step 2: Reverse second half
    second_half = slow.next
    slow.next = None  # Split the list
    
    prev = None
    current = second_half
    while current:
        next_temp = current.next
        current.next = prev
        prev = current
        current = next_temp
    second_half = prev
    
    # Step 3: Merge two halves
    first_half = head
    while second_half:
        # Save nexts
        temp1 = first_half.next
        temp2 = second_half.next
        
        # Interleave
        first_half.next = second_half
        second_half.next = temp1
        
        # Move forward
        first_half = temp1
        second_half = temp2


def reverseEvenLengthGroups(head):
    """
    LC 2074: Reverse Nodes in Even Length Groups
    Group lengths are 1, 2, 3, 4, ...
    Reverse groups with even length
    
    Returns:
        ListNode: Head of modified list
    """
    if not head or not head.next:
        return head
    
    dummy = ListNode(0)
    dummy.next = head
    prev_group_end = dummy
    group_len = 1
    
    while prev_group_end.next:
        # Find group start and end
        group_start = prev_group_end.next
        group_end = group_start
        actual_len = 1
        
        # Move to end of current group (or list end)
        for _ in range(group_len - 1):
            if not group_end.next:
                break
            group_end = group_end.next
            actual_len += 1
        
        next_group_start = group_end.next
        
        # Reverse if even length
        if actual_len % 2 == 0:
            prev = next_group_start
            current = group_start
            
            for _ in range(actual_len):
                next_temp = current.next
                current.next = prev
                prev = current
                current = next_temp
            
            prev_group_end.next = prev
            prev_group_end = group_start
        else:
            prev_group_end = group_end
        
        group_len += 1
    
    return dummy.next


# Helper function
def printList(head):
    values = []
    while head:
        values.append(str(head.val))
        head = head.next
    return " -> ".join(values)


# Test cases
if __name__ == "__main__":
    # Test reorderList
    print("Testing reorderList:")
    # 1 -> 2 -> 3 -> 4 -> 5
    head = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
    print(f"Before: {printList(head)}")
    reorderList(head)
    print(f"After:  {printList(head)}")  # 1 -> 5 -> 2 -> 4 -> 3
    
    # Even length list
    print("\nTesting reorderList (even length):")
    # 1 -> 2 -> 3 -> 4
    head = ListNode(1, ListNode(2, ListNode(3, ListNode(4))))
    print(f"Before: {printList(head)}")
    reorderList(head)
    print(f"After:  {printList(head)}")  # 1 -> 4 -> 2 -> 3
    
    # Test reverseEvenLengthGroups
    print("\nTesting reverseEvenLengthGroups:")
    # 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
    # Groups: [1], [2,3], [4,5,6], [7]
    # Reverse even: [1], [3,2], [4,5,6], [7]
    head = ListNode(1, ListNode(2, ListNode(3, ListNode(4, 
        ListNode(5, ListNode(6, ListNode(7)))))))
    print(f"Before: {printList(head)}")
    result = reverseEvenLengthGroups(head)
    print(f"After:  {printList(result)}")  # 1 -> 3 -> 2 -> 4 -> 5 -> 6 -> 7
