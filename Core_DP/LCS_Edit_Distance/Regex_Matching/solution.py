"""
Regular Expression Matching

Pattern: Match with . (any char) and * (zero+ of previous char)

Time Complexity: O(m × n)
Space Complexity: O(m × n)
"""

def is_match_regex(s, p):
    m, n = len(s), len(p)
    dp = [[False] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = True
    
    # Handle patterns like "a*", "a*b*", etc. matching empty string
    for j in range(2, n + 1):
        if p[j-1] == '*':
            dp[0][j] = dp[0][j-2]
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if p[j-1] == '*':
                # * means zero occurrences
                dp[i][j] = dp[i][j-2]
                
                # * means one+ occurrences (if previous char matches)
                if p[j-2] == '.' or p[j-2] == s[i-1]:
                    dp[i][j] = dp[i][j] or dp[i-1][j]
            elif p[j-1] == '.' or p[j-1] == s[i-1]:
                dp[i][j] = dp[i-1][j-1]
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(is_match_regex("aa", "a*"))  # True
    print(is_match_regex("ab", ".*"))  # True
