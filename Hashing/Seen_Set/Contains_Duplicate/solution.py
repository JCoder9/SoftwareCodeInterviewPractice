"""
Contains Duplicate - Boolean Check Pattern

Pattern: Check if duplicates exist using set.

Time Complexity: O(n)
Space Complexity: O(n)
"""

def contains_duplicate(nums):
    """
    LeetCode 217: Contains Duplicate
    Check if array has any duplicates.
    """
    seen = set()
    
    for num in nums:
        if num in seen:
            return True
        seen.add(num)
    
    return False


# Test cases
if __name__ == "__main__":
    print(contains_duplicate([1, 2, 3, 4]))  # False
    print(contains_duplicate([1, 2, 3, 1]))  # True
