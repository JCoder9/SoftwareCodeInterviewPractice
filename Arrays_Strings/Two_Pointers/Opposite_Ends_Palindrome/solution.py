"""
Valid Palindrome - Opposite Ends Two Pointer Pattern

Problem: Check if a string is a palindrome, ignoring non-alphanumeric characters
         and case differences.

Pattern: Two pointers starting from opposite ends, moving towards center.

Time Complexity: O(n) - single pass through string
Space Complexity: O(1) - only using two pointers
"""

def is_palindrome(s: str) -> bool:
    """
    Check if string is a palindrome (ignoring non-alphanumeric, case-insensitive).
    
    Args:
        s: Input string to check
        
    Returns:
        True if palindrome, False otherwise
    """
    l, r = 0, len(s) - 1

    while l < r:
        # Skip non-alphanumeric from left
        while l < r and not s[l].isalnum():
            l += 1
        # Skip non-alphanumeric from right
        while l < r and not s[r].isalnum():
            r -= 1

        # Compare characters (case-insensitive)
        if s[l].lower() != s[r].lower():
            return False

        l += 1
        r -= 1

    return True


# Test cases
if __name__ == "__main__":
    test_cases = [
        ("A man, a plan, a canal: Panama", True),
        ("race a car", False),
        (" ", True),
        ("a", True),
        ("ab", False),
        ("aba", True),
    ]
    
    for s, expected in test_cases:
        result = is_palindrome(s)
        status = "✓" if result == expected else "✗"
        print(f"{status} is_palindrome('{s}') = {result} (expected {expected})")
