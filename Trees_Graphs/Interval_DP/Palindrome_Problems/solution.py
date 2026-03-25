"""Interval DP - Palindrome Problems (Longest Palindromic Substring)
LC 5: Find longest palindromic substring.
dp[i][j] = s[i]==s[j] AND dp[i+1][j-1]
Build from length 1 to n."""

def longestPalindrome(s: str) -> str:
    n = len(s)
    if n == 0:
        return ""
    dp = [[False] * n for _ in range(n)]
    start = 0
    max_len = 1
    for i in range(n):
        dp[i][i] = True
    for i in range(n - 1):
        if s[i] == s[i + 1]:
            dp[i][i + 1] = True
            start = i
            max_len = 2
    for length in range(3, n + 1):
        for i in range(n - length + 1):
            j = i + length - 1
            if s[i] == s[j] and dp[i + 1][j - 1]:
                dp[i][j] = True
                start = i
                max_len = length
    return s[start:start + max_len]

if __name__ == "__main__":
    print(longestPalindrome("babad"))  # "bab" or "aba"
