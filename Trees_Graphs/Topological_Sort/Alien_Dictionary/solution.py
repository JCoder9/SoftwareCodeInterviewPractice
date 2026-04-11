"""
Alien Dictionary (LC 269)

Problem: Given sorted alien words, find order of alien alphabet characters.
         Example: ["wrt","wrf","er","ett","rftt"] → "wertf"

Pattern: Compare adjacent words to build graph, then topological sort

Related LeetCode Problems:
- LC 269: Alien Dictionary (Hard) ⭐⭐⭐
- LC 953: Verifying an Alien Dictionary (Easy)
- LC 210: Course Schedule II (Medium)

Time Complexity: O(C) where C = total characters in all words
Space Complexity: O(1) or O(26) for English alphabet
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(k! × n × L) time | O(k) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all k! permutations of unique characters,
#                  validates each against word pairs — O(k! × n × L)"
#   2. Problem:    "For k=10 unique chars: 10! = 3.6M permutations to check"
#   3. Transition: "Build directed graph from adjacent word pairs, topological
#                  sort — O(C) total characters"
#
# def alien_order_naive(words):
#     from itertools import permutations
#     # Get all unique characters
#     chars = set(c for word in words for c in word)
#     
#     # Try all permutations of characters
#     for perm in permutations(chars):
#         order_map = {char: i for i, char in enumerate(perm)}
#         valid = True
#         # Check if this ordering validates all adjacent word pairs
#         for i in range(len(words) - 1):
#             word1, word2 = words[i], words[i+1]
#             # Compare words character by character
#             for j in range(min(len(word1), len(word2))):
#                 if order_map[word1[j]] < order_map[word2[j]]:
#                     break
#                 elif order_map[word1[j]] > order_map[word2[j]]:
#                     valid = False
#                     break
#             if not valid:
#                 break
#         if valid:
#             return ''.join(perm)
#     return ""
# ─────────────────────────────────────────────────────────────────────────────

from collections import defaultdict, deque

def alien_order(words):
    """
    LeetCode 269: Alien Dictionary
    Return alien alphabet order or "" if invalid
    """
    # Build graph of character orderings
    graph = defaultdict(set)
    in_degree = {c: 0 for word in words for c in word}
    
    # Compare adjacent words
    for i in range(len(words) - 1):
        word1, word2 = words[i], words[i + 1]
        min_len = min(len(word1), len(word2))
        
        # If word1 is prefix of word2 but longer, invalid
        if len(word1) > len(word2) and word1[:min_len] == word2[:min_len]:
            return ""
        
        # Find first different character
        for j in range(min_len):
            if word1[j] != word2[j]:
                if word2[j] not in graph[word1[j]]:
                    graph[word1[j]].add(word2[j])
                    in_degree[word2[j]] += 1
                break
    
    # Topological sort
    queue = deque([c for c in in_degree if in_degree[c] == 0])
    result = []
    
    while queue:
        char = queue.popleft()
        result.append(char)
        
        for neighbor in graph[char]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)
    
    return ''.join(result) if len(result) == len(in_degree) else ""

# Example
if __name__ == "__main__":
    print(alien_order(["wrt", "wrf", "er", "ett", "rftt"]))  # "wertf"
    Given alien alphabet order, verify if words are sorted.
    """
    order_map = {char: i for i, char in enumerate(order)}
    
    for i in range(len(words) - 1):
        word1, word2 = words[i], words[i + 1]
        
        for j in range(len(word1)):
            # word1 is longer and matches word2 prefix - invalid
            if j >= len(word2):
                return False
            
            if word1[j] != word2[j]:
                if order_map[word1[j]] > order_map[word2[j]]:
                    return False
                break  # Found difference, words are correctly ordered
    
    return True


def alienOrderDFS(words: List[str]) -> str:
    """
    Alien dictionary using DFS-based topological sort.
    """
    graph = defaultdict(set)
    chars = {char for word in words for char in word}
    
    # Build graph from adjacent words
    for i in range(len(words) - 1):
        word1, word2 = words[i], words[i + 1]
        min_len = min(len(word1), len(word2))
        
        if len(word1) > len(word2) and word1[:min_len] == word2[:min_len]:
            return ""
        
        for j in range(min_len):
            if word1[j] != word2[j]:
                graph[word1[j]].add(word2[j])
                break
    
    # DFS with cycle detection
    # 0: unvisited, 1: visiting, 2: visited
    state = {char: 0 for char in chars}
    result = []
    cycle = False
    
    def dfs(char):
        nonlocal cycle
        if state[char] == 1:
            cycle = True
            return
        if state[char] == 2:
            return
        
        state[char] = 1
        
        for neighbor in graph[char]:
            dfs(neighbor)
            if cycle:
                return
        
        state[char] = 2
        result.append(char)
    
    for char in chars:
        if state[char] == 0:
            dfs(char)
            if cycle:
                return ""
    
    return ''.join(result[::-1])


# Test cases
if __name__ == "__main__":
    # Test LC 269
    words1 = ["wrt", "wrf", "er", "ett", "rftt"]
    print("Alien order:", alienOrder(words1))  # "wertf"
    
    words2 = ["z", "x"]
    print("Alien order:", alienOrder(words2))  # "zx"
    
    words3 = ["abc", "ab"]
    print("Alien order (invalid):", alienOrder(words3))  # ""
    
    # Test LC 953
    words4 = ["hello", "leetcode"]
    order = "hlabcdefgijkmnopqrstuvwxyz"
    print("Is alien sorted:", isAlienSorted(words4, order))  # True
    
    words5 = ["word", "world", "row"]
    order2 = "worldabcefghijkmnpqstuvxyz"
    print("Is alien sorted:", isAlienSorted(words5, order2))  # False
    
    # Test DFS version
    print("Alien order (DFS):", alienOrderDFS(words1))  # "wertf"
