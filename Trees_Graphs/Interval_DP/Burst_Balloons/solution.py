"""Interval DP - Burst Balloons (LC 312)
Think reverse: which balloon to burst LAST (not first).
Add boundary balloons with value 1.
dp[left][right] = max coins from bursting balloons in (left, right) exclusive.
Time: O(n³), Space: O(n²)"""

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
