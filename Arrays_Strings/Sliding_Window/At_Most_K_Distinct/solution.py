"""
Longest Substring with At Most K Distinct Characters - Frequency Map Sliding Window

Problem: Find the length of the longest substring with at most K distinct characters.

Pattern: Variable window with frequency map - shrink when distinct count exceeds K.

Time Complexity: O(n) - each character visited at most twice
Space Complexity: O(min(k, alphabet)) - at most k+1 chars in map before shrinking
"""

from collections import defaultdict

def longest_at_most_k_distinct(s: str, k: int) -> int:
    """
    Find longest substring with at most K distinct characters.
    
    Args:
        s: Input string
        k: Maximum number of distinct characters allowed
        
    Returns:
        Length of longest valid substring
    """
    if not s or k <= 0:
        return 0  # No valid substring

    count = defaultdict(int)
    left = 0
    best = 0  # Length can be 0 if empty, so start at 0

    for right, ch in enumerate(s):
        count[ch] += 1

        # Shrink window while we have too many distinct characters
        while len(count) > k:
            count[s[left]] -= 1
            if count[s[left]] == 0:
                del count[s[left]]
            left += 1

        # Window is valid, update best
        best = max(best, right - left + 1)

    return best


# Test cases
if __name__ == "__main__":
    test_cases = [
        ("eceba", 2, 3),      # "ece" -> length 3
        ("aa", 1, 2),         # "aa" -> length 2
        ("a", 2, 1),          # "a" -> length 1
        ("abcabc", 2, 2),     # "ab", "bc", "ca", etc. -> length 2
        ("abcabcabc", 3, 9),  # "abcabcabc" -> length 9
    ]
    
    for s, k, expected in test_cases:
        result = longest_at_most_k_distinct(s, k)
        status = "✓" if result == expected else "✗"
        print(f"{status} longest_at_most_k_distinct('{s}', k={k}) = {result} (expected {expected})")
