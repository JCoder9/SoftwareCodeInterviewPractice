"""
Monotonic Stack - Next Greater Element

Problem: For each element in array, find the next greater element to its right.
         Return -1 if no greater element exists.

Pattern: Maintain a decreasing monotonic stack (stores indices). When we find a larger
         element, it's the "next greater" for all smaller elements we pop.

Related LeetCode Problems:
- LC 496: Next Greater Element I (Easy) ⭐⭐
- LC 503: Next Greater Element II (Medium) - circular array
- LC 739: Daily Temperatures (Medium) ⭐⭐⭐
- LC 42: Trapping Rain Water (Hard) ⭐⭐⭐

Time Complexity: O(n) - each element pushed/popped once
Space Complexity: O(n) - stack storage
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force: for each element, scan right until we find a larger
#                  element — nested loops give O(n²)"
#   2. Problem:    "For n=10,000: 50 million comparisons worst case (decreasing array);
#                  very slow"
#   3. Transition: "Monotonic stack tracks pending elements in one pass; when we find
#                  next greater, pop all smaller — O(n) total"
#
# def next_greater_element_naive(nums):
#     n = len(nums)
#     result = [-1] * n
#     
#     for i in range(n):
#         for j in range(i + 1, n):
#             if nums[j] > nums[i]:
#                 result[i] = nums[j]
#                 break
#     return result
# ─────────────────────────────────────────────────────────────────────────────

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
