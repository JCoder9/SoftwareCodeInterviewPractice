"""
Sliding Window Maximum - Monotonic Deque Pattern

Problem: Given an array and window size k, find the maximum element in each
         sliding window as it moves from left to right.

Pattern: Use a deque to maintain indices of elements in decreasing order of values.
         Front of deque always contains index of maximum element in current window.

Time Complexity: O(n) - each element added and removed from deque at most once
Space Complexity: O(k) - deque holds at most k elements
"""

from collections import deque
from typing import List

def max_sliding_window(nums: List[int], k: int) -> List[int]:
    """
    Find maximum in each sliding window of size k.
    
    Args:
        nums: Input array
        k: Window size
        
    Returns:
        List of maximum values for each window position
    """
    if not nums or k <= 0 or k > len(nums):
        return []  # No valid windows
    
    dq = deque()  # Stores indices, values at indices are in decreasing order
    res = []

    for i, x in enumerate(nums):
        # Remove indices of smaller elements from back
        # (they can never be maximum while current element is in window)
        while dq and nums[dq[-1]] <= x:
            dq.pop()
        dq.append(i)

        # Remove indices outside current window from front
        if dq[0] <= i - k:
            dq.popleft()

        # Once we have a full window, record maximum (front of deque)
        if i >= k - 1:
            res.append(nums[dq[0]])

    return res


# Test cases
if __name__ == "__main__":
    test_cases = [
        ([1, 3, -1, -3, 5, 3, 6, 7], 3, [3, 3, 5, 5, 6, 7]),
        ([1], 1, [1]),
        ([1, -1], 1, [1, -1]),
        ([9, 11], 2, [11]),
        ([4, -2], 2, [4]),
    ]
    
    for nums, k, expected in test_cases:
        result = max_sliding_window(nums, k)
        status = "✓" if result == expected else "✗"
        print(f"{status} max_sliding_window({nums}, k={k}) = {result} (expected {expected})")
