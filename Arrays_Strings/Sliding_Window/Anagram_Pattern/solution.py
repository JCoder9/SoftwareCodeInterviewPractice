"""
Find All Anagrams in String - Fixed Window + Frequency Matching

Problem: Find all starting indices of p's anagrams in string s.

Pattern: Fixed window size (len(p)) with frequency array comparison.
         Window slides through s, checking if frequencies match.

Time Complexity: O(n × 26) = O(n) for lowercase letters - array comparison is constant
Space Complexity: O(1) - two fixed-size arrays of 26
"""

from typing import List

def find_anagrams(s: str, p: str) -> List[int]:
    """
    Find all starting indices of p's anagrams in s.
    
    Args:
        s: String to search in
        p: Pattern to find anagrams of
        
    Returns:
        List of starting indices where anagrams are found
    """
    if not s or not p or len(p) > len(s):
        return []  # No anagrams possible

    # Frequency arrays for lowercase a-z
    need = [0] * 26
    win = [0] * 26
    
    # Build frequency array for pattern p
    for ch in p:
        need[ord(ch) - ord('a')] += 1

    res = []
    left = 0

    for right, ch in enumerate(s):
        # Add new character to window
        win[ord(ch) - ord('a')] += 1

        # Shrink window if it exceeds pattern length
        if right - left + 1 > len(p):
            win[ord(s[left]) - ord('a')] -= 1
            left += 1

        # Check if we have an anagram (frequency arrays match)
        if right - left + 1 == len(p) and win == need:
            res.append(left)

    return res


# Test cases
if __name__ == "__main__":
    test_cases = [
        ("cbaebabacd", "abc", [0, 6]),      # "cba" at 0, "bac" at 6
        ("abab", "ab", [0, 1, 2]),          # "ab" at 0, "ba" at 1, "ab" at 2
        ("baa", "aa", [1]),                 # "aa" at 1
        ("a", "a", [0]),                    # "a" at 0
        ("abc", "xyz", []),                 # No anagrams
    ]
    
    for s, p, expected in test_cases:
        result = find_anagrams(s, p)
        status = "✓" if result == expected else "✗"
        print(f"{status} find_anagrams('{s}', '{p}') = {result} (expected {expected})")
