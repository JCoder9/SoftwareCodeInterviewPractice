"""
Interval DP - Matrix Chain Multiplication / Merge Stones

Problem: Merge adjacent stone piles with cost = sum of merged stones.
         Example: stones = [3,4,3] → 17 (merge 3+4=7 cost 7, then 7+3=10 cost 10)

Pattern: Interval DP - try all split points k in [i, j-1]
         dp[i][j] = min cost to merge stones[i:j+1]

Related LeetCode Problems:
- LC 1000: Minimum Cost to Merge Stones (Hard) ⭐⭐⭐
- LC 312: Burst Balloons (Hard)
- LC 1039: Minimum Score Triangulation (Medium)

Formula: dp[i][j] = min(dp[i][k] + dp[k+1][j] + sum[i:j])

Time Complexity: O(n³) - 3 nested loops
Space Complexity: O(n²) - DP table
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all ways to parenthesize n-1 merges — Catalan
#                  number = O(4^n / n^1.5) approaches"
#   2. Problem:    "For n=20: ~10^9 ways to merge; each requires O(n) to compute cost"
#   3. Transition: "Use interval DP to cache subproblems [i,j] — O(n³)"
#
# def merge_stones_naive(stones):
#     def helper(arr):
#         if len(arr) == 1:
#             return 0
#         min_cost = float('inf')
#         # Try merging at each position
#         for i in range(len(arr) - 1):
#             merged_val = arr[i] + arr[i + 1]
#             new_arr = arr[:i] + [merged_val] + arr[i+2:]
#             cost = merged_val + helper(new_arr)
#             min_cost = min(min_cost, cost)
#         return min_cost
#     return helper(stones)
# ─────────────────────────────────────────────────────────────────────────────

def mergeStones(stones: list[int]) -> int:
    n = len(stones)
    prefix = [0] * (n + 1)
    for i in range(n):
        prefix[i + 1] = prefix[i] + stones[i]
    dp = [[0] * n for _ in range(n)]
    for length in range(2, n + 1):
        for i in range(n - length + 1):
            j = i + length - 1
            dp[i][j] = float('inf')
            for k in range(i, j):
                cost = dp[i][k] + dp[k + 1][j] + prefix[j + 1] - prefix[i]
                dp[i][j] = min(dp[i][j], cost)
    return dp[0][n - 1]

if __name__ == "__main__":
    print(mergeStones([3, 4, 3]))  # 17
