"""
Group Anagrams - Hash Map Pattern

Problem: Group strings that are anagrams of each other.

Strategy: Use sorted string as key. Anagrams have same sorted representation.

Time Complexity: O(n × k log k) where n = number of strings, k = max length
Space Complexity: O(n × k) to store all strings
"""

from collections import defaultdict
from typing import List


def group_anagrams(strs: List[str]) -> List[List[str]]:
    """Group all anagrams together."""
    # Dictionary: key = sorted string, value = list of anagrams
    groups = defaultdict(list)
    
    for word in strs:
        # Sort the word to create a key
        # "eat" -> "aet", "tea" -> "aet", "ate" -> "aet"
        key = ''.join(sorted(word))
        groups[key].append(word)
    
    return list(groups.values())


# Test
if __name__ == "__main__":
    words = ["eat", "tea", "tan", "ate", "nat", "bat"]
    print(group_anagrams(words))
    # [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]
