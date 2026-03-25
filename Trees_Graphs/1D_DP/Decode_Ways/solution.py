"""
Decode Ways (Count Combinations)

Pattern: Count ways to decode digit string where A=1, B=2, ..., Z=26

Time Complexity: O(n)
Space Complexity: O(n) - can be optimized to O(1)
"""

def numDecodings(s):
    if not s or s[0] == '0':
        return 0
    
    n = len(s)
    dp = [0] * (n + 1)
    dp[0] = 1  # Empty string
    dp[1] = 1  # First character (already checked it's not '0')
    
    for i in range(2, n + 1):
        # Check one digit
        if s[i-1] != '0':
            dp[i] += dp[i-1]
        
        # Check two digits
        two_digit = int(s[i-2:i])
        if 10 <= two_digit <= 26:
            dp[i] += dp[i-2]
    
    return dp[n]

if __name__ == "__main__":
    print(numDecodings("226"))  # Output: 3
