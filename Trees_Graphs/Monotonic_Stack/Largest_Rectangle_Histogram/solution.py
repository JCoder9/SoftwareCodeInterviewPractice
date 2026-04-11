"""
Monotonic Stack - Largest Rectangle in Histogram (LC 84)

Problem: Find largest rectangular area in histogram.
         Example: heights = [2,1,5,6,2,3] → 10 (width=2, height=5)

Pattern: For each bar, find previous/next smaller elements to determine width

Related LeetCode Problems:
- LC 84: Largest Rectangle in Histogram (Hard) ⭐⭐⭐
- LC 85: Maximal Rectangle (Hard)
- LC 1504: Count Submatrices With All Ones (Medium)

Time Complexity: O(n) - each bar pushed/popped once
Space Complexity: O(n) - stack storage
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries each bar as height, extends left/right to find
#                  width — O(n²) worst case"
#   2. Problem:    "For n=10,000 bars: 100M operations to check all rectangles"
#   3. Transition: "Use monotonic stack to find prev/next smaller in one pass —
#                  O(n)"
#
# def largest_rectangle_area_naive(heights):
#     max_area = 0
#     n = len(heights)
#     for i in range(n):
#         min_height = heights[i]
#         for j in range(i, n):
#             min_height = min(min_height, heights[j])
#             width = j - i + 1
#             max_area = max(max_area, min_height * width)
#     return max_area
# ─────────────────────────────────────────────────────────────────────────────

def largest_rectangle_area(heights):
    stack = []  # Monotonic increasing stack
    max_area = 0
    n = len(heights)
    
    for i in range(n):
        # When we find a smaller bar, calculate areas
        while stack and heights[i] < heights[stack[-1]]:
            height_idx = stack.pop()
            height = heights[height_idx]
            
            # Width calculation:
            # Left boundary: previous smaller (stack top)
            # Right boundary: current index (i)
            width = i if not stack else i - stack[-1] - 1
            max_area = max(max_area, height * width)
        
        stack.append(i)
    
    # Process remaining bars
    while stack:
        height_idx = stack.pop()
        height = heights[height_idx]
        width = n if not stack else n - stack[-1] - 1
        max_area = max(max_area, height * width)
    
    return max_area

if __name__ == "__main__":
    heights = [2, 1, 5, 6, 2, 3]
    print(largest_rectangle_area(heights))  # 10
