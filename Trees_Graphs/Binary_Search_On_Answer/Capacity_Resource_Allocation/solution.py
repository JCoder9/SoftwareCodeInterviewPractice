"""Binary Search on Answer - Capacity/Resource Allocation (LC 875)
Koko Eating Bananas: Find minimum k to finish all piles in h hours.
If Koko can finish with speed K, she can with K+1. Search for min K.
Time: O(n * log(max_pile)), Space: O(1)"""

import math

def minEatingSpeed(piles, h):
    def can_finish(speed):
        # Can Koko finish all piles in h hours at this speed?
        hours = 0
        for pile in piles:
            hours += math.ceil(pile / speed)
            if hours > h:
                return False
        return True
    
    # Search space: [1, max pile size]
    left = 1
    right = max(piles)
    
    while left < right:
        mid = left + (right - left) // 2
        
        if can_finish(mid):
            # Can finish with this speed, try slower
            right = mid
        else:
            # Too slow, need faster speed
            left = mid + 1
    
    return left

if __name__ == "__main__":
    print(minEatingSpeed([3, 6, 7, 11], 8))  # 4
