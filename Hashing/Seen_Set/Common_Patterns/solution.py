"""
Seen Set / Last Index / Deduplication - Hash Set Pattern

Problem: Use hash sets to track seen elements for:
- Detecting duplicates
- Finding first unique element
- Checking for existence
- Tracking visited states

Pattern: Use set to remember what we've seen as we iterate.

Time Complexity: O(n) for single pass with O(1) lookups
Space Complexity: O(n) worst case for set storage
"""

from typing import List, Optional


def two_sum(nums: List[int], target: int) -> Optional[List[int]]:
    """
    Find two indices where nums[i] + nums[j] = target.
    
    Strategy: Use hash map to remember (value -> index) as we scan.
    For each number, check if (target - number) was seen before.
    
    Time: O(n), Space: O(n)
    """
    seen = {}  # value -> index
    
    for i, num in enumerate(nums):
        complement = target - num
        if complement in seen:
            return [seen[complement], i]
        seen[num] = i
    
    return None




if __name__ == "__main__":
    print("Contains Duplicate:")
    test_cases = [
        ([1, 2, 3, 1], True),
        ([1, 2, 3, 4], False),
        ([1, 1, 1, 3, 3, 4, 3, 2, 4, 2], True),
    ]
