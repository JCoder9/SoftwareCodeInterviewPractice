"""
Wildcard Matching

Pattern: Match string with pattern containing ? (any char) and * (any sequence)

Time Complexity: O(m × n)
Space Complexity: O(m × n)
"""

def is_match(s, p):
    m, n = len(s), len(p)
    # dp[i][j] = s[0..i-1] matches p[0..j-1]
    dp = [[False] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = True
    
    # Handle patterns like "*", "**", etc.
    for j in range(1, n + 1):
        if p[j-1] == '*':
            dp[0][j] = dp[0][j-1]
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if p[j-1] == '*':
                # * matches empty or * matches one+ characters
                dp[i][j] = dp[i][j-1] or dp[i-1][j]
            elif p[j-1] == '?' or s[i-1] == p[j-1]:
                dp[i][j] = dp[i-1][j-1]
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(is_match("adceb", "*a*b"))  # True
