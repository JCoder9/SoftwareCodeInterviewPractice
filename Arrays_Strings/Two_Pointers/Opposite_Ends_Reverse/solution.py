"""
Reverse Array/String In-Place - Opposite Ends Two Pointer Pattern

Problem: Reverse an array or string in-place by swapping elements from both ends.

Pattern: Two pointers starting from opposite ends, swapping and moving inward.

Time Complexity: O(n) - single pass through half of array
Space Complexity: O(1) - in-place modification
"""

from typing import List

def reverse_list(chars: List[str]) -> None:
    """
    Reverse a list in-place using two pointers.
    
    Args:
        chars: List to reverse (modified in-place)
    """
    l, r = 0, len(chars) - 1
    
    while l < r:
        # Swap elements at left and right pointers
        chars[l], chars[r] = chars[r], chars[l]
        l += 1
        r -= 1


def reverse_string(s: str) -> str:
    """
    Reverse a string (strings are immutable in Python, so returns new string).
    
    Args:
        s: String to reverse
        
    Returns:
        Reversed string
    """
    chars = list(s)
    reverse_list(chars)
    return ''.join(chars)


# Test cases
if __name__ == "__main__":
    # Test list reversal
    test_lists = [
        ["h", "e", "l", "l", "o"],
        ["H", "a", "n", "n", "a", "h"],
        ["a"],
        ["a", "b"],
    ]
    
    print("List reversal tests:")
    for chars in test_lists:
        original = chars.copy()
        reverse_list(chars)
        print(f"  {original} -> {chars}")
    
    # Test string reversal
    test_strings = ["hello", "Hannah", "a", "ab", ""]
    
    print("\nString reversal tests:")
    for s in test_strings:
        result = reverse_string(s)
        print(f"  '{s}' -> '{result}'")
