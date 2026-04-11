"""
Binary Search on Answer - Minimize the Maximum (LC 410)

Problem: Split Array Largest Sum - Split into k subarrays minimizing max sum.
         Example: nums = [7,2,5,10,8], k = 2 → 18 ([7,2,5,10] and [8])

Pattern: If we can split with max_sum=X, we can with X+1. Binary search for min X.

Related LeetCode Problems:
- LC 410: Split Array Largest Sum (Hard) ⭐⭐⭐
- LC 1011: Capacity To Ship Packages Within D Days (Medium)
- LC 1231: Divide Chocolate (Hard)

Time Complexity: O(n × log(sum - max)) - n validations, log search space
Space Complexity: O(1)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all ways to place k-1 dividers among n-1 gaps —
#                  C(n-1, k-1) = exponential combinations"
#   2. Problem:    "For n=100, k=50: ~10^29 combinations to check"
#   3. Transition: "Binary search on answer (max_sum range) with greedy validation —
#                  O(n log sum)"
#
# def split_array_naive(nums, k):
#     from itertools import combinations
#     n = len(nums)
#     min_max_sum = float('inf')
#     
#     # Generate all ways to place k-1 dividers
#     for dividers in combinations(range(1, n), k - 1):
#         dividers = [0] + list(dividers) + [n]
#         max_sum = 0
#         for i in range(len(dividers) - 1):
#             subarray_sum = sum(nums[dividers[i]:dividers[i + 1]])
#             max_sum = max(max_sum, subarray_sum)
#         min_max_sum = min(min_max_sum, max_sum)
#     return min_max_sum
# ─────────────────────────────────────────────────────────────────────────────

def splitArray(nums, k):
    def can_split(max_sum):
        # Can we split into k subarrays with each sum <= max_sum?
        groups = 1
        current_sum = 0
        
        for num in nums:
            if current_sum + num > max_sum:
                groups += 1
                current_sum = num
                if groups > k:
                    return False
            else:
                current_sum += num
        
        return True
    
    # Search space: [largest element, sum of all elements]
    left = max(nums)  # At minimum, one subarray must have the largest element
    right = sum(nums)  # At maximum, all elements in one subarray
    
    while left < right:
        mid = left + (right - left) // 2
        
        if can_split(mid):
            # If we can split with max_sum = mid, try smaller
            right = mid
        else:
            # If we can't, need larger max_sum
            left = mid + 1
    
    return left

if __name__ == "__main__":
    print(splitArray([7, 2, 5, 10, 8], 2))  # 18
