"""
String DP - Longest Common Subsequence (LCS)

Problem: Find the length of the longest subsequence common to both strings.

Related LeetCode Problems:
- LC 1143: Longest Common Subsequence (Medium)
- LC 583: Delete Operation for Two Strings (Medium)
- LC 712: Minimum ASCII Delete Sum for Two Strings (Medium)

Key Insight: 2D DP comparing characters.
- If chars match: extend previous LCS
- If chars don't match: take best of skipping one char from either string

Time Complexity: O(m × n)
Space Complexity: O(m × n) or O(min(m,n)) optimized
"""

from typing import List


def longest_common_subsequence(text1: str, text2: str) -> int:
    """
    LC 1143: Longest Common Subsequence.
    Time: O(m × n)
    Space: O(m × n)
    """
    m, n = len(text1), len(text2)
    # dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1]
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if text1[i-1] == text2[j-1]:
                # Characters match! Extend previous LCS
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                # Take best of: skip char from text1 OR skip char from text2
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    
    return dp[m][n]




if __name__ == "__main__":
    print("LCS 'abcde' and 'ace':", longest_common_subsequence("abcde", "ace"))  # 3
    print("LCS 'abc' and 'abc':", longest_common_subsequence("abc", "abc"))  # 3
    print("LCS 'abc' and 'def':", longest_common_subsequence("abc", "def"))  # 0
    
    print("\nActual LCS:", lcs_with_string("abcde", "ace"))  # "ace"
