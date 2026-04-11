"""
Fast/Slow Pointers - Detect Cycle Pattern

Problem: Given a linked list, determine if it has a cycle in it.

Pattern: Fast pointer moves 2 steps, slow moves 1. If they meet, cycle exists.
         If fast reaches None, no cycle.

Related LeetCode Problems:
- LC 141: Linked List Cycle (Easy)
- LC 287: Find the Duplicate Number (Medium) - using array as implicit linked list
- LC 457: Circular Array Loop (Medium)

Time Complexity: O(n) - visit each node at most twice
Space Complexity: O(1) - only two pointers
"""

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force uses a set to track visited nodes
#                   — O(n) time but O(n) space for the set"
#   2. Problem:    "Uses extra space; can we do it with O(1) space?"
#   3. Transition: "With fast/slow pointers, if there's a cycle they'll eventually
#                   meet — same O(n) time but O(1) space"
#
# def hasCycle_naive(head):
#     visited = set()
#     curr = head
#     
#     while curr:
#         if curr in visited:
#             return True  # Cycle detected
#         visited.add(curr)
#         curr = curr.next
#     return False  # Reached end, no cycle
# ─────────────────────────────────────────────────────────────────────────

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def hasCycle(head):
    """
    LC 141: Detect if linked list has a cycle
    
    Returns:
        bool: True if cycle exists, False otherwise
    """
    if not head or not head.next:
        return False
    
    slow = head
    fast = head
    
    while fast and fast.next:
        slow = slow.next          # Move 1 step
        fast = fast.next.next     # Move 2 steps
        
        if slow == fast:          # They met - cycle exists
            return True
    
    return False  # Fast reached the end - no cycle


def findDuplicate(nums):
    """
    LC 287: Find the Duplicate Number
    Treat array as implicit linked list where nums[i] points to nums[nums[i]]
    
    Array contains n+1 integers in range [1, n], exactly one duplicate
    
    Returns:
        int: The duplicate number
    """
    # Phase 1: Detect cycle using fast/slow
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


def circularArrayLoop(nums):
    """
    LC 457: Circular Array Loop
    Determine if there's a loop in circular array (all same direction)
    
    Returns:
        bool: True if valid loop exists
    """
    n = len(nums)
    
    def next_index(i):
        return (i + nums[i]) % n
    
    for i in range(n):
        if nums[i] == 0:
            continue
        
        slow = fast = i
        # Check if all elements in potential cycle have same direction
        forward = nums[i] > 0
        
        while True:
            # Move slow one step
            slow = next_index(slow)
            if nums[slow] == 0 or (nums[slow] > 0) != forward:
                break
            
            # Move fast two steps
            fast = next_index(fast)
            if nums[fast] == 0 or (nums[fast] > 0) != forward:
                break
            fast = next_index(fast)
            if nums[fast] == 0 or (nums[fast] > 0) != forward:
                break
            
            # Check if single element loop
            if slow == next_index(slow):
                break
            
            if slow == fast:
                return True
        
        # Mark visited elements as 0 (no valid loop through them)
        slow = i
        val = nums[i]
        while nums[slow] != 0 and (nums[slow] > 0) == forward:
            next_i = next_index(slow)
            nums[slow] = 0
            slow = next_i
    
    return False


# Test cases
if __name__ == "__main__":
    # Test hasCycle
    print("Testing hasCycle:")
    node1 = ListNode(1)
    node2 = ListNode(2)
    node3 = ListNode(3)
    node4 = ListNode(4)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node2  # Creates cycle
    print(f"Has cycle: {hasCycle(node1)}")  # True
    
    # Test findDuplicate
    print("\nTesting findDuplicate:")
    print(f"Duplicate in [1,3,4,2,2]: {findDuplicate([1,3,4,2,2])}")  # 2
    print(f"Duplicate in [3,1,3,4,2]: {findDuplicate([3,1,3,4,2])}")  # 3
    
    # Test circularArrayLoop
    print("\nTesting circularArrayLoop:")
    print(f"Loop in [2,-1,1,2,2]: {circularArrayLoop([2,-1,1,2,2])}")  # True
    print(f"Loop in [-1,2]: {circularArrayLoop([-1,2])}")  # False
