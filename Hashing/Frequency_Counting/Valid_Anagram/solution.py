"""
Valid Anagram - Basic Frequency Check

Problem: Given two strings s and t, return True if t is an anagram of s, False otherwise.
         An anagram uses the same characters in a different order.

Pattern: Build frequency count for both strings and compare.

Time Complexity: O(n) - single pass through both strings
Space Complexity: O(1) - at most 26 characters for lowercase letters
"""

from collections import Counter

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n log n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force sorts both strings and compares if they're equal
#                   — O(n log n) time for sorting"
#   2. Problem:    "Sorting is slower than needed; uses O(n) space for sorted copies"
#   3. Transition: "With frequency counting we track character counts and compare
#                   in one pass — drops to O(n) time with O(1) space"
#
# def is_anagram_naive(s: str, t: str) -> bool:
#     if len(s) != len(t):
#         return False
#     return sorted(s) == sorted(t)
# ─────────────────────────────────────────────────────────────────────────


def is_anagram(s: str, t: str) -> bool:
    """Check if two strings are anagrams (same letters, different order)."""
    # Different lengths? Can't be anagrams
    if len(s) != len(t):
        return False
    
    # Count array for 26 lowercase letters (same as Java approach)
    count = [0] * 26
    
    # Single pass: increment for s, decrement for t
    for c1, c2 in zip(s, t):
        count[ord(c1) - ord('a')] += 1
        count[ord(c2) - ord('a')] -= 1
    
    # If anagrams, all counts should be 0
    return all(c == 0 for c in count)


# Alternative: using Counter (more Pythonic, but slightly less efficient)
def is_anagram_v2(s: str, t: str) -> bool:
    return Counter(s) == Counter(t)


# Test
if __name__ == "__main__":
    print(is_anagram("listen", "silent"))  # True
    print(is_anagram("hello", "world"))    # False
