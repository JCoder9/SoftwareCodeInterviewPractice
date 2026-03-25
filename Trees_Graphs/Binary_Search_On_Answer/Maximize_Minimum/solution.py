"""Binary Search on Answer - Maximize the Minimum (LC 1552)
Magnetic Force Between Two Balls: Place m balls in n positions to maximize min distance.
If we can place with min_dist=X, we can with X-1. Search for maximum X.
Time: O(n log n + n * log(max_pos - min_pos)), Space: O(1)"""

def maxDistance(position, m):
    def can_place(min_dist):
        # Can we place m balls with at least min_dist apart?
        count = 1  # Place first ball at position[0]
        last_pos = position[0]
        
        for i in range(1, len(position)):
            if position[i] - last_pos >= min_dist:
                count += 1
                last_pos = position[i]
                if count == m:
                    return True
        
        return False
    
    position.sort()
    
    # Search space: [1, distance between first and last position]
    left = 1
    right = position[-1] - position[0]
    
    while left < right:
        mid = left + (right - left + 1) // 2  # +1 to avoid infinite loop
        
        if can_place(mid):
            # If we can place with min_dist = mid, try larger
            left = mid
        else:
            # If we can't, try smaller
            right = mid - 1
    
    return left

if __name__ == "__main__":
    print(maxDistance([1, 2, 3, 4, 7], 3))  # 3
