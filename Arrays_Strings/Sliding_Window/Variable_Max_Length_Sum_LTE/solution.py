"""
Maximum Length Subarray with Sum <= S - Variable-Size Sliding Window

Problem: Find the maximum length of a contiguous subarray whose sum is <= S.

Pattern: Variable window - expand to include elements, shrink when invalid.

Time Complexity: O(n) - each element visited at most twice
Space Complexity: O(1) - only tracking window sum and pointers
"""

from typing import List

def max_len_sum_at_most_s(nums: List[int], S: int) -> int:
    """
    Find maximum length of subarray with sum <= S.
    
    Args:
        nums: Input array (positive integers)
        S: Maximum sum threshold
        
    Returns:
        Maximum length of valid subarray
    """
    if not nums:
        return 0
    
    left = 0
    window_sum = 0
    best = 0  # Maximizing length, start at 0

    for right, x in enumerate(nums):
        window_sum += x

        # Shrink window while it's invalid (sum > S)
        while window_sum > S:
            window_sum -= nums[left]
            left += 1

        # Window is valid, update best
        best = max(best, right - left + 1)

    return best  # No sentinel check needed


# Test cases
if __name__ == "__main__":
    test_cases = [
        ([1, 2, 3, 4, 5], 8, 3),       # [1, 2, 3] or [3, 4] -> length 3
        ([5, 1, 1, 1, 1], 6, 5),       # [1, 1, 1, 1, 1] but sum=5, or [5, 1] -> length 2, actually all 1s = 4
        ([3, 1, 2, 1], 4, 3),          # [1, 2, 1] -> length 3
        ([1, 1, 1], 2, 2),             # [1, 1] -> length 2
        ([10], 5, 0),                  # No valid subarray
    ]
    
    for nums, S, expected in test_cases:
        result = max_len_sum_at_most_s(nums, S)
        status = "✓" if result == expected else "✗"
        print(f"{status} max_len_sum_at_most_s({nums}, S={S}) = {result} (expected {expected})")
