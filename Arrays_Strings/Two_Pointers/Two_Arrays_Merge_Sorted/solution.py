"""
Merge Two Sorted Arrays - Two Pointer Pattern Across Arrays

Problem: Merge two sorted arrays into a single sorted array.

Pattern: One pointer for each array, compare and merge.

Time Complexity: O(m + n) - single pass through both arrays
Space Complexity: O(m + n) - for output array
"""

from typing import List

def merge_sorted(a: List[int], b: List[int]) -> List[int]:
    """
    Merge two sorted arrays into a single sorted array.
    
    Args:
        a: First sorted array
        b: Second sorted array
        
    Returns:
        Merged sorted array containing all elements from both arrays
    """
    i = j = 0
    out = []
    
    # Merge elements while both arrays have remaining elements
    while i < len(a) and j < len(b):
        if a[i] <= b[j]:
            out.append(a[i])
            i += 1
        else:
            out.append(b[j])
            j += 1

    # Append remaining elements from a (if any)
    out.extend(a[i:])
    
    # Append remaining elements from b (if any)
    out.extend(b[j:])
    
    return out


# Test cases
if __name__ == "__main__":
    test_cases = [
        ([1, 3, 5], [2, 4, 6], [1, 2, 3, 4, 5, 6]),
        ([1, 2, 3], [4, 5, 6], [1, 2, 3, 4, 5, 6]),
        ([], [1, 2, 3], [1, 2, 3]),
        ([1, 2, 3], [], [1, 2, 3]),
        ([1], [2], [1, 2]),
    ]
    
    for a, b, expected in test_cases:
        result = merge_sorted(a, b)
        status = "✓" if result == expected else "✗"
        print(f"{status} merge_sorted({a}, {b}) = {result}")
