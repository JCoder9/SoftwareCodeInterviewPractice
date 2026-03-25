"""
Frequency Counting - Ransom Note, First Unique, Top K Patterns

Pattern: Count character/element frequencies for various tasks.

Time Complexity: O(n) for counting
Space Complexity: O(k) where k = unique elements
"""

from collections import Counter
from typing import List
import heapq


def can_construct(ransom_note: str, magazine: str) -> bool:
    """Can you construct a ransom note from magazine letters?"""
    # Count available letters
    available = Counter(magazine)
    
    # Try to use letters for ransom note
    for char in ransom_note:
        if available[char] <= 0:
            return False
        available[char] -= 1
    
    return True


def first_uniq_char(s: str) -> int:
    """Find the first character that appears only once."""
    # Count all characters
    count = Counter(s)
    
    # Find first character with count 1
    for i, char in enumerate(s):
        if count[char] == 1:
            return i
    
    return -1


def top_k_frequent(nums: List[int], k: int) -> List[int]:
    """Return the K most frequent elements."""
    # Count frequencies
    count = Counter(nums)
    
    # Use heap to get top k
    return heapq.nlargest(k, count.keys(), key=count.get)


# Test cases
if __name__ == "__main__":
    print("Ransom Note:")
    print(can_construct("aa", "aab"))  # True
    print(can_construct("aa", "ab"))   # False
    
    print("\nFirst Unique Character:")
    print(first_uniq_char("leetcode"))      # 0
    print(first_uniq_char("loveleetcode"))  # 2
    print(first_uniq_char("aabb"))          # -1
    
    print("\nTop K Frequent:")
    print(top_k_frequent([1,1,1,2,2,3], 2))  # [1, 2]
    print(top_k_frequent([1], 1))            # [1]
