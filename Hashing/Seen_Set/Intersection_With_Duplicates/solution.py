"""
Intersection With Duplicates - Count-Based Pattern

Pattern: Use hash map to track counts for intersection with duplicates.

Time Complexity: O(n + m)
Space Complexity: O(min(n, m))
"""

from typing import List
from collections import Counter


def intersect(nums1, nums2):
    """Find intersection where duplicates count."""
    count1 = Counter(nums1)
    result = []

    for num in nums2:
        if count1[num] > 0:
            result.append(num)
            count1[num] -= 1

    return result


def find_pairs(nums: List[int], k: int) -> int:
    """
    LeetCode 532: K-diff Pairs in an Array
    
    Find number of unique k-diff pairs.
    
    Strategy: HashMap with count tracking!
    
    Time: O(n), Space: O(n)
    """
    if k < 0:
        return 0
    
    count = Counter(nums)
    result = 0
    
    for num in count:
        if k == 0:
            # For k=0, need at least 2 occurrences
            if count[num] > 1:
                result += 1
        else:
            # Check if num + k exists
            if num + k in count:
                result += 1
    
    return result




if __name__ == "__main__":
    print("Intersection II:")
    print(intersect([1,2,2,1], [2,2]))  # [2,2]
    print(intersect([4,9,5], [9,4,9,8,4]))  # [4,9] or [9,4]
    
    print("\nFind Common Characters:")
