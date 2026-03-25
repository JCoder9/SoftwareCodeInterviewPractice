"""
XOR Prefix - Find Subarrays with XOR = K

Problem: Count/find subarrays where XOR equals K.

Key Insight: XOR has special property: a ^ b = k means a ^ k = b!
So we track prefix XOR values in a hashmap.

Property: prefix_xor[j] ^ prefix_xor[i] = XOR of subarray from i+1 to j

Time Complexity: O(n)
Space Complexity: O(n)
"""

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
