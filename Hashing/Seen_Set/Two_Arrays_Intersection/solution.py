"""
Two Arrays Intersection - Unique Elements Pattern

Pattern: Use set intersection to find common unique elements.

Time Complexity: O(n + m)
Space Complexity: O(min(n, m))
"""

from typing import List
from collections import Counter


def intersection(nums1, nums2):
    """Find intersection of two arrays (each element appears once in result)."""
    seen = set(nums1)
    result = set()

    for num in nums2:
        if num in seen:
            result.add(num)

    return list(result)


def remove_vowels(s: str) -> str:
    """
    LeetCode 1119: Remove Vowels from a String
    
    Remove all vowels from string.
    
    Strategy: Set membership check!
    
    Time: O(n), Space: O(1)
    """
    vowels = set('aeiou')
    return ''.join(c for c in s if c not in vowels)




if __name__ == "__main__":
    print("Intersection:")
    print(intersection([1,2,2,1], [2,2]))  # [2]
    print(intersection([4,9,5], [9,4,9,8,4]))  # [9,4] or [4,9]
    
    print("\nIntersection (Two Pointers):")
