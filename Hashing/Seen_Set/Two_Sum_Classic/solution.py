"""
Two Sum - Classic Seen-Set Pattern

Pattern: Use hash map to find complement (target - current).

Time Complexity: O(n) - single pass
Space Complexity: O(n) - storing values in map
"""

def two_sum(nums, target):
    """
    LeetCode 1: Two Sum
    
    Find two numbers that add up to target.
    Return their indices.
    """
    seen = {}  # value -> index
    
    for i, num in enumerate(nums):
        complement = target - num
        
        if complement in seen:
            return [seen[complement], i]
        
        seen[num] = i
    
    return []


# Test cases
if __name__ == "__main__":
    print(two_sum([2, 7, 11, 15], 9))   # [0, 1]
    print(two_sum([3, 2, 4], 6))        # [1, 2]
