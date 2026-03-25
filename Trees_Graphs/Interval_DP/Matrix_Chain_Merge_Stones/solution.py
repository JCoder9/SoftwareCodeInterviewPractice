"""Interval DP - Matrix Chain Multiplication (Merge Stones)
Merge adjacent piles. dp[i][j] = min cost to merge stones[i:j+1].
For k from i to j-1: dp[i][j] = min(dp[i][k] + dp[k+1][j] + sum[i:j])
Time: O(n³), Space: O(n²)"""

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
