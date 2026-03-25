"""
Word Break (Boolean DP)

Pattern: Given a string and dictionary, determine if string can be segmented into words

Time Complexity: O(n² × m) where n = string length, m = average word length
Space Complexity: O(n)
"""

def wordBreak(s, wordDict):
    word_set = set(wordDict)
    n = len(s)
    # dp[i] = True if s[0:i] can be segmented
    dp = [False] * (n + 1)
    dp[0] = True  # Empty string
    
    for i in range(1, n + 1):
        for j in range(i):
            # If s[0:j] is breakable and s[j:i] is a word
            if dp[j] and s[j:i] in word_set:
                dp[i] = True
                break
    
    return dp[n]

if __name__ == "__main__":
    print(wordBreak("leetcode", ["leet", "code"]))  # Output: True
