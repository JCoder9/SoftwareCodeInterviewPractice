"""
Remove Duplicates from Sorted Array - Same Direction Two Pointer Pattern

Problem: Remove duplicates from a sorted array in-place, keeping each unique value once.
         Return the new length.

Pattern: Slow pointer tracks write position, fast pointer scans array.

Time Complexity: O(n) - single pass through array
Space Complexity: O(1) - in-place modification
"""

from typing import List

def remove_duplicates(nums: List[int]) -> int:
    """
    Remove duplicates from sorted array in-place.
    
    Args:
        nums: Sorted array (modified in-place)
        
    Returns:
        New length of array with unique elements
    """
    if not nums:
        return 0

    slow = 1  # Next write position (first element always stays)
    
    for fast in range(1, len(nums)):
        # If current element is different from previous, keep it
        if nums[fast] != nums[fast - 1]:
            nums[slow] = nums[fast]
            slow += 1
    
    return slow


# ─────────────────────────────────────────────────────────────────────────────
# VARIANT 1: Return a new array  (O(n) space)
# Asked as: "return the deduplicated array" / "don't modify the input"
# ─────────────────────────────────────────────────────────────────────────────
def remove_duplicates_return_new(nums: List[int]) -> List[int]:
    if not nums:
        return []

    result = [nums[0]]
    for i in range(1, len(nums)):
        if nums[i] != nums[i - 1]:
            result.append(nums[i])

    return result


# ─────────────────────────────────────────────────────────────────────────────
# VARIANT 2: In-place, return the array  (O(1) space)
# Asked as: "return the modified array" while still doing it in-place.
# Same two-pointer logic; trim the tail with del, then return the list itself.
# ─────────────────────────────────────────────────────────────────────────────
def remove_duplicates_inplace_return_array(nums: List[int]) -> List[int]:
    if not nums:
        return nums

    slow = 1
    for fast in range(1, len(nums)):
        if nums[fast] != nums[fast - 1]:
            nums[slow] = nums[fast]
            slow += 1

    del nums[slow:]  # Trim excess elements in-place
    return nums


# Test cases
if __name__ == "__main__":
    test_cases = [
        ([1, 1, 2], 2, [1, 2]),
        ([0, 0, 1, 1, 1, 2, 2, 3, 3, 4], 5, [0, 1, 2, 3, 4]),
        ([1], 1, [1]),
        ([1, 2, 3], 3, [1, 2, 3]),
        ([], 0, []),
    ]
    
    for nums, expected_len, expected_vals in test_cases:
        original = nums.copy()
        length = remove_duplicates(nums)
        unique_vals = nums[:length]
        status = "✓" if length == expected_len and unique_vals == expected_vals else "✗"
        print(f"{status} {original} -> length={length}, values={unique_vals}")

    print("\n--- Variant 1: return new array ---")
    for nums, _, expected_vals in test_cases:
        result = remove_duplicates_return_new(nums)
        status = "✓" if result == expected_vals else "✗"
        print(f"{status} {nums} -> {result}")

    print("\n--- Variant 2: in-place, return array ---")
    for nums, _, expected_vals in test_cases:
        result = remove_duplicates_inplace_return_array(nums.copy())
        status = "✓" if result == expected_vals else "✗"
        print(f"{status} {nums} -> {result}")
