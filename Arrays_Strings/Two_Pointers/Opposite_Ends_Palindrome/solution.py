"""
Valid Palindrome - Opposite Ends Two Pointer Pattern

Problem: Check if a string is a palindrome, ignoring non-alphanumeric characters
         and case differences.

Pattern: Two pointers starting from opposite ends, moving towards center.

Time Complexity: O(n) - single pass through string
Space Complexity: O(1) - only using two pointers
"""

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force filters all alphanumeric chars to a new string,
#                   then compares with its reverse — O(n) time, O(n) space"
#   2. Problem:    "Uses extra space for filtered string and its reverse"
#   3. Transition: "With two pointers from opposite ends we skip non-alphanumeric
#                   in-place and compare — same O(n) time but O(1) space"
#
# def is_palindrome_naive(s: str) -> bool:
#     filtered = ''.join(c.lower() for c in s if c.isalnum())
#     return filtered == filtered[::-1]
# ─────────────────────────────────────────────────────────────────────────

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
