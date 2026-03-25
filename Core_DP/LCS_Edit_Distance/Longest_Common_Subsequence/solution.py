"""
Longest Common Subsequence (LCS)

Pattern: Find length of longest subsequence common to both strings

Time Complexity: O(m × n)
Space Complexity: O(m × n) - can optimize to O(min(m,n))
"""

def longest_common_subsequence(text1, text2):
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

# Test
if __name__ == "__main__":
    print(longest_common_subsequence("abcde", "ace"))  # 3
