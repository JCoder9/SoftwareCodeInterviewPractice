"""
Shortest Common Supersequence

Pattern: Find shortest string that has both strings as subsequences

Time Complexity: O(m × n)
Space Complexity: O(m × n)
"""

def shortest_common_supersequence(str1, str2):
    m, n = len(str1), len(str2)
    # First, compute LCS table
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if str1[i-1] == str2[j-1]:
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    
    # Backtrack to build the SCS
    i, j = m, n
    result = []
    
    while i > 0 and j > 0:
        if str1[i-1] == str2[j-1]:
            result.append(str1[i-1])
            i -= 1
            j -= 1
        elif dp[i-1][j] > dp[i][j-1]:
            result.append(str1[i-1])
            i -= 1
        else:
            result.append(str2[j-1])
            j -= 1
    
    # Add remaining characters
    while i > 0:
        result.append(str1[i-1])
        i -= 1
    while j > 0:
        result.append(str2[j-1])
        j -= 1
    
    return ''.join(reversed(result))

# Test
if __name__ == "__main__":
    print(shortest_common_supersequence("abac", "cab"))  # "cabac"
