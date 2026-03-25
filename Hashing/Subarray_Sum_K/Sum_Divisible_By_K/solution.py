"""
Sum Divisible by K - Modulo Arithmetic Pattern

Pattern: Use prefix sum modulo with hash map.

Time Complexity: O(n)
Space Complexity: O(k)
"""

from typing import List


def subarray_sum_divisible_by_k(nums, k):
    """Count subarrays where sum % k == 0"""
    # Map: remainder -> count
    remainder_map = {0: 1}  # Base case
    
    current_sum = 0
    count = 0
    
    for num in nums:
        current_sum += num
        
        # Get remainder (handle negatives properly)
        remainder = current_sum % k
        if remainder < 0:
            remainder += k
        
        # If we've seen this remainder before, we found subarrays
        if remainder in remainder_map:
            count += remainder_map[remainder]
        
        # Add to map
        remainder_map[remainder] = remainder_map.get(remainder, 0) + 1
    
    return count


# Test
if __name__ == "__main__":
    print(subarray_sum_divisible_by_k([4, 5, 0, -2, -3, 1], 5))  # 7
    print(subarray_sum_divisible_by_k([5], 9))                    # 0
    print(subarray_sum_divisible_by_k([2, -2, 2, -4], 2))         # 6
