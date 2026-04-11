"""
Interval DP - Burst Balloons (LC 312)

Problem: Burst balloons to maximize coins. coins = nums[left]*nums[i]*nums[right]
         Example: nums = [3,1,5,8] → 167

Key Insight: Think reverse - which balloon to burst LAST (not first)
             Add boundary balloons with value 1

Related LeetCode Problems:
- LC 312: Burst Balloons (Hard) ⭐⭐⭐
- LC 1039: Minimum Score Triangulation of Polygon (Medium)
- LC 664: Strange Printer (Hard)

Formula: dp[left][right] = max coins from bursting in (left, right) exclusive

Time Complexity: O(n³) - 3 nested loops
Space Complexity: O(n²) - DP table
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n!) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all n! orderings of bursting balloons,
#                  simulates each order — O(n!)"
#   2. Problem:    "For n=10: 10! = 3.6M orderings; for n=15: 1.3 trillion"
#   3. Transition: "Think backwards: which balloon bursts LAST in interval? Use
#                  interval DP — O(n³)"
#
# def max_coins_naive(nums):
#     from itertools import permutations
#     if not nums:
#         return 0
#     
#     max_coins = 0
#     # Try all orderings
#     for order in permutations(range(len(nums))):
#         coins = 0
#         balloons = [1] + nums[:] + [1]
#         for idx in order:
#             # Find balloon's current position (accounting for previous bursts)
#             actual_idx = idx + 1  # +1 for left boundary
#             coins += balloons[actual_idx-1] * balloons[actual_idx] * balloons[actual_idx+1]
#             balloons.pop(actual_idx)
#         max_coins = max(max_coins, coins)
#     return max_coins
# ─────────────────────────────────────────────────────────────────────────────

def maxCoins(nums: list[int]) -> int:
    balloons = [1] + nums + [1]
    n = len(balloons)
    dp = [[0] * n for _ in range(n)]
    for length in range(2, n):
        for left in range(n - length):
            right = left + length
            for k in range(left + 1, right):
                coins = balloons[left] * balloons[k] * balloons[right]
                total = coins + dp[left][k] + dp[k][right]
                dp[left][right] = max(dp[left][right], total)
    return dp[0][n - 1]

if __name__ == "__main__":
    print(maxCoins([3, 1, 5, 8]))  # 167
