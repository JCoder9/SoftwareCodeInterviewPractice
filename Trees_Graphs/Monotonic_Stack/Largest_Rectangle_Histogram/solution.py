"""Monotonic Stack - Largest Rectangle in Histogram (LC 84)
Find largest rectangular area in histogram.
For each bar, find previous/next smaller elements to determine width.
Time: O(n), Space: O(n)"""

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
