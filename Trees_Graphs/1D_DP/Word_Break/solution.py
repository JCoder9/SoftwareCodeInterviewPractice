"""
Word Break (Boolean DP)

Problem: Given string s and dictionary of words, return true if s can be segmented
         into space-separated sequence of dictionary words.
         Example: s = "leetcode", dict = ["leet", "code"] → true

Pattern: dp[i] = true if s[0:i] can be segmented.
         For each position i, check all splits: if s[0:j] works and s[j:i] is a word.

Related LeetCode Problems:
- LC 139: Word Break (Medium) ⭐⭐⭐
- LC 140: Word Break II (Hard) - return all possible sentences
- LC 472: Concatenated Words (Hard)

Time Complexity: O(n² × m) where n = string length, m = average word length
Space Complexity: O(n)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all possible ways to split string recursively
#                  — exponential branching at each position"
#   2. Problem:    "For length-20 string: over 1 million split combinations; massive
#                  overlapping subproblems"
#   3. Transition: "DP memoizes whether prefix can be segmented; each position
#                  solved once — O(n²) time"
#
# def word_break_naive(s, word_dict, start=0):
#     if start == len(s):
#         return True
#     
#     # Try all possible next words
#     for end in range(start + 1, len(s) + 1):
#         word = s[start:end]
#         if word in word_dict:
#             if word_break_naive(s, word_dict, end):
#                 return True
#     return False
# ─────────────────────────────────────────────────────────────────────────────

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
