"""Monotonic Stack - Next Greater Element (LC 496, 503)
For each element, find next element to right that is greater.
Maintain decreasing stack. When larger element found, it's next greater.
Time: O(n), Space: O(n)"""

def next_greater_element(nums):
    n = len(nums)
    result = [-1] * n  # Default to -1 (no greater element)
    stack = []  # Stack stores indices
    
    for i in range(n):
        # While current element is greater than stack top
        # nums[i] is the next greater for stack elements
        while stack and nums[i] > nums[stack[-1]]:
            idx = stack.pop()
            result[idx] = nums[i]
        
        stack.append(i)
    
    return result

if __name__ == "__main__":
    nums = [2, 1, 2, 4, 3]
    print(next_greater_element(nums))  # [4, 2, 4, -1, -1]
