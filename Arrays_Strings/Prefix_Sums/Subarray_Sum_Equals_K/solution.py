"""
Subarray Sum Equals K - Prefix Sum + HashMap Pattern

LeetCode 560 - Extremely common at Google, Facebook, Amazon, Microsoft

Problem: Given an array and integer k, count how many contiguous subarrays sum to k.

Key Insight: If prefix[j] - prefix[i] = k, then subarray from i+1 to j sums to k.
             Rearranging: prefix[i] = prefix[j] - k
             So while at position j, check if (currentSum - k) was seen before!

Time Complexity: O(n)
Space Complexity: O(n) for HashMap
"""

from typing import List
from collections import defaultdict

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Check every possible subarray - for each starting position,
#                   extend right and track sum until we hit k"
#   2. Problem:    "This is O(n²) with nested loops. For n=10⁴, that's 10⁸ operations"
#   3. Transition: "With prefix sums + HashMap, we can do it in O(n) with single pass.
#                   The key is: if we've seen (currentSum - k) before, those positions
#                   start subarrays that end here and sum to k"
#
# def subarray_sum_brute_force(nums, k):
#     count = 0
#     for start in range(len(nums)):
#         current_sum = 0
#         for end in range(start, len(nums)):
#             current_sum += nums[end]
#             if current_sum == k:
#                 count += 1
#     return count
# ─────────────────────────────────────────────────────────────────────────

def subarray_sum_equals_k(nums: List[int], k: int) -> int:
    """
    Count subarrays with sum equals k.
    
    HashMap stores: prefixSum -> frequency (how many times we've seen this prefix sum)
    Why frequency? Same prefix sum can appear multiple times!
    
    Example: nums = [1, -1, 1, -1, 1], k = 0
             prefixSum cycles: 1, 0, 1, 0, 1
             Multiple ways to form subarrays with sum = 0
    """
    count = 0
    prefix_sum = 0
    prefix_map = {0: 1}  # Handle subarrays starting from index 0
    
    for num in nums:
        prefix_sum += num
        
        # If (prefix_sum - k) exists, those positions can start subarrays ending here
        if prefix_sum - k in prefix_map:
            count += prefix_map[prefix_sum - k]
        
        # Record current prefix sum
        prefix_map[prefix_sum] = prefix_map.get(prefix_sum, 0) + 1
    
    return count


def max_subarray_len_equals_k(nums: List[int], k: int) -> int:
    """
    VARIANT: Find maximum length of subarray with sum equals k.
    
    Strategy: Store first occurrence of each prefix sum (want longest subarray)
    """
    max_len = 0
    prefix_sum = 0
    prefix_map = {0: -1}  # For subarrays starting from index 0
    
    for i, num in enumerate(nums):
        prefix_sum += num
        
        if prefix_sum - k in prefix_map:
            max_len = max(max_len, i - prefix_map[prefix_sum - k])
        
        # Only store FIRST occurrence (want max length)
        if prefix_sum not in prefix_map:
            prefix_map[prefix_sum] = i
    
    return max_len


def check_subarray_sum_multiple_k(nums: List[int], k: int) -> bool:
    """
    VARIANT (LC 523): Check if subarray sum is multiple of k (length >= 2).
    
    Key: Two sums with same remainder (mod k) means the subarray between them is divisible by k.
    """
    if len(nums) < 2:
        return False
    
    remainder_map = {0: -1}
    prefix_sum = 0
    
    for i, num in enumerate(nums):
        prefix_sum += num
        remainder = prefix_sum % k if k != 0 else prefix_sum
        
        if remainder in remainder_map:
            if i - remainder_map[remainder] >= 2:
                return True
        else:
            remainder_map[remainder] = i
    
    return False


def subarrays_divisible_by_k(nums: List[int], k: int) -> int:
    """
    VARIANT (LC 974): Count subarrays with sum divisible by k.
    
    Handle negative remainders: (sum % k + k) % k ensures positive remainder
    """
    count = 0
    prefix_sum = 0
    remainder_count = defaultdict(int)
    remainder_count[0] = 1
    
    for num in nums:
        prefix_sum += num
        remainder = (prefix_sum % k + k) % k  # Handle negative remainders
        
        count += remainder_count[remainder]
        remainder_count[remainder] += 1
    
    return count


# Example usage
if __name__ == "__main__":
    print("=== Subarray Sum Equals K (LC 560) ===")
    test_cases = [
        ([1, 1, 1], 2, 2),           # [1,1] at [0,1] and [1,2]
        ([1, 2, 3], 3, 2),           # [3] and [1,2]
        ([1, -1, 0], 0, 3)           # [-1,1], [0], [-1,1,0]
    ]
    for nums, k, expected in test_cases:
        result = subarray_sum_equals_k(nums, k)
        status = "✓" if result == expected else "✗"
        print(f"{status} subarray_sum_equals_k({nums}, k={k}) = {result}")
    
    print("\n=== Maximum Length Subarray Sum K ===")
    print(f"max_len([1,-1,5,-2,3], k=3) = {max_subarray_len_equals_k([1,-1,5,-2,3], 3)}")  # 4: [-1,5,-2,3]
    
    print("\n=== Subarray Sum Multiple of K (LC 523) ===")
    print(f"multiple([23,2,4,6,7], k=6) = {check_subarray_sum_multiple_k([23,2,4,6,7], 6)}")  # True: [2,4]
    
    print("\n=== Count Divisible by K (LC 974) ===")
    print(f"divisible([4,5,0,-2,-3,1], k=5) = {subarrays_divisible_by_k([4,5,0,-2,-3,1], 5)}")  # 7
