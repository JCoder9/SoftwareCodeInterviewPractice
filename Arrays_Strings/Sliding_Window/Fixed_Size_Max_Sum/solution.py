"""
Maximum Sum of Subarray Size K - Fixed-Size Sliding Window

Problem: Find the maximum sum of any subarray of length k.

Pattern: Fixed window size - add new element, remove old element when window exceeds k.

Time Complexity: O(n) - single pass through array
Space Complexity: O(1) - only tracking window sum
"""

from typing import List, Optional

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n × k) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force recalculates the sum for each window of size k
#                   from scratch — O(n × k) time"
#   2. Problem:    "For n=10⁵ and k=10³, we'd do ~10⁸ operations"
#   3. Transition: "With a sliding window we maintain the sum incrementally:
#                   add new element, subtract old — drops to O(n)"
#
# def max_sum_k_naive(nums: List[int], k: int) -> Optional[int]:
#     if len(nums) < k or k <= 0:
#         return None
#     
#     best = float('-inf')
#     for i in range(len(nums) - k + 1):
#         window_sum = sum(nums[i:i + k])
#         best = max(best, window_sum)
#     return best
# ─────────────────────────────────────────────────────────────────────────

def max_sum_k(nums: List[int], k: int) -> Optional[int]:
    """
    Find maximum sum of any subarray of length k.
    
    Args:
        nums: Input array
        k: Window size
        
    Returns:
        Maximum sum, or None if no valid window possible
    """
    if len(nums) < k or k <= 0:
        return None  # No valid window possible
    
    window_sum = 0
    best = float("-inf")  # Safe now because we validated k
    left = 0

    for right, x in enumerate(nums):
        window_sum += x

        # Shrink window if it exceeds size k
        if right - left + 1 > k:
            window_sum -= nums[left]
            left += 1

        # Update best when window is exactly size k
        if right - left + 1 == k:
            best = max(best, window_sum)

    return best


# Test cases
if __name__ == "__main__":
    test_cases = [
        ([1, 4, 2, 10, 23, 3, 1, 0, 20], 4, 39),  # [4, 2, 10, 23]
        ([2, 3], 3, None),                         # k > len(nums)
        ([1, -1, 5, -2, 3], 2, 4),                # [5, -2] -> sum = 3, actually [5,-2]=3, [-1,5]=4
        ([1, -1, 5, -2, 3], 3, 6),                # [5, -2, 3]
        ([5], 1, 5),
    ]
    
    for nums, k, expected in test_cases:
        result = max_sum_k(nums, k)
        status = "✓" if result == expected else "✗"
        print(f"{status} max_sum_k({nums}, k={k}) = {result} (expected {expected})")
