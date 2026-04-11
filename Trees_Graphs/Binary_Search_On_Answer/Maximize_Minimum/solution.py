"""
Binary Search on Answer - Maximize the Minimum (LC 1552)

Problem: Magnetic Force - Place m balls in n positions to maximize min distance.
         Example: position = [1,2,3,4,7], m = 3 → 3 (positions 1,4,7)

Pattern: If we can place with min_dist=X, we can with X-1. Binary search for max X.

Related LeetCode Problems:
- LC 1552: Magnetic Force Between Two Balls (Medium) ⭐⭐⭐
- LC 2064: Minimized Maximum of Products (Medium)
- LC 875: Koko Eating Bananas (Medium)

Time Complexity: O(n log n + n × log(max_pos)) - sort + binary search
Space Complexity: O(1)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(C(n,m) × m) = exponential time | O(m) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all ways to choose m positions from n, computes
#                  min distance for each — C(n,m) combinations"
#   2. Problem:    "For n=100, m=50: ~10^29 combinations to check"
#   3. Transition: "Binary search on min_distance value with greedy placement check —
#                  O(n log max_dist)"
#
# def max_distance_naive(position, m):
#     from itertools import combinations
#     position.sort()
#     max_min_dist = 0
#     
#     # Try all ways to choose m positions
#     for chosen in combinations(range(len(position)), m):
#         # Compute minimum distance in this configuration
#         min_dist = float('inf')
#         for i in range(len(chosen) - 1):
#             dist = position[chosen[i+1]] - position[chosen[i]]
#             min_dist = min(min_dist, dist)
#         max_min_dist = max(max_min_dist, min_dist)
#     return max_min_dist
# ─────────────────────────────────────────────────────────────────────────────

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
