"""
Valid Anagram - Basic Frequency Check

Pattern: Check if two strings are anagrams (same characters, different order).

Time Complexity: O(n)
Space Complexity: O(1) - at most 26 characters
"""

from collections import Counter


def is_anagram(s: str, t: str) -> bool:
    """Check if two strings are anagrams (same letters, different order)."""
    # Different lengths? Can't be anagrams
    if len(s) != len(t):
        return False
    
    # Count characters in both strings
    count = {}
    
    # Add counts from first string
    for char in s:
        count[char] = count.get(char, 0) + 1
    
    # Subtract counts from second string
    for char in t:
        count[char] = count.get(char, 0) - 1
    
    # If anagrams, all counts should be 0
    for val in count.values():
        if val != 0:
            return False
    
    return True


# Alternative: using Counter (cleaner)
def is_anagram_v2(s: str, t: str) -> bool:
    return Counter(s) == Counter(t)


# Test
if __name__ == "__main__":
    print(is_anagram("listen", "silent"))  # True
    print(is_anagram("hello", "world"))    # False
