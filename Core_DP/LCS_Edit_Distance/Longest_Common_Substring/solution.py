"""
Longest Common Substring

Pattern: Find length of longest continuous matching substring

Time Complexity: O(m × n)
Space Complexity: O(m × n) - can optimize to O(n)
"""

def longest_common_substring(text1, text2):
    m, n = len(text1), len(text2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    max_len = 0
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if text1[i-1] == text2[j-1]:
                # Extend the substring
                dp[i][j] = dp[i-1][j-1] + 1
                max_len = max(max_len, dp[i][j])
            # else: dp[i][j] stays 0 (substring breaks)
    
    return max_len

# Test
if __name__ == "__main__":
    print(longest_common_substring("abcde", "abfce"))  # 2
