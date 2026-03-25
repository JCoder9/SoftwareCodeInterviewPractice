"""Monotonic Stack - Trapping Rain Water (LC 42)
Calculate trapped rain water given elevation map.
Water level = min(max_left, max_right) - height[i]
Time: O(n), Space: O(n)"""

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
