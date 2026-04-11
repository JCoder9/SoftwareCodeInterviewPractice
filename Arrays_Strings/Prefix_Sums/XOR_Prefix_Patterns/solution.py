"""
XOR Prefix - Find Subarrays with XOR = K

Problem: Count/find subarrays where XOR equals K.
         Example: nums = [4,2,2,6,4], k = 6 → 4 subarrays

Key Insight: XOR property: a ^ b = k means a ^ k = b!
             Track prefix XOR values in hashmap.

Related LeetCode Problems:
- LC 1310: XOR Queries of a Subarray (Medium) ⭐⭐
- LC 1442: Count Triplets That Can Form Two Arrays of Equal XOR (Medium)
- LC 136: Single Number (Easy) ⭐⭐⭐

Property: prefix_xor[j] ^ prefix_xor[i] = XOR of subarray from i+1 to j

Time Complexity: O(n) - single pass with hashmap lookups
Space Complexity: O(n) - hashmap storage
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks all subarrays with nested loops, computes XOR
#                  for each — O(n²)"
#   2. Problem:    "For n=1000: 1000² = 1 million subarray checks; redundant XOR
#                  computations"
#   3. Transition: "Use prefix XOR with hashmap to find matching subarrays in
#                  one pass — O(n)"
#
# def count_xor_pairs_naive(nums, k):
#     count = 0
#     n = len(nums)
#     for i in range(n):
#         xor_val = 0
#         for j in range(i, n):
#             xor_val ^= nums[j]
#             if xor_val == k:
#                 count += 1
#     return count
# ─────────────────────────────────────────────────────────────────────────────

from typing import List


def count_xor_pairs(nums, k):
    """Count pairs with XOR equal to k"""
    count = 0
    xor_prefix = 0
    # HashMap: prefix_xor -> count
    prefix_map = {0: 1}
    
    for num in nums:
        xor_prefix ^= num
        
        # If (xor_prefix ^ k) exists, we found subarrays!
        # Because: prefix_xor ^ previous_xor = k
        target = xor_prefix ^ k
        if target in prefix_map:
            count += prefix_map[target]
        
        prefix_map[xor_prefix] = prefix_map.get(xor_prefix, 0) + 1
    
    return count


# Example usage
if __name__ == "__main__":
    print(count_xor_pairs([4, 2, 2, 6, 4], 6))  # 4
    print(count_xor_pairs([1, 2, 3], 0))  # 0
