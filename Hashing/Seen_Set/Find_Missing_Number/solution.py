"""
Find Missing Number - Various Detection Patterns

Pattern: Use different techniques to find missing/duplicate numbers.

Time Complexity: O(n)
Space Complexity: O(1) to O(n) depending on approach
"""

from typing import List


def missing_number(nums):
    """Find missing number from 0 to n using set."""
    seen = set(nums)

    for i in range(len(nums) + 1):
        if i not in seen:
            return i

    return -1


def missing_number_math(nums):
    """Find missing number using math (sum formula). O(1) space."""
    n = len(nums)
    expected_sum = n * (n + 1) // 2
    actual_sum = sum(nums)
    return expected_sum - actual_sum


def missing_two_numbers(nums: List[int]) -> List[int]:
    """
    Find two missing numbers from [1, n+2].
    
    Strategy: Math + bit manipulation!
    
    Time: O(n), Space: O(1)
    """
    n = len(nums) + 2
    
    # Total XOR of all numbers and array
    xor_all = 0
    for i in range(1, n + 1):
        xor_all ^= i
    
    for num in nums:
        xor_all ^= num
    
    # xor_all = missing1 ^ missing2
    # Find rightmost set bit
    rightmost_bit = xor_all & -xor_all
    
    # Partition numbers by this bit
    missing1 = missing2 = 0
    
    for i in range(1, n + 1):
        if i & rightmost_bit:
            missing1 ^= i
        else:
            missing2 ^= i
    
    for num in nums:
        if num & rightmost_bit:
            missing1 ^= num
        else:
            missing2 ^= num
    
    return [missing1, missing2]




if __name__ == "__main__":
    print("Missing Number:")
    print(missing_number([3,0,1]))  # 2
    print(missing_number([0,1]))  # 2
    print(missing_number([9,6,4,2,3,5,7,0,1]))  # 8
    
    print("\nFirst Missing Positive:")
