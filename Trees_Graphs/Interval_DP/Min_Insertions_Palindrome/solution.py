"""Interval DP - Minimum Insertions to Make Palindrome (LC 1312)
Find longest palindromic subsequence (LPS). Answer = n - LPS.
dp[i][j] = LPS length in s[i:j+1].
If s[i] == s[j]: dp[i][j] = dp[i+1][j-1] + 2
Else: dp[i][j] = max(dp[i+1][j], dp[i][j-1])
Time: O(n²), Space: O(n²)"""

def minInsertions(s: str) -> int:
    n = len(s)
    dp = [[0] * n for _ in range(n)]
    for i in range(n):
        dp[i][i] = 1
    for length in range(2, n + 1):
        for i in range(n - length + 1):
            j = i + length - 1
            if s[i] == s[j]:
                dp[i][j] = dp[i + 1][j - 1] + 2 if i + 1 <= j - 1 else 2
            else:
                dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
    lps_length = dp[0][n - 1]
    return n - lps_length

if __name__ == "__main__":
    print(minInsertions("zzazz"))  # 0
    print(minInsertions("mbadm"))  # 2
