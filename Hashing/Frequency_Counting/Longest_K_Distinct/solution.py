"""
K Distinct Characters - Sliding Window Pattern

Pattern: Substring problems with exactly/at most K distinct characters.

Time Complexity: O(n)
Space Complexity: O(k)
"""

from collections import defaultdict


def length_of_longest_substring_k_distinct(s: str, k: int) -> int:
    """Longest substring with at most K distinct characters."""
    if k == 0:
        return 0
    
    count = defaultdict(int)
    left = 0
    max_length = 0
    
    for right in range(len(s)):
        # Add character to window
        count[s[right]] += 1
        
        # Shrink window if too many distinct characters
        while len(count) > k:
            count[s[left]] -= 1
            if count[s[left]] == 0:
                del count[s[left]]
            left += 1
        
        # Update max length
        max_length = max(max_length, right - left + 1)
    
    return max_length


# Test
if __name__ == "__main__":
    print(length_of_longest_substring_k_distinct("eceba", 2))  # 3
    print(length_of_longest_substring_k_distinct("aa", 1))     # 2
