"""
Find All Anagrams - Sliding Window + Frequency

Pattern: Find all anagram start indices using sliding window.

Time Complexity: O(n)
Space Complexity: O(1)
"""

from collections import Counter
from typing import List


def find_anagrams(s: str, p: str) -> List[int]:
    """Find all starting indices of anagram substrings."""
    result = []
    p_count = Counter(p)
    window_count = Counter()
    
    p_len = len(p)
    
    for i in range(len(s)):
        # Add new character to window
        window_count[s[i]] += 1
        
        # Remove character that's no longer in window
        if i >= p_len:
            left_char = s[i - p_len]
            window_count[left_char] -= 1
            if window_count[left_char] == 0:
                del window_count[left_char]
        
        # Check if window is an anagram
        if window_count == p_count:
            result.append(i - p_len + 1)
    
    return result


# Test
if __name__ == "__main__":
    print(find_anagrams("cbaebabacd", "abc"))  # [0, 6]
    print(find_anagrams("abab", "ab"))         # [0, 1, 2]
