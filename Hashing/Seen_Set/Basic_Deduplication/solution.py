"""
Basic Deduplication - Remove Duplicates Preserving Order

Pattern: Track what we've seen to remove duplicates.

Time Complexity: O(n)
Space Complexity: O(n)
"""

from typing import List


def remove_duplicates(arr):
    """Remove duplicates from array while preserving order."""
    seen = set()
    result = []

    for num in arr:
        if num not in seen:
            seen.add(num)
            result.append(num)

    return result


def length_of_longest_substring_without_repeating(s: str) -> int:
    """
    LeetCode 3: Longest Substring Without Repeating Characters
    
    Find length of longest substring without repeating characters.
    
    Strategy: Sliding window + set!
    
    Time: O(n), Space: O(min(n, charset))
    """
    seen = set()
    left = 0
    max_length = 0
    
    for right in range(len(s)):
        # Shrink window while duplicate exists
        while s[right] in seen:
            seen.remove(s[left])
            left += 1
        
        seen.add(s[right])
        max_length = max(max_length, right - left + 1)
    
    return max_length




if __name__ == "__main__":
    print("Remove Duplicates:")
    print(remove_duplicates([1, 2, 2, 3, 1, 4]))  # [1, 2, 3, 4]
    
    print("\nRemove Duplicate Letters:")
