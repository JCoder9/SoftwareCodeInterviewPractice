"""
String DP - Edit Distance (Levenshtein Distance)

Problem: Minimum operations (insert, delete, replace) to convert word1 to word2.

Related LeetCode Problems:
- LC 72: Edit Distance (Hard)
- LC 161: One Edit Distance (Medium)
- LC 583: Delete Operation for Two Strings (Medium)

Key Insight: 3 operations to consider at each step.
- If chars match: no operation needed
- If chars don't match: try replace, delete, or insert

Time Complexity: O(m × n)
Space Complexity: O(m × n) or O(min(m,n)) optimized
"""

from typing import List

def min_distance(word1: str, word2: str) -> int:
    """
    LC 72: Edit Distance.
    Time: O(m × n)
    Space: O(m × n)
    """
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


def min_distance_with_operations(word1: str, word2: str) -> tuple:
    """
    Return min distance AND the actual operations.
    """
    m, n = len(word1), len(word2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    for i in range(m + 1):
        dp[i][0] = i
    for j in range(n + 1):
        dp[0][j] = j
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if word1[i-1] == word2[j-1]:
                dp[i][j] = dp[i-1][j-1]
            else:
                dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
    
    # Backtrack operations
    operations = []
    i, j = m, n
    
    while i > 0 or j > 0:
        if i == 0:
            operations.append(f"Insert '{word2[j-1]}'")
            j -= 1
        elif j == 0:
            operations.append(f"Delete '{word1[i-1]}'")
            i -= 1
        elif word1[i-1] == word2[j-1]:
            i -= 1
            j -= 1
        else:
            replace_cost = dp[i-1][j-1]
            delete_cost = dp[i-1][j]
            insert_cost = dp[i][j-1]
            
            if replace_cost <= delete_cost and replace_cost <= insert_cost:
                operations.append(f"Replace '{word1[i-1]}' with '{word2[j-1]}'")
                i -= 1
                j -= 1
            elif delete_cost <= insert_cost:
                operations.append(f"Delete '{word1[i-1]}'")
                i -= 1
            else:
                operations.append(f"Insert '{word2[j-1]}'")
                j -= 1
    
    return dp[m][n], operations[::-1]


def min_distance_optimized(word1: str, word2: str) -> int:
    """
    Space-optimized to O(min(m,n)).
    """
    if len(word1) > len(word2):
        word1, word2 = word2, word1
    
    m, n = len(word1), len(word2)
    prev = list(range(m + 1))
    curr = [0] * (m + 1)
    
    for j in range(1, n + 1):
        curr[0] = j
        for i in range(1, m + 1):
            if word1[i-1] == word2[j-1]:
                curr[i] = prev[i-1]
            else:
                curr[i] = 1 + min(prev[i-1], prev[i], curr[i-1])
        prev, curr = curr, prev
    
    return prev[m]


# Test cases
if __name__ == "__main__":
    print("horse -> ros:", min_distance("horse", "ros"))  # 3
    print("intention -> execution:", min_distance("intention", "execution"))  # 5
    
    dist, ops = min_distance_with_operations("horse", "ros")
    print(f"\nDistance: {dist}")
    print("Operations:", ops)
    
    print(f"\nOptimized: {min_distance_optimized('horse', 'ros')}")
