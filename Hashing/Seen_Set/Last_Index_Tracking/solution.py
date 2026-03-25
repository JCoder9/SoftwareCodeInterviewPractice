"""
Last Index Tracking - First Unique Character Pattern

Pattern: Use hash map to track last seen index or count.

Time Complexity: O(n)
Space Complexity: O(k) where k is unique character count
"""

from typing import List, Optional
from collections import Counter


def first_unique_char(s):
    """Find the first character that doesn't repeat in a string. Returns index."""
    char_count = {}
    for char in s:
        char_count[char] = char_count.get(char, 0) + 1

    for i, char in enumerate(s):
        if char_count[char] == 1:
            return i

    return -1


def length_of_longest_substring(s):
    """Find the longest substring without repeating characters."""
    last_seen = {}
    max_length = 0
    start = 0

    for i, char in enumerate(s):
        if char in last_seen and last_seen[char] >= start:
            start = last_seen[char] + 1

        last_seen[char] = i
        max_length = max(max_length, i - start + 1)

    return max_length


def logger_rate_limiter():
    """
    LeetCode 359: Logger Rate Limiter
    
    Accept message only if not printed in last 10 seconds.
    
    Strategy: Track last timestamp for each message!
    
    Time: O(1) per operation
    Space: O(n) where n is unique messages
    """
    class Logger:
        def __init__(self):
            self.last_seen = {}
        
        def should_print_message(self, timestamp: int, message: str) -> bool:
            if message not in self.last_seen:
                self.last_seen[message] = timestamp
                return True
            
            if timestamp - self.last_seen[message] >= 10:
                self.last_seen[message] = timestamp
                return True
            
            return False
    
    return Logger()




if __name__ == "__main__":
    print("First Unique Character:")
    print(first_uniq_char("leetcode"))  # 0
    print(first_uniq_char("loveleetcode"))  # 2
    print(first_uniq_char("aabb"))  # -1
    
    print("\nUnique Morse Representations:")
