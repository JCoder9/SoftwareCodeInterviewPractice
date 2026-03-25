"""
Edit Distance (Levenshtein Distance)

Pattern: Minimum operations (insert, delete, replace) to convert word1 to word2

Time Complexity: O(m × n)
Space Complexity: O(m × n) - can optimize to O(min(m,n))
"""

def min_distance(word1, word2):
    m, n = len(word1), len(word2)
    # dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    # Base cases: converting to/from empty string
    for i in range(m + 1):
        dp[i][0] = i  # Delete all i characters
    for j in range(n + 1):
        dp[0][j] = j  # Insert all j characters
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if word1[i-1] == word2[j-1]:
                # Characters match, no operation needed
                dp[i][j] = dp[i-1][j-1]
            else:
                # Min of: replace, delete, insert
                dp[i][j] = 1 + min(
                    dp[i-1][j-1],  # Replace
                    dp[i-1][j],     # Delete from word1
                    dp[i][j-1]      # Insert into word1
                )
    
    return dp[m][n]

# Test
if __name__ == "__main__":
    print(min_distance("horse", "ros"))  # 3
