"""
Move Zeroes to End - Same Direction Two Pointer Pattern

Problem: Move all zeros to the end of array while maintaining relative order
         of non-zero elements.

Pattern: Slow pointer tracks write position for non-zeros, fast pointer scans array.

Time Complexity: O(n) - single pass through array
Space Complexity: O(1) - in-place modification
"""

from typing import List

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force creates a new list, appends non-zeros first,
#                   then fills rest with zeros — O(n) time, O(n) space"
#   2. Problem:    "Uses extra space; can't do it in-place"
#   3. Transition: "With two pointers (slow for write position, fast for scanning)
#                   we write non-zeros in-place — same O(n) time but O(1) space"
#
# def move_zeroes_naive(nums: List[int]) -> None:
#     temp = [x for x in nums if x != 0]  # Non-zeros
#     temp.extend([0] * (len(nums) - len(temp)))  # Add zeros
#     for i in range(len(nums)):
#         nums[i] = temp[i]
# ─────────────────────────────────────────────────────────────────────────

def move_zeroes(nums: List[int]) -> None:
    """
    Move all zeros to end of array, maintaining order of non-zeros.
    
    Args:
        nums: Array to modify (modified in-place)
    """
    slow = 0  # Next position to write non-zero element
    
    # First pass: move all non-zeros to front
    for fast in range(len(nums)):
        if nums[fast] != 0:
            nums[slow] = nums[fast]
            slow += 1

    # Second pass: fill rest with zeros
    for i in range(slow, len(nums)):
        nums[i] = 0


# Test cases
if __name__ == "__main__":
    test_cases = [
        [0, 1, 0, 3, 12],
        [0],
        [1, 2, 3],
        [0, 0, 1],
        [1, 0, 0, 2, 0, 3],
    ]
    
    for nums in test_cases:
        original = nums.copy()
        move_zeroes(nums)
        print(f"{original} -> {nums}")
