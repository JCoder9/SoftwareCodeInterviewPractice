"""
2D DP - String/Sequence Matching (All Variants Reference)

This file demonstrates string matching patterns. For individual implementations,
see the separate folders.

Common Patterns:
1. Longest Common Subsequence (LCS)
2. Edit Distance (transform one string to another)
3. Longest Common Substring (contiguous)
4. Shortest Common Supersequence
5. Delete Operations for Two Strings
6. Distinct Subsequences (count occurrences)
7. Wildcard Matching (? and *)
8. Regular Expression Matching (. and *)

Recurrence Structure:
dp[i][j] depends on:
- Characters match: dp[i-1][j-1] + something
- Characters differ: combine dp[i-1][j], dp[i][j-1]

Time Complexity: O(m * n)
Space Complexity: O(m * n), often optimizable to O(min(m, n))
"""

# All functions are implemented in their respective folders
# This file serves as a reference

if __name__ == "__main__":
    print("See individual variant folders for implementations:")
    print("- Longest_Common_Subsequence/")
    print("- Edit_Distance/")
    print("- Longest_Common_Substring/")
    print("- Shortest_Common_Supersequence/")
    print("- Delete_Operations/")
    print("- Distinct_Subsequences/")
    print("- Wildcard_Matching/")
    print("- Regex_Matching/")
