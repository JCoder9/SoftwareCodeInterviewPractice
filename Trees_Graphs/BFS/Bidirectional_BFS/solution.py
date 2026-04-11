"""
BFS - Bidirectional BFS (Meet in the Middle)

Problem: Find shortest path in large search space.
         Example: Word Ladder, Open the Lock

Pattern: BFS from both start AND end simultaneously, meet in middle

Related LeetCode Problems:
- LC 127: Word Ladder (Hard) ⭐⭐⭐
- LC 752: Open the Lock (Medium)
- LC 433: Minimum Genetic Mutation (Medium)

Key Insight: Search space grows exponentially - O(b^d) becomes O(b^(d/2))
             Two small searches beat one big one!

Time Complexity: O(b^(d/2)) - much better than O(b^d)
Space Complexity: O(b^(d/2))
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(b^d) time | O(b^d) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Standard BFS from start explores all nodes level-by-level —
#                  O(b^d) where b=branching factor, d=depth"
#   2. Problem:    "For b=10, d=6: 10^6 = 1M states; for d=8: 100M states"
#   3. Transition: "BFS from both ends meets in middle — O(b^(d/2)); for d=6:
#                  2×10^3 = 2K states vs 1M"
#
# def bfs_naive(graph, start, end):
#     from collections import deque
#     queue = deque([(start, 0)])
#     visited = {start}
#     
#     while queue:
#         node, dist = queue.popleft()
#         if node == end:
#             return dist
#         
#         for neighbor in graph[node]:
#             if neighbor not in visited:
#                 visited.add(neighbor)
#                 queue.append((neighbor, dist + 1))
#     return -1
# ─────────────────────────────────────────────────────────────────────────────

from collections import deque

def bidirectional_bfs(graph, start, end):
    """Standard bidirectional BFS"""
    if start == end:
        return 0
    
    queue_start = deque([(start, 0)])
    queue_end = deque([(end, 0)])
    
    visited_start = {start: 0}
    visited_end = {end: 0}
    
    while queue_start or queue_end:
        # Expand from start side
        if queue_start:
            node, dist = queue_start.popleft()
            
            for neighbor in graph[node]:
                if neighbor in visited_end:
                    return dist + 1 + visited_end[neighbor]
                
                if neighbor not in visited_start:
                    visited_start[neighbor] = dist + 1
                    queue_start.append((neighbor, dist + 1))
        
        # Expand from end side
        if queue_end:
            node, dist = queue_end.popleft()
            
            for neighbor in graph[node]:
                if neighbor in visited_start:
                    return dist + 1 + visited_start[neighbor]
                
                if neighbor not in visited_end:
                    visited_end[neighbor] = dist + 1
                    queue_end.append((neighbor, dist + 1))
    
    return -1


def openLock(deadends, target):
    """LC 752: Open the Lock"""
    if target == "0000":
        return 0
    
    dead = set(deadends)
    if "0000" in dead:
        return -1
    
    def neighbors(code):
        """Generate all possible next states"""
        result = []
        for i in range(4):
            digit = int(code[i])
            for d in [-1, 1]:
                new_digit = (digit + d) % 10
                result.append(code[:i] + str(new_digit) + code[i+1:])
        return result
    
    # Bidirectional BFS
    start_set = {"0000"}
    end_set = {target}
    visited = {"0000", target}
    steps = 0
    
    while start_set and end_set:
        # Always expand the smaller set
        if len(start_set) > len(end_set):
            start_set, end_set = end_set, start_set
        
        next_set = set()
        for code in start_set:
            for neighbor in neighbors(code):
                if neighbor in end_set:
                    return steps + 1
                
                if neighbor not in visited and neighbor not in dead:
                    visited.add(neighbor)
                    next_set.add(neighbor)
        
        start_set = next_set
        steps += 1
    
    return -1


def ladderLength(beginWord, endWord, wordList):
    """LC 127: Word Ladder (Bidirectional BFS)"""
    word_set = set(wordList)
    if endWord not in word_set:
        return 0
    
    def neighbors(word):
        """Generate all possible next words"""
        result = []
        for i in range(len(word)):
            for c in 'abcdefghijklmnopqrstuvwxyz':
                if c != word[i]:
                    new_word = word[:i] + c + word[i+1:]
                    if new_word in word_set:
                        result.append(new_word)
        return result
    
    # Bidirectional BFS
    start_set = {beginWord}
    end_set = {endWord}
    visited = {beginWord, endWord}
    steps = 1
    
    while start_set and end_set:
        # Always expand the smaller set
        if len(start_set) > len(end_set):
            start_set, end_set = end_set, start_set
        
        next_set = set()
        for word in start_set:
            for neighbor in neighbors(word):
                if neighbor in end_set:
                    return steps + 1
                
                if neighbor not in visited:
                    visited.add(neighbor)
                    next_set.add(neighbor)
        
        start_set = next_set
        steps += 1
    
    return 0


# Test cases
if __name__ == "__main__":
    # Test basic bidirectional BFS
    print("Testing bidirectional BFS:")
    graph = {
        0: [1, 2], 1: [0, 3], 2: [0, 4],
        3: [1, 5], 4: [2, 5], 5: [3, 4]
    }
    print(bidirectional_bfs(graph, 0, 5))  # 3
    
    # Test openLock
    print("\nTesting openLock:")
    deadends = ["0201", "0101", "0102", "1212", "2002"]
    print(openLock(deadends, "0202"))  # 6
    
    # Test word ladder
    print("\nTesting word ladder:")
    wordList = ["hot", "dot", "dog", "lot", "log", "cog"]
    print(ladderLength("hit", "cog", wordList))  # 5
