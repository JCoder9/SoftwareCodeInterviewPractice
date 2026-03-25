"""Monotonic Stack - Next Smaller Element
For each element, find next element to right that is smaller.
Monotonic increasing stack. Pop larger elements.
Time: O(n), Space: O(n)"""

def next_smaller_element(nums):
    n = len(nums)
    result = [-1] * n
    stack = []  # Monotonic increasing stack (bottom to top)
    
    for i in range(n):
        # Pop larger elements - nums[i] is their next smaller
        while stack and nums[i] < nums[stack[-1]]:
            idx = stack.pop()
            result[idx] = nums[i]
        
        stack.append(i)
    
    return result

if __name__ == "__main__":
    nums = [4, 2, 1, 5, 3]
    print(next_smaller_element(nums))  # [2, 1, -1, 3, -1]
