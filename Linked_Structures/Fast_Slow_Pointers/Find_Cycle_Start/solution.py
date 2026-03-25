"""
Fast/Slow Pointers - Find Cycle Start Pattern

Related LeetCode Problems:
- LC 142: Linked List Cycle II (Medium)
- LC 287: Find the Duplicate Number (Medium)

Pattern (Floyd's Cycle Detection):
1. Use fast/slow pointers to detect if cycle exists
2. When they meet, keep one pointer at meeting point
3. Reset other pointer to head
4. Move both one step at a time
5. Where they meet = cycle start

Mathematical Proof:
- Let distance from head to cycle start = F
- Let distance from cycle start to meeting point = a
- Let cycle length = C
- When they meet: slow traveled F + a, fast traveled F + a + nC
- Since fast travels 2x speed: 2(F + a) = F + a + nC
- Simplifies to: F = nC - a
- This means F steps from head = nC - a steps from meeting point

Time Complexity: O(n)
Space Complexity: O(1)
"""

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def detectCycle(head):
    """
    LC 142: Find where the cycle begins
    
    Returns:
        ListNode: Node where cycle starts, or None if no cycle
    """
    if not head or not head.next:
        return None
    
    # Step 1: Detect if cycle exists
    slow = head
    fast = head
    has_cycle = False
    
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        
        if slow == fast:
            has_cycle = True
            break
    
    if not has_cycle:
        return None
    
    # Step 2: Find cycle start
    slow = head  # Reset slow to head
    # fast stays at meeting point
    
    while slow != fast:
        slow = slow.next
        fast = fast.next
    
    return slow  # This is the cycle start!


def detectCycleWithLength(head):
    """
    Find cycle start and also return cycle length
    
    Returns:
        tuple: (cycle_start_node, cycle_length) or (None, 0)
    """
    if not head or not head.next:
        return None, 0
    
    # Step 1: Detect cycle
    slow = head
    fast = head
    has_cycle = False
    
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        
        if slow == fast:
            has_cycle = True
            break
    
    if not has_cycle:
        return None, 0
    
    # Calculate cycle length
    cycle_length = 1
    current = slow.next
    while current != slow:
        cycle_length += 1
        current = current.next
    
    # Step 2: Find cycle start
    slow = head
    while slow != fast:
        slow = slow.next
        fast = fast.next
    
    return slow, cycle_length


def findDuplicate(nums):
    """
    LC 287: Find the Duplicate Number
    Same algorithm - array treated as implicit linked list
    
    Returns:
        int: The duplicate number
    """
    # Phase 1: Detect cycle
    slow = nums[0]
    fast = nums[0]
    
    while True:
        slow = nums[slow]
        fast = nums[nums[fast]]
        if slow == fast:
            break
    
    # Phase 2: Find entrance to cycle (the duplicate)
    slow = nums[0]
    while slow != fast:
        slow = nums[slow]
        fast = nums[fast]
    
    return slow


# Test cases
if __name__ == "__main__":
    # Test detectCycle
    print("Testing detectCycle:")
    # Create: 1 -> 2 -> 3 -> 4 -> 5
    #                ^         |
    #                |_________|
    node1 = ListNode(1)
    node2 = ListNode(2)
    node3 = ListNode(3)
    node4 = ListNode(4)
    node5 = ListNode(5)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5
    node5.next = node3  # Cycle starts at node3
    
    result = detectCycle(node1)
    print(f"Cycle starts at node with value: {result.val if result else None}")  # 3
    
    # Test with cycle length
    print("\nTesting detectCycleWithLength:")
    start, length = detectCycleWithLength(node1)
    print(f"Cycle starts at: {start.val if start else None}, Length: {length}")  # 3, 3
    
    # Test findDuplicate
    print("\nTesting findDuplicate:")
    print(f"Duplicate in [1,3,4,2,2]: {findDuplicate([1,3,4,2,2])}")  # 2
    print(f"Duplicate in [3,1,3,4,2]: {findDuplicate([3,1,3,4,2])}")  # 3
    print(f"Duplicate in [2,5,9,6,9,3,8,9,7,1,4]: {findDuplicate([2,5,9,6,9,3,8,9,7,1,4])}")  # 9
