"""
String DP - Longest Common Substring

Problem: Find length of longest CONTINUOUS matching substring.

Related LeetCode Problems:
- LC 718: Maximum Length of Repeated Subarray (Medium)

Key Insight: Unlike LCS, breaks reset to 0.
- dp[i][j] = length of common substring ending at i, j
- If match: extend by 1
- If no match: reset to 0

Time: O(m × n)
Space: O(m × n) or O(n) optimized
"""

def longest_common_substring(text1: str, text2: str) -> int:
    m, n = len(text1), len(text2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    max_len = 0
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if text1[i-1] == text2[j-1]:
                dp[i][j] = dp[i-1][j-1] + 1
                max_len = max(max_len, dp[i][j])
    
    return max_len

if __name__ == "__main__":
    print(longest_common_substring("abcde", "abfce"))  # 2
