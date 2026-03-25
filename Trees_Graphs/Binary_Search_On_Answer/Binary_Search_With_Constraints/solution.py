"""Binary Search on Answer - Binary Search with Constraints (LC 1870)
Minimum Speed to Arrive on Time: Find min speed with time constraints.
If speed X works, X+1 also works. Search for minimum working speed.
Time: O(n * log(max_speed)), Space: O(1)"""

import math

def minSpeedOnTime(dist, hour):
    def can_arrive(speed):
        # Can we arrive in time at this speed?
        time = 0
        
        # All trips except last need to round up (wait for next hour)
        for i in range(len(dist) - 1):
            time += math.ceil(dist[i] / speed)
        
        # Last trip doesn't need to round up
        time += dist[-1] / speed
        
        return time <= hour
    
    # Edge case: impossible if not enough hours
    if len(dist) > math.ceil(hour):
        return -1
    
    # Search space: [1, large number like 10^7]
    left = 1
    right = 10**7
    
    while left < right:
        mid = left + (right - left) // 2
        
        if can_arrive(mid):
            right = mid
        else:
            left = mid + 1
    
    return left

if __name__ == "__main__":
    print(minSpeedOnTime([1, 3, 2], 6))     # 1
    print(minSpeedOnTime([1, 3, 2], 2.7))   # 3
