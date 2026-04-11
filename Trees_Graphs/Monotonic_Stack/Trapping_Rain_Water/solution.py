"""
Monotonic Stack - Trapping Rain Water (LC 42)

Problem: Calculate trapped rain water given elevation map.
         Example: height = [0,1,0,2,1,0,1,3,2,1,2,1] → 6 units

Pattern: Water level = min(max_left, max_right) - height[i]

Related LeetCode Problems:
- LC 42: Trapping Rain Water (Hard) ⭐⭐⭐
- LC 407: Trapping Rain Water II (Hard)
- LC 1944: Number of Visible People in a Queue (Hard)

Time Complexity: O(n) - single/double pass
Space Complexity: O(n) - arrays for left/right max or stack
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force scans left and right from each position to find max
#                  heights — O(n²)"
#   2. Problem:    "For n=10,000: 100M comparisons to find left/right max for each
#                  position"
#   3. Transition: "Pre-compute left_max and right_max arrays in two passes — O(n)"
#
# def trap_rain_water_naive(height):
#     water = 0
#     n = len(height)
#     for i in range(n):
#         # Find max height to the left
#         left_max = 0
#         for j in range(0, i + 1):
#             left_max = max(left_max, height[j])
#         # Find max height to the right
#         right_max = 0
#         for j in range(i, n):
#             right_max = max(right_max, height[j])
#         # Water at position i
#         water += min(left_max, right_max) - height[i]
#     return water
# ─────────────────────────────────────────────────────────────────────────────

def trap_rain_water(height):
    if not height:
        return 0
    
    n = len(height)
    left_max = [0] * n
    right_max = [0] * n
    
    # Find max height to the left of each position
    left_max[0] = height[0]
    for i in range(1, n):
        left_max[i] = max(left_max[i-1], height[i])
    
    # Find max height to the right of each position
    right_max[n-1] = height[n-1]
    for i in range(n-2, -1, -1):
        right_max[i] = max(right_max[i+1], height[i])
    
    # Calculate trapped water
    water = 0
    for i in range(n):
        water_level = min(left_max[i], right_max[i])
        water += max(0, water_level - height[i])
    
    return water

def trap_rain_water_stack(height):
    """Stack-based solution"""
    stack = []
    water = 0
    
    for i in range(len(height)):
        # When we find a higher bar, calculate water trapped
        while stack and height[i] > height[stack[-1]]:
            bottom = stack.pop()
            
            if not stack:
                break
            
            # Distance between boundaries
            distance = i - stack[-1] - 1
            # Height of water = min of two boundaries - bottom
            bounded_height = min(height[i], height[stack[-1]]) - height[bottom]
            water += distance * bounded_height
        
        stack.append(i)
    
    return water

if __name__ == "__main__":
    heights = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
    print(trap_rain_water(heights))        # 6
    print(trap_rain_water_stack(heights))  # 6
