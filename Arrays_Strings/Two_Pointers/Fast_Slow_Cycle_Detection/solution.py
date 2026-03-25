"""
Linked List Cycle Detection - Fast/Slow Pointer Pattern (Floyd's Algorithm)

Problem: Detect if a linked list has a cycle.

Pattern: Slow pointer moves 1 step, fast pointer moves 2 steps.
         If there's a cycle, they will eventually meet.

Time Complexity: O(n) - worst case visit each node once
Space Complexity: O(1) - only two pointers
"""

from typing import Optional

class ListNode:
    """Definition for singly-linked list node."""
    def __init__(self, val: int = 0, next: Optional['ListNode'] = None):
        self.val = val
        self.next = next


def has_cycle(head: Optional[ListNode]) -> bool:
    """
    Detect if a linked list has a cycle using Floyd's algorithm.
    
    Args:
        head: Head of the linked list
        
    Returns:
        True if cycle exists, False otherwise
    """
    if not head or not head.next:
        return False
    
    slow = head
    fast = head
    
    while fast and fast.next:
        slow = slow.next        # Move 1 step
        fast = fast.next.next   # Move 2 steps
        
        if slow is fast:        # They met - cycle exists
            return True
    
    return False  # fast reached end - no cycle


# Helper function to create a cycle for testing
def create_list_with_cycle(values: list, cycle_pos: int) -> Optional[ListNode]:
    """Create a linked list with an optional cycle."""
    if not values:
        return None
    
    nodes = [ListNode(val) for val in values]
    
    # Link nodes
    for i in range(len(nodes) - 1):
        nodes[i].next = nodes[i + 1]
    
    # Create cycle if cycle_pos is valid
    if 0 <= cycle_pos < len(nodes):
        nodes[-1].next = nodes[cycle_pos]
    
    return nodes[0]


# Test cases
if __name__ == "__main__":
    test_cases = [
        ([3, 2, 0, -4], 1, True),   # Cycle at position 1
        ([1, 2], 0, True),           # Cycle at position 0
        ([1], -1, False),            # No cycle
        ([1, 2, 3], -1, False),      # No cycle
    ]
    
    for values, cycle_pos, expected in test_cases:
        head = create_list_with_cycle(values, cycle_pos)
        result = has_cycle(head)
        status = "✓" if result == expected else "✗"
        cycle_desc = f"cycle at pos {cycle_pos}" if cycle_pos >= 0 else "no cycle"
        print(f"{status} List {values} ({cycle_desc}): has_cycle = {result}")
