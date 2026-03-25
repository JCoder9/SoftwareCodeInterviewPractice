"""
Subarray Sum Equals K - Prefix Sum + Hash Map Pattern

Problem: Count/find subarrays whose sum equals a target value K.

Pattern: Use prefix sums with hash map
- Key insight: If prefix[j] - prefix[i] = K, then subarray[i:j] sums to K
- Store prefix sums in map as we iterate
- For each position, check if (current_prefix - K) exists in map

Time Complexity: O(n) - single pass
Space Complexity: O(n) - hash map of prefix sums
"""

from typing import List
from collections import defaultdict

def subarray_sum_equals_k(nums: List[int], k: int) -> int:
    """
    Count number of contiguous subarrays that sum to k.
    
    Strategy:
    - Track running prefix sum
    - Use map to store {prefix_sum: count}
    - If (prefix_sum - k) exists, add its count to result
    
    Time: O(n), Space: O(n)
    """
    count = 0
    prefix_sum = 0
    prefix_map = defaultdict(int)
    prefix_map[0] = 1  # Empty prefix (sum = 0)
    
    for num in nums:
        prefix_sum += num
        
        # Check if we can form subarray ending here with sum = k
        if prefix_sum - k in prefix_map:
            count += prefix_map[prefix_sum - k]
        
        # Add current prefix sum to map
        prefix_map[prefix_sum] += 1
    
    return count


def max_subarray_sum_equals_k(nums: List[int], k: int) -> int:
    """
    Find the maximum length of a subarray that sums to k.
    
    Strategy: Store first occurrence index of each prefix sum.
    If we see same prefix sum again, the subarray between has sum 0.
    If we see (prefix - k), we found a subarray summing to k.
    
    Time: O(n), Space: O(n)
    """
    prefix_sum = 0
    first_occurrence = {0: -1}  # prefix_sum -> first index
    max_length = 0
    
    for i, num in enumerate(nums):
        prefix_sum += num
        
        # Check if subarray ending here sums to k
        if prefix_sum - k in first_occurrence:
            length = i - first_occurrence[prefix_sum - k]
            max_length = max(max_length, length)
        
        # Only store first occurrence
        if prefix_sum not in first_occurrence:
            first_occurrence[prefix_sum] = i
    
    return max_length


def continuous_subarray_sum_multiple_k(nums: List[int], k: int) -> bool:
    """
    Check if array has a continuous subarray of size >= 2 that sums to multiple of k.
    
    Strategy: Use modulo arithmetic with prefix sums.
    If two prefix sums have same remainder when divided by k,
    the subarray between them is divisible by k.
    
    Time: O(n), Space: O(min(n, k))
    """
    if k == 0:
        # Special case: need at least two consecutive zeros
        for i in range(len(nums) - 1):
            if nums[i] == 0 and nums[i + 1] == 0:
                return True
        return False
    
    prefix_sum = 0
    remainder_seen = {0: -1}  # remainder -> index
    
    for i, num in enumerate(nums):
        prefix_sum += num
        remainder = prefix_sum % k
        
        if remainder in remainder_seen:
            # Found subarray divisible by k
            if i - remainder_seen[remainder] >= 2:
                return True
        else:
            remainder_seen[remainder] = i
    
    return False


# Test cases
if __name__ == "__main__":
    print("Subarray Sum Equals K:")
    test_cases = [
        ([1, 1, 1], 2, 2),           # [1,1] appears twice
        ([1, 2, 3], 3, 2),           # [1,2] and [3]
        ([1, -1, 0], 0, 3),          # [1,-1], [0], [1,-1,0]
    ]
    for nums, k, expected in test_cases:
        result = subarray_sum_equals_k(nums, k)
        status = "✓" if result == expected else "✗"
        print(f"{status} subarray_sum_equals_k({nums}, k={k}) = {result}")
    
    print("\nMax Length Subarray Sum Equals K:")
    test_cases2 = [
        ([1, -1, 5, -2, 3], 3, 4),   # [-1, 5, -2, 3] has length 4
        ([1, 0, -1], 0, 3),          # [1, 0, -1] has length 3
    ]
    for nums, k, expected in test_cases2:
        result = max_subarray_sum_equals_k(nums, k)
        status = "✓" if result == expected else "✗"
        print(f"{status} max_subarray_sum_equals_k({nums}, k={k}) = {result}")
    
    print("\nContinuous Subarray Sum (Multiple of K):")
    test_cases3 = [
        ([23, 2, 4, 6, 7], 6, True),    # [2,4] sums to 6
        ([23, 2, 6, 4, 7], 6, True),    # [23,2,6,4,7] sums to 42 = 6*7
        ([23, 2, 6, 4, 7], 13, False),
    ]
    for nums, k, expected in test_cases3:
        result = continuous_subarray_sum_multiple_k(nums, k)
        status = "✓" if result == expected else "✗"
        print(f"{status} continuous_subarray_sum_multiple_k({nums[:3]}..., k={k}) = {result}")
