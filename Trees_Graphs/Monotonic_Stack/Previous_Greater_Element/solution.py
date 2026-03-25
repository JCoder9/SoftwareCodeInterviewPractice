"""Monotonic Stack - Previous Greater Element
For each element, find previous element to left that is greater.
Monotonic decreasing stack. Check stack top for previous greater.
Time: O(n), Space: O(n)"""

def previous_greater_element(nums):
    n = len(nums)
    result = [-1] * n
    stack = []  # Monotonic decreasing stack
    
    for i in range(n):
        # Remove elements smaller than or equal to current
        while stack and nums[stack[-1]] <= nums[i]:
            stack.pop()
        
        # If stack not empty, top is the previous greater
        if stack:
            result[i] = nums[stack[-1]]
        
        stack.append(i)
    
    return result

if __name__ == "__main__":
    nums = [4, 2, 3, 1, 5]
    print(previous_greater_element(nums))  # [-1, 4, 4, 3, -1]
