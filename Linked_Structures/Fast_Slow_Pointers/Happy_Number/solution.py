"""
Happy Number (Non-Linked List Use Case!)

Pattern: Treat number sequence like a linked list, use fast/slow to detect cycles

Time Complexity: O(log n)
Space Complexity: O(1)
"""

def isHappy(n):
    """
    Determine if a number is happy
    """
    def get_next(number):
        total_sum = 0
        while number > 0:
            digit = number % 10
            total_sum += digit ** 2
            number //= 10
        return total_sum
    
    slow = n
    fast = n
    
    while True:
        slow = get_next(slow)           # Move 1 step
        fast = get_next(get_next(fast)) # Move 2 steps
        
        if fast == 1:
            return True
        
        if slow == fast:  # Cycle detected
            return False

# Test
if __name__ == "__main__":
    # Example: 19 is happy
    # 1² + 9² = 82
    # 8² + 2² = 68
    # 6² + 8² = 100
    # 1² + 0² + 0² = 1
    print(isHappy(19))  # True

    # Example: 2 is not happy (gets stuck in cycle)
    print(isHappy(2))   # False
