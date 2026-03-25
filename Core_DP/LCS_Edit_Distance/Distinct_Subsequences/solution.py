"""
Distinct Subsequences

Pattern: Count how many distinct subsequences of s equal t

Time Complexity: O(m × n)
Space Complexity: O(m × n)
"""

def num_distinct(s, t):
    m, n = len(s), len(t)
    # dp[i][j] = number of ways t[0..j-1] appears in s[0..i-1]
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    # Empty string is subsequence of any string (1 way)
    for i in range(m + 1):
        dp[i][0] = 1
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            # Don't use s[i-1]
            dp[i][j] = dp[i-1][j]
            
            # Use s[i-1] if it matches t[j-1]
            if s[i-1] == t[j-1]:
                dp[i][j] += dp[i-1][j-1]
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(num_distinct("rabbbit", "rabbit"))  # 3
